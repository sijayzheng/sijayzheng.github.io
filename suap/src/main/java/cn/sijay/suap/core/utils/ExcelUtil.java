package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <strong>ExcelUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
public class ExcelUtil {

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param response  响应体
     */
    public static <T> void export(List<T> list, String sheetName, Class<T> clazz, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
            exportExcel(list, sheetName, clazz, false, os);
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
     * @param merge     是否合并单元格
     * @param response  响应体
     */
    public static <T> void export(List<T> list, String sheetName, Class<T> clazz, boolean merge, HttpServletResponse response) {
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
    public static <T> void export(List<T> list, String sheetName, Class<T> clazz, OutputStream os) {
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
//                                                   .registerConverter(new ExcelBigNumberConvert())
                                                   .sheet(sheetName);
        if (merge) {
            // 合并处理器
//            builder.registerWriteHandler(new CellMergeStrategy(list, true));
        }
        // 添加下拉框操作
        builder.doWrite(list);
    }

    public static <T> List<T> read(MultipartFile file, Class<T> clazz) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            HashMap<Integer, String> titleMap = getTitle(sheet);
            List<HashMap<String, String>> list = getData(sheet, titleMap);
            return convertList(list, clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.EXCEL_READ_ERROR);
        }
    }

    private static <T> List<T> convertList(List<HashMap<String, String>> list, Class<T> clazz) {
        return list.stream().map(map -> {
            try {
                T t = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                    if (annotation != null) {
                        field.setAccessible(true);
                        field.set(t, map.get(annotation.value()[0]));
                    }
                }
                return t;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("{} 实例化失败", clazz.getName());
                return null;
            }
        }).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
    }

    private static HashMap<Integer, String> getTitle(Sheet sheet) {
        HashMap<Integer, String> titleMap = new HashMap<>();
        for (Cell cell : sheet.getRow(0)) {
            titleMap.put(cell.getColumnIndex(), getCellValue(cell));
        }
        return titleMap;
    }

    private static String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell) ?
                cell.getLocalDateTimeCellValue().toString() :
                BigDecimal.valueOf(cell.getNumericCellValue()).toString();
            case STRING -> cell.getRichStringCellValue().getString();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case ERROR -> String.valueOf(cell.getErrorCellValue());
            default -> cell.getRichStringCellValue().toString();
        };
    }

    private static List<HashMap<String, String>> getData(Sheet sheet, HashMap<Integer, String> titleMap) {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            HashMap<String, String> map = new HashMap<>();
            for (Cell cell : sheet.getRow(j)) {
                map.put(titleMap.get(cell.getColumnIndex()), getCellValue(cell));
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 重置响应体
     */
    private static void resetResponse(String sheetName, HttpServletResponse response) {
        String filename = GenerateUtil.generateSimpleUUID() + "_" + sheetName + ".xlsx";
        FileUtil.setAttachmentResponseHeader(response, filename);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
    }

    public static <T> void exportTemplate(String name, Class<T> clazz, HttpServletResponse response) {
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                     .autoCloseStream(false)
                     // 自动适配
                     .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                     .sheet(name).doWrite(new ArrayList<>());
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.EXCEL_TEMPLATE_EXPORT_ERROR);
        }
    }
}
