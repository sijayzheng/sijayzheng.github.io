package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.exception.UtilException;
import com.alibaba.excel.EasyExcel;
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

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <strong>ExcelUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
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

    protected static <T> List<T> convertList(List<HashMap<String, String>> list, Class<T> clazz) {
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

    protected static List<HashMap<String, String>> readSheet(Sheet sheet) {
        HashMap<Integer, String> titleMap = getTitle(sheet);
        return getData(sheet, titleMap);
    }

    protected static HashMap<Integer, String> getTitle(Sheet sheet) {
        HashMap<Integer, String> titleMap = new HashMap<>();
        for (Cell cell : sheet.getRow(0)) {
            titleMap.put(cell.getColumnIndex(), getCellValue(cell));
        }
        return titleMap;
    }

    protected static List<HashMap<String, String>> getData(Sheet sheet, HashMap<Integer, String> titleMap) {
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

    protected static String getCellValue(Cell cell) {
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

    /**
     * 反向解析值 男=0,女=1,未知=2
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String reverseByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(StringUtil.SEPARATOR);
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtil.containsAny(propertyValue, separator)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[1].equals(value)) {
                        propertyString.append(itemArray[0]).append(separator);
                        break;
                    }
                }
            } else {
                if (itemArray[1].equals(propertyValue)) {
                    return itemArray[0];
                }
            }
        }
        return StringUtil.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String convertByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(StringUtil.SEPARATOR);
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtil.containsAny(propertyValue, separator)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[0].equals(value)) {
                        propertyString.append(itemArray[1]).append(separator);
                        break;
                    }
                }
            } else {
                if (itemArray[0].equals(propertyValue)) {
                    return itemArray[1];
                }
            }
        }
        return StringUtil.stripEnd(propertyString.toString(), separator);
    }

    public static <T> void export(List<T> list, String name, Class<T> clazz, HttpServletResponse response) {
        exportExcel(list, "项目分包合同", clazz, response);

    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param response  响应体
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, false, os, null);
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
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, merge, os, null);
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
     * @param response  响应体
     * @param options   级联下拉选
     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, HttpServletResponse response, List<DropDownOptions> options) {
//        try {
//            resetResponse(sheetName, response);
//            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, false, os, options);
//        } catch (IOException e) {
//            throw new RuntimeException("导出Excel异常");
//        }
//    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param os        输出流
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, OutputStream os) {
//        exportExcel(list, sheetName, clazz, false, os, null);
    }

//    /**
//     * 导出excel
//     *
//     * @param list      导出数据集合
//     * @param sheetName 工作表的名称
//     * @param clazz     实体类
//     * @param merge     是否合并单元格
//     * @param response  响应体
//     * @param options   级联下拉选
//     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, HttpServletResponse response, List<DropDownOptions> options) {
//        try {
//            resetResponse(sheetName, response);
//            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, merge, os, options);
//        } catch (IOException e) {
//            throw new RuntimeException("导出Excel异常");
//        }
//    }

    //    /**
//     * 导出excel
//     *
//     * @param list      导出数据集合
//     * @param sheetName 工作表的名称
//     * @param clazz     实体类
//     * @param merge     是否合并单元格
//     * @param os        输出流
//     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, OutputStream os, List<DropDownOptions> options) {
//        ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
//                                                   .autoCloseStream(false)
//                                                   // 自动适配
//                                                   .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
//                                                   // 大数值自动转换 防止失真
//                                                   .registerConverter(new ExcelBigNumberConvert())
//                                                   .sheet(sheetName);
//        if (merge) {
//            // 合并处理器
//            builder.registerWriteHandler(new CellMergeStrategy(list, true));
//        }
//        // 添加下拉框操作
//        builder.registerWriteHandler(new ExcelDownHandler(options));
//        builder.doWrite(list);
//    }
    public static <T> void exportTemplate(String name, Class<T> clazz, HttpServletResponse response) {
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                     .autoCloseStream(false)
                     // 自动适配
                     .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                     .sheet(name).doWrite(new ArrayList<>());
        } catch (IOException e) {
            throw new UtilException(ExceptionEnum.EXCEL_TEMPLATE_EXPORT_ERROR);
        }
    }

    //    /**
//     * 导出excel
//     *
//     * @param list      导出数据集合
//     * @param sheetName 工作表的名称
//     * @param clazz     实体类
//     * @param os        输出流
//     * @param options   级联下拉选内容
//     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, OutputStream os, List<DropDownOptions> options) {
//        exportExcel(list, sheetName, clazz, false, os, options);
//    }
    public static <T> List<T> read(MultipartFile file, Class<T> clazz) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            return convertList(readSheet(workbook.getSheetAt(0)), clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.EXCEL_READ_ERROR);
        }
    }

    /**
     * 同步导入(适用于小数据量)
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public static <T> List<T> importExcel(InputStream is, Class<T> clazz) {
        return EasyExcel.read(is).head(clazz).autoCloseStream(false).sheet().doReadSync();
    }

//    /**
//     * 单表多数据模板导出 模板格式为 {.属性}
//     *
//     * @param filename     文件名
//     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
//     *                     例如: excel/temp.xlsx
//     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
//     * @param data         模板需要的数据
//     * @param response     响应体
//     */
//    public static void exportTemplate(List<Object> data, String filename, String templatePath, HttpServletResponse response) {
//        try {
//            resetResponse(filename, response);
//            ServletOutputStream os = response.getOutputStream();
//            exportTemplate(data, templatePath, os);
//        } catch (IOException e) {
//            throw new RuntimeException("导出Excel异常");
//        }
//    }

    /**
     * 使用校验监听器 异步导入 同步返回
     *
     * @param is         输入流
     * @param clazz      对象类型
     * @param isValidate 是否 Validator 检验 默认为是
     * @return 转换后集合
     */
//    public static <T> ExcelResult<T> importExcel(InputStream is, Class<T> clazz, boolean isValidate) {
//        DefaultExcelListener<T> listener = new DefaultExcelListener<>(isValidate);
//        EasyExcel.read(is, clazz, listener).sheet().doRead();
//        return listener.getExcelResult();
//    }

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

//    /**
//     * 单表多数据模板导出 模板格式为 {.属性}
//     *
//     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
//     *                     例如: excel/temp.xlsx
//     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
//     * @param data         模板需要的数据
//     * @param os           输出流
//     */
//    public static void exportTemplate(List<Object> data, String templatePath, OutputStream os) {
//        ClassPathResource templateResource = new ClassPathResource(templatePath);
//        ExcelWriter excelWriter = EasyExcel.write(os)
//                                           .withTemplate(templateResource.getStream())
//                                           .autoCloseStream(false)
//                                           // 大数值自动转换 防止失真
//                                           .registerConverter(new ExcelBigNumberConvert())
//                                           .build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        if (CollectionUtils.isEmpty(data)) {
//            throw new IllegalArgumentException("数据为空");
//        }
//        // 单表多数据导出 模板格式为 {.属性}
//        for (Object d : data) {
//            excelWriter.fill(d, writeSheet);
//        }
//        excelWriter.finish();
//    }

//    /**
//     * 多表多数据模板导出 模板格式为 {key.属性}
//     *
//     * @param filename     文件名
//     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
//     *                     例如: excel/temp.xlsx
//     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
//     * @param data         模板需要的数据
//     * @param response     响应体
//     */
//    public static void exportTemplateMultiList(Map<String, Object> data, String filename, String templatePath, HttpServletResponse response) {
//        try {
//            resetResponse(filename, response);
//            ServletOutputStream os = response.getOutputStream();
//            exportTemplateMultiList(data, templatePath, os);
//        } catch (IOException e) {
//            throw new RuntimeException("导出Excel异常");
//        }
//    }

//    /**
//     * 多表多数据模板导出 模板格式为 {key.属性}
//     *
//     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
//     *                     例如: excel/temp.xlsx
//     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
//     * @param data         模板需要的数据
//     * @param os           输出流
//     */
//    public static void exportTemplateMultiList(Map<String, Object> data, String templatePath, OutputStream os) {
//        ClassPathResource templateResource = new ClassPathResource(templatePath);
//        ExcelWriter excelWriter = EasyExcel.write(os)
//                                           .withTemplate(templateResource.getStream())
//                                           .autoCloseStream(false)
//                                           // 大数值自动转换 防止失真
////                                           .registerConverter(new ExcelBigNumberConvert())
//                                           .build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        if (data.isEmpty()) {
//            throw new IllegalArgumentException("数据为空");
//        }
//        for (Map.Entry<String, Object> map : data.entrySet()) {
//            // 设置列表后续还有数据
//            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
//            if (map.getValue() instanceof Collection) {
//                // 多表导出必须使用 FillWrapper
//                excelWriter.fill(new FillWrapper(map.getKey(), (Collection<?>) map.getValue()), fillConfig, writeSheet);
//            } else {
//                excelWriter.fill(map.getValue(), writeSheet);
//            }
//        }
//        excelWriter.finish();
//    }

    /**
     * 重置响应体
     */
    private static void resetResponse(String sheetName, HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = encodingFilename(sheetName);
        FileNameUtil.setAttachmentResponseHeader(response, filename);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
    }

    /**
     * 编码文件名
     */
    public static String encodingFilename(String filename) {
        return GenerateUtil.generateSimpleUUID() + "_" + filename + ".xlsx";
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
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, int rowIndex, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
            ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
                                                       .autoCloseStream(false)
                                                       // 自动适配
                                                       .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                                                       // 大数值自动转换 防止失真
//                                                       .registerConverter(new ExcelBigNumberConvert())
                                                       .sheet(sheetName);
            if (merge) {
                // 合并处理器
//                builder.registerWriteHandler(new CellMergeStrategy(list, true, rowIndex));
            }
            builder.doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 导出excel
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, int rowIndex, Set<String> excludeColumnFieldNames, HttpServletResponse response) {
        try {
            log.info(JsonUtil.toJsonString(excludeColumnFieldNames));
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
            ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
                                                       .autoCloseStream(false)
                                                       // 自动适配
                                                       .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                                                       // 大数值自动转换 防止失真
//                                                       .registerConverter(new ExcelBigNumberConvert())
                                                       .excludeColumnFieldNames(excludeColumnFieldNames)
                                                       .sheet(sheetName);
//            builder.registerWriteHandler(new CellMergeStrategy(list, true, rowIndex));
            builder.doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }
//
//	public static void main(String[] args) {
//		String path = "D:\home\sijay\Downloads\devicemodel_bangong (1).xls";
//		try {
//			readExcel(path);
//		} catch (InvalidFormatException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 获取excel中的内容
//	 *
//	 * @param path
//	 * @return
//	 * @throws IOException
//	 * @throws InvalidFormatException
//	 */
//	public static List<List<Object>> readExcel(String path) throws IOException, InvalidFormatException {
//		List<List<Object>> list = new ArrayList<>();
//		try {
//			File file = new File(path);
//			Workbook workbook = WorkbookFactory.create(file);
//			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//				Sheet sheet = workbook.getSheetAt(i);
//				for (Row row : sheet) {
//					List<Object> l = new ArrayList<>();
//					for (Cell cell : row) {
//						System.out.println(getCellValue(cell));
//						l.add(getCellValue(cell));
//					}
//					list.add(l);
//				}
//			}
//			workbook.close();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return list;
//	}
//
//	/**
//	 * 向excel写入内容
//	 *
//	 * @param path
//	 * @param filename
//	 * @throws IOException
//	 * @throws InvalidFormatException
//	 */
//	public static void writeExcel(String path, String filename, String sheetName, List<String> titleList, List<List<String>> dataList)
//			throws IOException, InvalidFormatException {
//		File file = new File(path + "/" + filename);
//		FileOutputStream outputStream = new FileOutputStream(file);
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet(sheetName);
//		XSSFRow titleRow = sheet.createRow(0);
//		for (int i = 0; i < titleList.size(); i++) {
//			XSSFCell cell = titleRow.createCell(i);
//			cell.setCellValue(titleList.get(i));
//		}
//		for (int i = 0; i < dataList.size(); i++) {
//			XSSFRow row = sheet.createRow(i);
//			List<String> list = dataList.get(i);
//			for (int j = 0; j < list.size(); j++) {
//				XSSFCell cell = row.createCell(j);
//				cell.setCellValue(list.get(j));
//			}
//		}
//		workbook.write(outputStream);
//		workbook.close();
//		outputStream.flush();
//		outputStream.close();
//	}
//
//	/**
//	 * 获取单元格的值
//	 *
//	 * @param cell
//	 * @return
//	 */
//	private static Object getCellValue(Cell cell) {
//		switch (cell.getCellType()) {
//			case NUMERIC:
//				if (DateUtil.isCellDateFormatted(cell)) {
//					return cell.getDateCellValue();
//				} else {
//					return cell.getNumericCellValue();
//				}
//			case STRING:
//				return cell.getRichStringCellValue()
//						   .getString();
//			case BOOLEAN:
//				return cell.getBooleanCellValue();
//			case FORMULA:
//				return cell.getCellFormula();
//			case ERROR:
//				return cell.getErrorCellValue();
//			default:
//				return cell.getRichStringCellValue()
//						   .toString();
//		}
//	}

}
