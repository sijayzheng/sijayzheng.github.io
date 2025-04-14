package cn.sijay.biu.core.strategy;

import cn.sijay.biu.core.annotation.CellMerge;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.core.util.ReflectUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.WorkbookWriteHandler;
import com.alibaba.excel.write.handler.context.WorkbookWriteHandlerContext;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 列值重复合并策略
 *
 * @author Sijay
 */
@Slf4j
public class CellMergeStrategy extends AbstractMergeStrategy implements WorkbookWriteHandler {

    private final List<CellRangeAddress> cellList;
    private final boolean hasTitle;
    private int rowIndex;

    public CellMergeStrategy(List<?> list, boolean hasTitle) {
        this.hasTitle = hasTitle;
        // 行合并开始下标
        this.rowIndex = hasTitle ? 1 : 0;
        this.cellList = handle(list, hasTitle);
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        //单元格写入了,遍历合并区域,如果该Cell在区域内,但非首行,则清空
        final int rowIndex = cell.getRowIndex();
        if (CollectionUtil.isNotEmpty(cellList)) {
            for (CellRangeAddress cellAddresses : cellList) {
                final int firstRow = cellAddresses.getFirstRow();
                if (cellAddresses.isInRange(cell) && rowIndex != firstRow) {
                    cell.setBlank();
                }
            }
        }
    }

    @Override
    public void afterWorkbookDispose(final WorkbookWriteHandlerContext context) {
        //当前表格写完后，统一写入
        if (CollectionUtil.isNotEmpty(cellList)) {
            for (CellRangeAddress item : cellList) {
                context.getWriteContext().writeSheetHolder().getSheet().addMergedRegion(item);
            }
        }
    }

    @SneakyThrows
    private List<CellRangeAddress> handle(List<?> list, boolean hasTitle) {
        List<CellRangeAddress> cellList = new ArrayList<>();
        if (CollectionUtil.isEmpty(list)) {
            return cellList;
        }
        List<Field> fields = ReflectUtil.getFields(list.getFirst()
                                                       .getClass(), field -> !"serialVersionUID".equals(field.getName()));

        // 有注解的字段
        List<Field> mergeFields = new ArrayList<>();
        List<Integer> mergeFieldsIndex = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if (field.isAnnotationPresent(CellMerge.class)) {
                CellMerge cm = field.getAnnotation(CellMerge.class);
                mergeFields.add(field);
                mergeFieldsIndex.add(cm.index() == -1 ? i : cm.index());
                if (hasTitle) {
                    ExcelProperty property = field.getAnnotation(ExcelProperty.class);
                    rowIndex = Math.max(rowIndex, property.value().length);
                }
            }
        }

        Map<Field, RepeatCell> map = new HashMap<>();
        // 生成两两合并单元格
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < mergeFields.size(); j++) {
                Field field = mergeFields.get(j);
                Object val = cn.sijay.biu.core.util.ReflectUtil.invokeGetter(list.get(i), field.getName());

                int colNum = mergeFieldsIndex.get(j);
                if (!map.containsKey(field)) {
                    map.put(field, new RepeatCell(val, i));
                } else {
                    RepeatCell repeatCell = map.get(field);
                    Object cellValue = repeatCell.getValue();
                    if (cellValue == null || "".equals(cellValue)) {
                        // 空值跳过不合并
                        continue;
                    }

                    if (!cellValue.equals(val)) {
                        if ((i - repeatCell.getCurrent() > 1)) {
                            cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, colNum, colNum));
                        }
                        map.put(field, new RepeatCell(val, i));
                    } else if (i == list.size() - 1) {
                        if (i > repeatCell.getCurrent() && isMerge(list, i, field)) {
                            cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex, colNum, colNum));
                        }
                    } else if (!isMerge(list, i, field)) {
                        if ((i - repeatCell.getCurrent() > 1)) {
                            cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, colNum, colNum));
                        }
                        map.put(field, new RepeatCell(val, i));
                    }
                }
            }
        }
        return cellList;
    }

    private boolean isMerge(List<?> list, int i, Field field) {
        boolean isMerge = true;
        CellMerge cm = field.getAnnotation(CellMerge.class);
        final String[] mergeBy = cm.mergeBy();
//        if (StringUtil.isAllNotBlank(mergeBy)) {
//            //比对当前list(i)和list(i - 1)的各个属性值一一比对 如果全为真 则为真
//            for (String fieldName : mergeBy) {
//                final Object valCurrent = ReflectUtil.getFieldValue(list.get(i), fieldName);
//                final Object valPre = ReflectUtil.getFieldValue(list.get(i - 1), fieldName);
//                if (!Objects.equals(valPre, valCurrent)) {
//                    //依赖字段如有任一不等值,则标记为不可合并
//                    isMerge = false;
//                }
//            }
//        }
        return isMerge;
    }

    @Data
    @AllArgsConstructor
    static class RepeatCell {

        private Object value;

        private int current;

    }
}
