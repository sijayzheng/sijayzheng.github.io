package cn.sijay.suap.common.controller;

import cn.sijay.suap.common.service.RouteService;
import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.entity.Route;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <strong>CommonController</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-09-11
 */
@Tag(name = "通用")
@Slf4j
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController extends BaseController {
    private final RouteService routeService;

    /**
     * 获取所有路由
     */
    @GetMapping("/getRoutes")
    public Res<List<Route>> getRoutes() {
        return success(routeService.getRoutes());
    }
}
