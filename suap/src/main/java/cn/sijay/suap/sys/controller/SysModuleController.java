package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.sys.entity.SysModule;
import cn.sijay.suap.sys.service.ISysModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>SysModuleController</strong>
 * <p>
 * 模块控制层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Tag(name = "模块")
@RestController
@RequestMapping("/sysModule")
@RequiredArgsConstructor
public class SysModuleController extends BaseController {
    private final ISysModuleService sysModuleService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public PageResult<SysModule> page(SysModule sysModule, PageQuery pageQuery) {
        return toPageResult(sysModuleService.page(sysModule, pageQuery));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<SysModule> getById(@RequestParam("id") Long id) {
        return success(sysModuleService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody SysModule sysModule) {
        sysModuleService.save(sysModule);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        sysModuleService.remove(ids);
        return success();
    }

    /**
     * 导入
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        sysModuleService.importData(file);
        return success();
    }

    /**
     * 导出
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(sysModuleService.list(null), "模块", SysModule.class, response);
    }

    /**
     * 下载模块_模板
     */
    @Operation(summary = "下载模块_模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("模块_模板", SysModule.class, response);
    }

}
