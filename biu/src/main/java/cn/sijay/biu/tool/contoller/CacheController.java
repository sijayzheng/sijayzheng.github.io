package cn.sijay.biu.tool.contoller;

import cn.sijay.biu.core.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存监控
 *
 * @author Sijay
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/cache")
public class CacheController extends BaseController {

    private final RedissonConnectionFactory connectionFactory;

    /**
     * 获取缓存监控列表
     */
//    @SaCheckPermission("monitor:cache:list")
//    @GetMapping()
//    public Result<CacheListInfoVo> getInfo() throws Exception {
//        RedisConnection connection = connectionFactory.getConnection();
//        Properties commandStats = connection.commands().info("commandstats");
//
//        List<Map<String, String>> pieList = new ArrayList<>();
//        if (commandStats != null) {
//            commandStats.stringPropertyNames().forEach(key -> {
//                Map<String, String> data = new HashMap<>(2);
//                String property = commandStats.getProperty(key);
//                data.put("name", StringUtil.removeStart(key, "cmdstat_"));
//                data.put("value", StringUtil.substringBetween(property, "calls=", ",usec"));
//                pieList.add(data);
//            });
//        }
//
//        CacheListInfoVo infoVo = new CacheListInfoVo();
//        infoVo.setInfo(connection.commands().info());
//        infoVo.setDbSize(connection.commands().dbSize());
//        infoVo.setCommandStats(pieList);
//        return success(infoVo);
//    }

}
