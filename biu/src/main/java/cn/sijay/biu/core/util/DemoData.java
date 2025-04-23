package cn.sijay.demos.util;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty(index = 0)
    private String a;
    @ExcelProperty(index = 1)
    private String b;
    @ExcelProperty(index = 2)
    private String c;
    @ExcelProperty(index = 3)
    private String d;
}
