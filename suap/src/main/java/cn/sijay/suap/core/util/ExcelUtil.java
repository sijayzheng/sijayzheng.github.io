package cn.sijay.suap.core.util;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>ExcelUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/19 9:48
 */
@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ExcelUtil {

    public static <T> List<T> read(String path, Class<T> clazz) {
        return read(new File(path), clazz);
    }

    public static <T> List<T> read(File file, Class<T> clazz) {
        try (Workbook workbook = WorkbookFactory.create(file)) {
            return convertList(readSheet(workbook.getSheetAt(0)), clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.EXCEL_READ_ERROR);
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
                        field.set(t, map.get(annotation.value()));
                    }
                }
                return t;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("{} 实例化失败", clazz.getName());
                return null;
            }
        }).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
    }

    public static List<HashMap<String, String>> readSheet(Sheet sheet) {
        HashMap<Integer, String> titleMap = getTitle(sheet);
        return getData(sheet, titleMap);
    }

    private static HashMap<Integer, String> getTitle(Sheet sheet) {
        HashMap<Integer, String> titleMap = new HashMap<>();
        for (Cell cell : sheet.getRow(0)) {
            titleMap.put(cell.getColumnIndex(), getCellValue(cell));
        }
        return titleMap;
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

    private static String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> DateUtil.isCellDateFormatted(cell) ?
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue()) :
                    BigDecimal.valueOf(cell.getNumericCellValue()).toString();
            case STRING -> cell.getRichStringCellValue().getString();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case ERROR -> String.valueOf(cell.getErrorCellValue());
            default -> cell.getRichStringCellValue().toString();
        };
    }

    public static <T> void export(List<T> list, String name, Class<T> clazz, HttpServletResponse response) {
        HashMap<String, Integer> map = new HashMap<>();
        try (Workbook workbook = WorkbookFactory.create(true)) {
            Sheet sheet = workbook.createSheet(name);
            Row titleRow = sheet.createRow(0);
            for (Field field : clazz.getDeclaredFields()) {
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    titleRow.createCell(annotation.sort()).setCellValue(annotation.value());
                    map.put(field.getName(), annotation.sort());
                }
            }
            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
                Row row = sheet.createRow(i + 1);
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    Field field = clazz.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    row.createCell(entry.getValue()).setCellValue(field.get(t).toString());
                }
            }
            ResponseUtil.setDownloadFileName(response, name + FileUtil.getFileSuffix() + ".xlsx");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

    public static <T> void exportTemplate(String name, Class<T> clazz, HttpServletResponse response) {
        try (Workbook workbook = WorkbookFactory.create(true)) {
            Sheet sheet = workbook.createSheet(name);
            Row titleRow = sheet.createRow(0);
            for (Field field : clazz.getDeclaredFields()) {
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    titleRow.createCell(annotation.sort()).setCellValue(annotation.value());
                }
            }
            ResponseUtil.setDownloadFileName(response, name + FileUtil.getFileSuffix() + ".xlsx");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

    public static <T> List<T> read(MultipartFile file, Class<T> clazz) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            return convertList(readSheet(workbook.getSheetAt(0)), clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.EXCEL_READ_ERROR);
        }
    }

}