package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.sys.entity.SysNotice;
import cn.sijay.suap.sys.service.ISysNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>SysNoticeController</strong>
 * <p>
 * 通知公告控制层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Tag(name = "通知公告")
@RestController
@RequestMapping("/sysNotice")
@RequiredArgsConstructor
public class SysNoticeController extends BaseController {
    private final ISysNoticeService sysNoticeService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public PageResult<SysNotice> page(SysNotice sysNotice, PageQuery pageQuery) {
        return toPageResult(sysNoticeService.page(sysNotice, pageQuery));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<SysNotice> getById(@RequestParam("id") Long id) {
        return success(sysNoticeService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody SysNotice sysNotice) {
        sysNoticeService.save(sysNotice);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        sysNoticeService.remove(ids);
        return success();
    }

    /**
     * 导入
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        sysNoticeService.importData(file);
        return success();
    }

    /**
     * 导出
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(sysNoticeService.list(null), "通知公告", SysNotice.class, response);
    }

    /**
     * 下载通知公告_模板
     */
    @Operation(summary = "下载通知公告_模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("通知公告_模板", SysNotice.class, response);
    }

}
