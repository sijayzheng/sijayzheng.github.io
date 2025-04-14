package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.sys.entity.SysMenu;
import cn.sijay.suap.sys.service.ISysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <strong>SysMenuController</strong>
 * <p>
 * 菜单控制层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Tag(name = "菜单")
@RestController
@RequestMapping("/sysMenu")
@RequiredArgsConstructor
public class SysMenuController extends BaseController {
    private final ISysMenuService sysMenuService;

    /**
     * 查询所有记录
     */
    @Operation(summary = "查询所有记录")
    @GetMapping("/listTree")
    public Res<List<SysMenu>> listTree(SysMenu sysMenu) {
        return success(sysMenuService.listTree(sysMenu));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<SysMenu> getById(@RequestParam("id") Long id) {
        return success(sysMenuService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        sysMenuService.remove(ids);
        return success();
    }

}
