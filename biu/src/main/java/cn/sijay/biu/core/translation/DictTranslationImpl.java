package cn.sijay.biu.core.translation;

import cn.sijay.biu.core.annotation.TranslationType;
import cn.sijay.biu.core.constant.TransConstant;
import lombok.AllArgsConstructor;

/**
 * 字典翻译实现
 *
 * @author Sijay
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTranslationImpl implements TranslationInterface<String> {

//    private final SystemDictService dictService;

    @Override
    public String translation(Object key, String other) {
//        if (key instanceof String dictValue && StringUtil.isNotBlank(other)) {
//            return dictService.getDictLabel(other, dictValue);
//        }
        return "";
    }
}
