package cn.sijay.biu.core.constant;

/**
 * 缓存组名称常量
 * <p>
 * key 格式为 cacheNames#ttl#maxIdleTime#maxSize
 * <p>
 * ttl 过期时间 如果设置为0则不过期 默认为0
 * maxIdleTime 最大空闲时间 根据LRU算法清理空闲数据 如果设置为0则不检测 默认为0
 * maxSize 组最大长度 根据LRU算法清理溢出数据 如果设置为0则无限长 默认为0
 * <p>
 * 例子: test#60s、test#0#60s、test#0#1m#1000、test#1h#0#500
 *
 * @author Sijay
 */
public interface CacheNames {

    /**
     * 系统配置
     */
    String SYS_CONFIG = "sys_config";

    /**
     * 数据字典
     */
    String SYS_DICT = "sys_dict";

    /**
     * 用户账户
     */
    String SYS_USER_NAME = "sys_user_name#30d";

    /**
     * 用户名称
     */
    String SYS_NICKNAME = "sys_nickname#30d";

    /**
     * 部门
     */
    String SYS_DEPT = "sys_dept#30d";

    /**
     * 在线用户
     */
    String ONLINE_TOKEN = "online_tokens";

}
