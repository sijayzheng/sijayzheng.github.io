package cn.sijay.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

/**
 * <strong>ExcelUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-06
 */
public class ExcelUtil {
    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            String fileName = "C:\\Users\\sijay\\Desktop\\经营指标统计表.xlsx";
            try (ExcelReader excelReader = EasyExcel.read(fileName).build()) {
                ReadSheet readSheet1 = EasyExcel.readSheet(i).head(DemoData.class).registerReadListener(new DemoDataListener(i)).build();
                excelReader.read(readSheet1);
            }
        }

    }

}

