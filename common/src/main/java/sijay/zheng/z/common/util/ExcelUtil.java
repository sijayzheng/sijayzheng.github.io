/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

import lombok.NoArgsConstructor;

/**
 * @author sijay
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ExcelUtil {
//
//	public static void main(String[] args) {
//		String path = "D:\\home\\sijay\\Downloads\\devicemodel_bangong (1).xls";
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
