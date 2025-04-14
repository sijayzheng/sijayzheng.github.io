package cn.sijay.biu.tool.contoller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.biu.core.annotation.Log;
import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.constant.CacheConstants;
import cn.sijay.biu.core.entity.OnlineUser;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.enums.BusinessType;
import cn.sijay.biu.core.util.RedisUtil;
import cn.sijay.biu.core.util.StreamUtil;
import cn.sijay.biu.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在线用户监控
 *
 * @author Sijay
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/online")
public class UserOnlineController extends BaseController {

    /**
     * 获取在线用户监控列表
     *
     * @param userName 用户名
     */
    @SaCheckPermission("monitor:online:list")
    @GetMapping("/list")
    public Result<OnlineUser> list(String userName) {
        // 获取所有未过期的 token
        List<String> keys = StpUtil.searchTokenValue("", 0, -1, false);
        List<OnlineUser> onlineUserList = new ArrayList<>();
        for (String key : keys) {
            String token = StringUtils.substringAfterLast(key, ":");
            // 如果已经过期则跳过
            if (StpUtil.stpLogic.getTokenActiveTimeoutByToken(token) < -1) {
                continue;
            }
            onlineUserList.add(RedisUtil.get(CacheConstants.ONLINE_TOKEN_KEY + token));
        }
        if (StringUtil.isNotEmpty(userName)) {
            onlineUserList = StreamUtil.filter(onlineUserList, userOnline ->
                    StringUtil.equals(userName, userOnline.getUserName())
            );
        }
        Collections.reverse(onlineUserList);
        onlineUserList.removeAll(Collections.singleton(null));
        return success(onlineUserList);
    }

    /**
     * 强退用户
     *
     * @param tokenId token值
     */
    @SaCheckPermission("monitor:online:forceLogout")
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public Result<String> forceLogout(@PathVariable String tokenId) {
        try {
            StpUtil.kickoutByTokenValue(tokenId);
        } catch (NotLoginException ignored) {
        }
        return success();
    }

    /**
     * 获取当前用户登录在线设备
     */
    @GetMapping()
    public Result<OnlineUser> getInfo() {
        // 获取指定账号 id 的 token 集合
        List<OnlineUser> onlineUserList = StpUtil.getTokenValueListByLoginId(StpUtil.getLoginIdAsString())
                                                 .stream()
                                                 .filter(token -> StpUtil.stpLogic.getTokenActiveTimeoutByToken(token) >= -1)
                                                 .map(token -> (OnlineUser) RedisUtil.get(CacheConstants.ONLINE_TOKEN_KEY + token))
                                                 .collect(Collectors.toList());
        //复制和处理 OnlineUser 对象列表
        Collections.reverse(onlineUserList);
        onlineUserList.removeAll(Collections.singleton(null));
        return success(onlineUserList);
    }

    /**
     * 强退当前在线设备
     *
     * @param tokenId token值
     */
    @Log(title = "在线设备", businessType = BusinessType.FORCE)
    @PostMapping("/{tokenId}")
    public Result<String> remove(@PathVariable("tokenId") String tokenId) {
        try {
            // 获取指定账号 id 的 token 集合
            StpUtil.getTokenValueListByLoginId(StpUtil.getLoginIdAsString())
                   .stream()
                   .filter(key -> key.equals(tokenId))
                   .findFirst()
                   .ifPresent(key -> StpUtil.kickoutByTokenValue(tokenId));
        } catch (NotLoginException ignored) {
        }
        return success();
    }

}
