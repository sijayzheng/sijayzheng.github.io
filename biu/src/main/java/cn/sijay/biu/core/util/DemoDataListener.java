package cn.sijay.demos.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.apache.commons.lang3.StringUtils;

/**
 * <strong>DemoDataListener</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-06
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class DemoDataListener implements ReadListener<DemoData> {
    private final Integer type;

    public DemoDataListener(int i) {
        type = i;
    }

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println("insert into oa_cash_journal (contract_code,creator,amount,date,type)value('"
                + StringUtils.defaultIfBlank(data.getA(), "")
                + "','"
                + StringUtils.defaultIfBlank(data.getB(), "")
                + "','"
                + StringUtils.defaultIfBlank(data.getC(), "0")
                + "',"
                + (StringUtils.isBlank(data.getD()) ? "null" : "'" + data.getD() + "'")
                + ","
                + (type + 1)
                + ");");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

}
