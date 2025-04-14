package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.sys.entity.SysConfig;
import cn.sijay.suap.sys.service.ISysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>SysConfigController</strong>
 * <p>
 * 系统配置控制层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Tag(name = "系统配置")
@RestController
@RequestMapping("/sysConfig")
@RequiredArgsConstructor
public class SysConfigController extends BaseController {
    private final ISysConfigService sysConfigService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public PageResult<SysConfig> page(SysConfig sysConfig, PageQuery pageQuery) {
        return toPageResult(sysConfigService.page(sysConfig, pageQuery));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<SysConfig> getById(@RequestParam("id") Long id) {
        return success(sysConfigService.getById(id));
    }

    /**
     * 根据配置编码查询系统配置
     *
     * @param code 配置编码
     */
    @Operation(summary = "根据配置编码查询系统配置")
    @GetMapping(value = "/getByCode")
    public Res<String> getByCode(@RequestParam("code") String code) {
        return success("操作成功", sysConfigService.getByCode(code)
                                                   .getValue());
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody SysConfig sysConfig) {
        sysConfigService.save(sysConfig);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        sysConfigService.remove(ids);
        return success();
    }

    /**
     * 导入
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        sysConfigService.importData(file);
        return success();
    }

    /**
     * 导出
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(sysConfigService.list(null), "系统配置", SysConfig.class, response);
    }

    /**
     * 下载系统配置_模板
     */
    @Operation(summary = "下载系统配置_模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("系统配置_模板", SysConfig.class, response);
    }

    /**
     * 刷新参数缓存
     */
    @Operation(summary = "刷新参数缓存")
    @PostMapping("/refreshCache")
    public Res<Void> refreshCache() {
        sysConfigService.resetConfigCache();
        return success();
    }

}
