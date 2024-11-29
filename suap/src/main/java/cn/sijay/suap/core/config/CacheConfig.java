package cn.sijay.suap.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * <strong>CacheConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@AutoConfiguration
@EnableCaching
public class CacheConfig {

//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Bean
//    public RedissonAutoConfigurationCustomizer redissonCustomizer() {
//        return config -> {
//            ObjectMapper om = objectMapper.copy();
//            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//            // 指定序列化输入的类型，类必须是非final修饰的。序列化时将对象全类名一起保存下来
//            om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
//            TypedJsonJacksonCodec jsonCodec = new TypedJsonJacksonCodec(Object.class, om);
//            // 组合序列化 key 使用 String 内容使用通用 json 格式
//            CompositeCodec codec = new CompositeCodec(StringCodec.INSTANCE, jsonCodec, jsonCodec);
//            config.setThreads(redissonProperties.getThreads())
//                  .setNettyThreads(redissonProperties.getNettyThreads())
//                  // 缓存 Lua 脚本 减少网络传输(redisson 大部分的功能都是基于 Lua 脚本实现)
//                  .setUseScriptCache(true)
//                  .setCodec(codec);
//            RedissonProperties.SingleServerConfig singleServerConfig = redissonProperties.getSingleServerConfig();
//            if (ObjectUtil.isNotNull(singleServerConfig)) {
//                // 使用单机模式
//                config.useSingleServer()
//                      //设置redis key前缀
//                      .setNameMapper(new KeyPrefixHandler(redissonProperties.getKeyPrefix()))
//                      .setTimeout(singleServerConfig.getTimeout())
//                      .setClientName(singleServerConfig.getClientName())
//                      .setIdleConnectionTimeout(singleServerConfig.getIdleConnectionTimeout())
//                      .setSubscriptionConnectionPoolSize(singleServerConfig.getSubscriptionConnectionPoolSize())
//                      .setConnectionMinimumIdleSize(singleServerConfig.getConnectionMinimumIdleSize())
//                      .setConnectionPoolSize(singleServerConfig.getConnectionPoolSize());
//            }
//            // 集群配置方式 参考下方注释
//            RedissonProperties.ClusterServersConfig clusterServersConfig = redissonProperties.getClusterServersConfig();
//            if (ObjectUtil.isNotNull(clusterServersConfig)) {
//                config.useClusterServers()
//                      //设置redis key前缀
//                      .setNameMapper(new KeyPrefixHandler(redissonProperties.getKeyPrefix()))
//                      .setTimeout(clusterServersConfig.getTimeout())
//                      .setClientName(clusterServersConfig.getClientName())
//                      .setIdleConnectionTimeout(clusterServersConfig.getIdleConnectionTimeout())
//                      .setSubscriptionConnectionPoolSize(clusterServersConfig.getSubscriptionConnectionPoolSize())
//                      .setMasterConnectionMinimumIdleSize(clusterServersConfig.getMasterConnectionMinimumIdleSize())
//                      .setMasterConnectionPoolSize(clusterServersConfig.getMasterConnectionPoolSize())
//                      .setSlaveConnectionMinimumIdleSize(clusterServersConfig.getSlaveConnectionMinimumIdleSize())
//                      .setSlaveConnectionPoolSize(clusterServersConfig.getSlaveConnectionPoolSize())
//                      .setReadMode(clusterServersConfig.getReadMode())
//                      .setSubscriptionMode(clusterServersConfig.getSubscriptionMode());
//            }
//            log.info("初始化 redis 配置");
//        };
//    }
//
//    /**
//     * 自定义缓存管理器 整合spring-cache
//     */
//    @Bean
//    public CacheManager cacheManager() {
//        return new PlusSpringCacheManager();
//    }

}

