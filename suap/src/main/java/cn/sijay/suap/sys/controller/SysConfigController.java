package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.annotation.Log;
import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.Ids;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Result;
import cn.sijay.suap.core.enums.OperateType;
import cn.sijay.suap.core.util.ExcelUtil;
import cn.sijay.suap.sys.entity.SysConfig;
import cn.sijay.suap.sys.service.ISysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * <em>SysConfigController 系统配置控制层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Tag(name = "系统配置", description = "/sysConfig")
@RequiredArgsConstructor
@RestController
@RequestMapping("sysConfig")
public class SysConfigController extends BaseController {
    private final ISysConfigService sysConfigService;

    /**
     * 根据id查询
     *
     * @param id id
     * @return 系统配置详细信息
     */
    @Log(value = "根据id查询", operateType = OperateType.SELECT)
    @Operation(summary = "根据id查询")
    @Parameter(name = "id", description = "id", required = true)
    @ApiResponse(responseCode = "200", description = "系统配置详细信息")
    @GetMapping("getById")
    public Result<SysConfig> getById(@RequestParam("id") Long id) {
        return success(sysConfigService.getById(id));
    }

    /**
     * 查询所有记录
     *
     * @return 系统配置信息列表，如果没有记录，则返回空列表。
     */
    @Log(value = "查询所有记录", operateType = OperateType.SELECT)
    @Operation(summary = "查询所有记录")
    @ApiResponse(responseCode = "200", description = "系统配置信息列表，如果没有记录，则返回空列表。")
    @GetMapping("listAll")
    public Result<List<SysConfig>> listAll() {
        return success(sysConfigService.list());
    }

    /**
     * 分页查询
     *
     * @param sysConfig 查询条件，可以为空。如果为空，则查询所有记录。
     * @param pageQuery 分页参数，可以为空。如果为空，则查询所有记录。
     * @return 系统配置信息列表，如果没有记录，则返回空列表。
     */
    @Log(value = "分页查询", operateType = OperateType.SELECT)
    @Operation(summary = "分页查询")
    @Parameter(name = "pageQuery", description = "分页查询条件，可以为空。如果为空，则查询所有记录。")
    @ApiResponse(responseCode = "200", description = "系统配置信息列表，如果没有记录，则返回空列表。")
    @GetMapping("page")
    public PageResult<SysConfig> page(SysConfig sysConfig, PageQuery pageQuery) {
        return toPageResult(sysConfigService.page(sysConfig, pageQuery));
    }

    /**
     * 添加
     *
     * @param sysConfig 添加的系统配置信息，不能为空。如果为空，则添加失败。
     * @return 是否添加成功
     */
    @Log(value = "添加", operateType = OperateType.CREATE)
    @Operation(summary = "添加")
    @Parameter(name = "sysConfig", description = "添加的系统配置信息，不能为空。如果为空，则添加失败。")
    @ApiResponse(responseCode = "200", description = "是否添加成功")
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody SysConfig sysConfig) {
        return toBoolean(sysConfigService.add(sysConfig), OperateType.CREATE);
    }

    /**
     * 修改
     *
     * @param sysConfig 修改的系统配置信息，不能为空。如果为空，则添加失败。
     * @return 是否修改成功
     */
    @Log(value = "修改", operateType = OperateType.MODIFY)
    @Operation(summary = "修改")
    @Parameter(name = "sysConfig", description = "修改的系统配置信息，不能为空。如果为空，则添加失败。")
    @ApiResponse(responseCode = "200", description = "是否修改成功")
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody SysConfig sysConfig) {
        return toBoolean(sysConfigService.update(sysConfig), OperateType.MODIFY);
    }

    /**
     * 删除
     *
     * @param ids 要删除的系统配置id，不能为空。如果为空，则删除失败。
     * @return 是否删除成功
     */
    @Log(value = "删除", operateType = OperateType.REMOVE)
    @Operation(summary = "删除")
    @Parameter(name = "ids", description = "要删除的系统配置id，不能为空。如果为空，则删除失败。")
    @ApiResponse(responseCode = "200", description = "是否删除成功")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody Ids<Long> ids) {
        return toBoolean(sysConfigService.remove(ids), OperateType.REMOVE);
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    @Log(value = "导入", operateType = OperateType.IMPORT)
    @Operation(summary = "导入")
    @Parameter(name = "file", description = "要导入的excel文件")
    @ApiResponse(responseCode = "200", description = "是否导入成功")
    @PostMapping("import")
    public Result<Void> importData(@RequestPart("file") MultipartFile file) {
        return success(sysConfigService.importData(file));
    }

    /**
     * 导出
     */
    @Log(value = "导出", operateType = OperateType.EXPORT)
    @Operation(summary = "导出")
    @ApiResponse(responseCode = "200", description = "是否导出成功")
    @PostMapping("export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(sysConfigService.list(), "系统配置", SysConfig.class, response);
    }

    /**
     * 下载系统配置_数据模板
     */
    @Log(value = "下载系统配置_数据模板", operateType = OperateType.DOWNLOAD)
    @Operation(summary = "下载系统配置_数据模板")
    @ApiResponse(responseCode = "200", description = "是否下载成功")
    @PostMapping("downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("系统配置_数据模板", SysConfig.class, response);
    }

}
