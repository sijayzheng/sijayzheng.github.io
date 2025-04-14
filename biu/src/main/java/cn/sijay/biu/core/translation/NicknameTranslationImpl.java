package cn.sijay.biu.core.translation;

import cn.sijay.biu.core.annotation.TranslationType;
import cn.sijay.biu.core.constant.TransConstant;
import lombok.AllArgsConstructor;

/**
 * 用户名称翻译实现
 *
 * @author Sijay
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NICKNAME)
public class NicknameTranslationImpl implements TranslationInterface<String> {

//    private final SystemUserService userService;

    @Override
    public String translation(Object key, String other) {
//        if (key instanceof Long id) {
//            return userService.selectNicknameByIds(id.toString());
//        } else if (key instanceof String ids) {
//            return userService.selectNicknameByIds(ids);
//        }
        return "";
    }
}
