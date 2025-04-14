package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.sys.entity.SysUser;
import cn.sijay.suap.sys.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>SysUserController</strong>
 * <p>
 * 用户控制层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor
public class SysUserController extends BaseController {
    private final ISysUserService sysUserService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public PageResult<SysUser> page(SysUser sysUser, PageQuery pageQuery) {
        return toPageResult(sysUserService.page(sysUser, pageQuery));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<SysUser> getById(@RequestParam("id") Long id) {
        return success(sysUserService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        sysUserService.remove(ids);
        return success();
    }

    /**
     * 导入
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        sysUserService.importData(file);
        return success();
    }

    /**
     * 导出
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(sysUserService.list(null), "用户", SysUser.class, response);
    }

    /**
     * 下载用户_模板
     */
    @Operation(summary = "下载用户_模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("用户_模板", SysUser.class, response);
    }

    /**
     * 获取当前登录用户信息
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/getUserInfo")
    public Res<SysUser> getUserInfo() {
        return success(sysUserService.getUserInfo());
    }

}
