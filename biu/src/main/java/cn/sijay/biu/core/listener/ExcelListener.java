package cn.sijay.biu.core.listener;

import cn.sijay.biu.core.entity.ExcelResult;
import com.alibaba.excel.read.listener.ReadListener;

/**
 * Excel 导入监听
 *
 * @author Sijay
 */
public interface ExcelListener<T> extends ReadListener<T> {

    ExcelResult<T> getExcelResult();

}
