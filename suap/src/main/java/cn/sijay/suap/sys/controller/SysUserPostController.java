package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.sys.service.ISysUserPostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <em>SysUserPostController 用户岗位控制层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Tag(name = "用户岗位", description = "/sysUserPost")
@RequiredArgsConstructor
@RestController
@RequestMapping("sysUserPost")
public class SysUserPostController extends BaseController {
    private final ISysUserPostService sysUserPostService;

}
