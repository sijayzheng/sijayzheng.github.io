package cn.sijay.bun.common.util;

import cn.sijay.bun.core.annotation.CellMerge;
import cn.sijay.bun.core.convert.ExcelBigNumberConvert;
import cn.sijay.bun.core.convert.ExcelBooleanStringConvert;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <strong>ExcelUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-20
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelUtil {

    /**
     * 同步导入(适用于小数据量)
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public static <T> List<T> importExcel(InputStream is, Class<T> clazz) {
        return EasyExcel.read(is).head(clazz).autoCloseStream(false).sheet().doReadSync();
    }

    /**
     * 使用自定义监听器 异步导入 自定义返回
     *
     * @param is       输入流
     * @param clazz    对象类型
     * @param listener 自定义监听器
     * @return 转换后集合
     */
//    public static <T> ExcelResult<T> importExcel(InputStream is, Class<T> clazz, ExcelListener<T> listener) {
//        EasyExcel.read(is, clazz, listener).sheet().doRead();
//        return listener.getExcelResult();
//    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param response  响应体
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, HttpServletResponse response) {
        exportExcel(list, sheetName, clazz, false, response);
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param merge     是否合并单元格
     * @param response  响应体
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
            exportExcel(list, sheetName, clazz, merge, os);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param os        输出流
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, OutputStream os) {
        exportExcel(list, sheetName, clazz, false, os);
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param merge     是否合并单元格
     * @param os        输出流
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, OutputStream os) {
        ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
                                                   .autoCloseStream(false)
                                                   // 自动适配
                                                   .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                                                   // 大数值自动转换 防止失真
                                                   .registerConverter(new ExcelBigNumberConvert())
                                                   .registerConverter(new ExcelBooleanStringConvert())
                                                   .sheet(sheetName);
        if (merge) {
            // 合并处理器
            builder.registerWriteHandler(new CellMergeStrategy(list, true));
        }
        // 添加下拉框操作
        builder.doWrite(list);
    }

    /**
     * 重置响应体
     */
    private static void resetResponse(String sheetName, HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = sheetName + "_" + UuidUtil.fastUuid() + ".xlsx";
        String encode = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        String percentEncodedFileName = encode.replaceAll("\\+", "%20");
        String contentDispositionValue = "attachment; filename=%s;filename*=utf-8''%s".formatted(percentEncodedFileName, percentEncodedFileName);
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
        response.setHeader("Content-disposition", contentDispositionValue);
        response.setHeader("download-filename", percentEncodedFileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
    }

    static class CellMergeStrategy extends AbstractMergeStrategy {

        private final List<CellRangeAddress> cellList;
        private final boolean hasTitle;
        private int rowIndex;

        public CellMergeStrategy(List<?> list, boolean hasTitle) {
            this.hasTitle = hasTitle;
            // 行合并开始下标
            this.rowIndex = hasTitle ? 1 : 0;
            this.cellList = handle(list, hasTitle);
        }

        public CellMergeStrategy(List<?> list, boolean hasTitle, int rowIndex) {
            this.hasTitle = hasTitle;
            // 行合并开始下标
            this.rowIndex = rowIndex;
            this.cellList = handle(list, hasTitle);
        }

        @Override
        protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
            // judge the list is not null
            if (CollectionUtils.isNotEmpty(cellList)) {
                // the judge is necessary
                if (cell.getRowIndex() == rowIndex && cell.getColumnIndex() == 0) {
                    for (CellRangeAddress item : cellList) {
                        sheet.addMergedRegion(item);
                    }
                }
            }
        }

        @SneakyThrows
        private List<CellRangeAddress> handle(List<?> list, boolean hasTitle) {
            List<CellRangeAddress> cellList = new ArrayList<>();
            if (CollectionUtils.isEmpty(list)) {
                return cellList;
            }
            Class<?> clazz = list.getFirst().getClass();
            List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(field -> !"serialVersionUID".equals(field.getName())).toList();
            // 有注解的字段
            List<Field> mergeFields = new ArrayList<>();
            Field mainField = null;
            int mainFieldIndex = 0;
            List<Integer> mergeFieldsIndex = new ArrayList<>();
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                if (field.isAnnotationPresent(CellMerge.class)) {
                    CellMerge cm = field.getAnnotation(CellMerge.class);
                    if (cm.main()) {
                        mainField = field;
                        mainFieldIndex = cm.index() == -1 ? i : cm.index();
                    } else {
                        mergeFields.add(field);
                        mergeFieldsIndex.add(cm.index() == -1 ? i : cm.index());
                    }
                    if (hasTitle) {
                        ExcelProperty property = field.getAnnotation(ExcelProperty.class);
                        rowIndex = Math.max(rowIndex, property.value().length);
                    }
                }
            }

            Map<Field, RepeatCell> map = new HashMap<>();
            // 生成两两合并单元格
            if (mainField == null) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < mergeFields.size(); j++) {
                        Field field = mergeFields.get(j);
                        Object val = ReflectUtil.invokeGetter(clazz, list.get(i), field.getName());
                        int colNum = mergeFieldsIndex.get(j);
                        if (!map.containsKey(field)) {
                            map.put(field, new RepeatCell(val, i));
                        } else {
                            RepeatCell repeatCell = map.get(field);
                            Object cellValue = repeatCell.getValue();
                            if (cellValue == null || "".equals(cellValue) || val == null || "".equals(val)) {
                                // 空值跳过不合并
                                if (cellValue != null && !"".equals(cellValue) && repeatCell.getCurrent() + rowIndex != i + rowIndex - 1) {
                                    cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, colNum, colNum));
                                }
                                map.put(field, new RepeatCell(val, i));
                            } else {
                                if (!Objects.equals(cellValue, val)) {
                                    if (i - repeatCell.getCurrent() > 1) {
                                        cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, colNum, colNum));
                                    }
                                    map.put(field, new RepeatCell(val, i));
                                } else if (i == list.size() - 1) {
                                    if (i > repeatCell.getCurrent()) {
                                        cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex, colNum, colNum));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Object val = ReflectUtil.invokeGetter(clazz, list.get(i), mainField.getName());
                    if (!map.containsKey(mainField)) {
                        map.put(mainField, new RepeatCell(val, i));
                    } else {
                        RepeatCell repeatCell = map.get(mainField);
                        Object cellValue = repeatCell.getValue();
                        if (cellValue == null || "".equals(cellValue) || val == null || "".equals(val)) {
                            // 空值跳过不合并
                            if (cellValue != null && !"".equals(cellValue) && repeatCell.getCurrent() + rowIndex != i + rowIndex - 1) {
                                cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, mainFieldIndex, mainFieldIndex));
                                for (int j = 0; j < mergeFields.size(); j++) {
                                    cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, mergeFieldsIndex.get(j), mergeFieldsIndex.get(j)));
                                }
                            }
                            map.put(mainField, new RepeatCell(val, i));
                        } else {
                            if (!Objects.equals(cellValue, val)) {
                                if (i - repeatCell.getCurrent() > 1) {
                                    cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, mainFieldIndex, mainFieldIndex));
                                    for (int j = 0; j < mergeFields.size(); j++) {
                                        cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, mergeFieldsIndex.get(j), mergeFieldsIndex.get(j)));
                                    }
                                }
                                map.put(mainField, new RepeatCell(val, i));
                            } else if (i == list.size() - 1) {
                                if (i > repeatCell.getCurrent()) {
                                    cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex, mainFieldIndex, mainFieldIndex));
                                    for (int j = 0; j < mergeFields.size(); j++) {
                                        cellList.add(new CellRangeAddress(repeatCell.getCurrent() + rowIndex, i + rowIndex - 1, mergeFieldsIndex.get(j), mergeFieldsIndex.get(j)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return cellList;
        }

        @Data
        @AllArgsConstructor
        static class RepeatCell {

            private Object value;

            private int current;

        }
    }
}
