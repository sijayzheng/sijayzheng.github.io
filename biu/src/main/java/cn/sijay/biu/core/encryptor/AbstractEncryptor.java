package cn.sijay.biu.core.encryptor;

import cn.sijay.biu.core.entity.EncryptContext;

/**
 * 所有加密执行者的基类
 *
 * @author Sijay
 * @version 4.6.0
 */
public abstract class AbstractEncryptor implements IEncryptor {

    public AbstractEncryptor(EncryptContext context) {
        // 用户配置校验与配置注入
    }

}
