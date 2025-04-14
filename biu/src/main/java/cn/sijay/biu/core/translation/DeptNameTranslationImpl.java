package cn.sijay.biu.core.translation;

import cn.sijay.biu.core.annotation.TranslationType;
import cn.sijay.biu.core.constant.TransConstant;
import lombok.AllArgsConstructor;

/**
 * 部门翻译实现
 *
 * @author Sijay
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    //    private final SystemDeptService deptService;
//
    @Override
    public String translation(Object key, String other) {
//        if (key instanceof String ids) {
//            return deptService.selectDeptNameByIds(ids);
//        } else if (key instanceof Long id) {
//            return deptService.selectDeptNameByIds(id.toString());
//        }
        return "";
    }
}
