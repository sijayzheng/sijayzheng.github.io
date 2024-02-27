package cn.sijay.suap.gen.controller;

import cn.sijay.suap.core.annotation.Log;
import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.*;
import cn.sijay.suap.core.enums.OperateType;
import cn.sijay.suap.core.properties.GenProperties;
import cn.sijay.suap.gen.entity.GenTable;
import cn.sijay.suap.gen.entity.GenTableColumn;
import cn.sijay.suap.gen.service.GenService;
import cn.sijay.suap.gen.service.IGenTableColumnService;
import cn.sijay.suap.gen.service.IGenTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * <em>GenTableColumnController</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 17:23
 */
@Tag(name = "代码生成", description = "/gen")
@RequiredArgsConstructor
@RestController
@RequestMapping("gen")
public class GenController extends BaseController {
    private final GenService genService;
    private final IGenTableService genTableService;
    private final IGenTableColumnService genTableColumnService;
    private final GenProperties genProperties;

    /**
     * 获取代码生成的参数信息
     */
    @Log(value = "获取代码生成的参数信息", operateType = OperateType.SELECT)
    @Operation(summary = "获取代码生成的参数信息")
    @ApiResponse(responseCode = "200", description = "获取代码生成的参数信息")
    @GetMapping("getGenProperties")
    public Result<GenProperties> getGenProperties() {
        return success(genProperties);
    }

    /**
     * 保存
     */
    @Log(value = "保存", operateType = OperateType.CREATE)
    @Operation(summary = "保存")
    @ApiResponse(responseCode = "200", description = "保存")
    @PostMapping("save")
    public Result<Pair<String, Long>> save(@RequestBody GenTable genTable) {
        return success(genService.save(genTable));
    }

    /**
     * 更新
     */
    @Log(value = "更新", operateType = OperateType.CREATE)
    @Operation(summary = "更新")
    @ApiResponse(responseCode = "200", description = "更新")
    @PostMapping("update")
    public Result<Pair<String, Long>> update(@RequestBody GenTable genTable) {
        return success(genService.update(genTable));
    }

    /**
     * 预览代码
     */
    @Log(value = "预览代码", operateType = OperateType.OTHER)
    @Operation(summary = "预览代码")
    @ApiResponse(responseCode = "200", description = "预览代码")
    @GetMapping("preview")
    public Result<HashMap<String, String>> preview(@RequestParam("id") Long id) {
        return success(genService.preview(id));
    }

    /**
     * 生成代码
     */
    @Log(value = "生成代码", operateType = OperateType.OTHER)
    @Operation(summary = "生成代码")
    @ApiResponse(responseCode = "200", description = "生成代码")
    @GetMapping("generate")
    public Result<Void> generate(@RequestParam("id") @Nullable Long id) {
        genService.generate(id);
        return success();
    }

    /**
     * 下载代码
     */
    @Log(value = "下载代码", operateType = OperateType.OTHER)
    @Operation(summary = "下载代码")
    @ApiResponse(responseCode = "200", description = "下载代码")
    @PostMapping("download")
    public void download(@RequestBody Ids<Long> id, HttpServletResponse response) {
        genService.download(id.getId(), response);
    }

    /**
     * 获取表的列信息
     */
    @Log(value = "获取表的列信息", operateType = OperateType.SELECT)
    @Operation(summary = "获取表的列信息")
    @ApiResponse(responseCode = "200", description = "表的列信息")
    @GetMapping("listTableColumns")
    public Result<List<GenTableColumn>> listTableColumns(@RequestParam("tableName") String tableName, @RequestParam("superClass") @Nullable String superClass) {
        return success(genService.listTableColumns(tableName, superClass));
    }

    /**
     * 根据tableId查询
     *
     * @param tableId tableId
     * @return 列信息详细信息
     */
    @Log(value = "根据tableId查询", operateType = OperateType.SELECT)
    @Operation(summary = "根据tableId查询")
    @Parameter(name = "tableId", description = "tableId", required = true)
    @ApiResponse(responseCode = "200", description = "列信息详细信息")
    @GetMapping("listColumnByTableId")
    public Result<List<GenTableColumn>> listColumnByTableId(@RequestParam("tableId") Long tableId) {
        return success(genService.listColumnByTableId(tableId));
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 表信息详细信息
     */
    @Log(value = "根据id查询", operateType = OperateType.SELECT)
    @Operation(summary = "根据id查询")
    @Parameter(name = "id", description = "id", required = true)
    @ApiResponse(responseCode = "200", description = "表信息详细信息")
    @GetMapping("getTableById")
    public Result<GenTable> getTableById(@RequestParam("id") Long id) {
        return success(genTableService.getById(id));
    }

    /**
     * 分页查询
     *
     * @param genTable  查询条件，可以为空。如果为空，则查询所有记录。
     * @param pageQuery 分页参数，可以为空。如果为空，则查询所有记录。
     * @return 表信息信息列表，如果没有记录，则返回空列表。
     */
    @Log(value = "分页查询", operateType = OperateType.SELECT)
    @Operation(summary = "分页查询")
    @Parameter(name = "pageQuery", description = "分页查询条件，可以为空。如果为空，则查询所有记录。")
    @ApiResponse(responseCode = "200", description = "表信息信息列表，如果没有记录，则返回空列表。")
    @GetMapping("page")
    public PageResult<GenTable> page(GenTable genTable, PageQuery pageQuery) {
        return toPageResult(genTableService.page(genTable, pageQuery));
    }

    /**
     * 删除
     *
     * @param ids 要删除的表信息id，不能为空。如果为空，则删除失败。
     * @return 是否删除成功
     */
    @Log(value = "删除", operateType = OperateType.REMOVE)
    @Operation(summary = "删除")
    @Parameter(name = "idDto", description = "要删除的表信息id，不能为空。如果为空，则删除失败。")
    @ApiResponse(responseCode = "200", description = "是否删除成功")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody Ids<Long> ids) {
        return toBoolean(genTableService.remove(ids), OperateType.REMOVE);
    }

    /**
     * 获取所有要添加的表信息
     *
     * @return
     */
    @Log(value = "获取所有要添加的表信息", operateType = OperateType.DOWNLOAD)
    @Operation(summary = "获取所有要添加的表信息")
    @ApiResponse(responseCode = "200", description = "所有要添加的表信息")
    @GetMapping("listTable")
    public Result<List<GenTable>> listTable() {
        return success(genTableService.listAllTable());
    }
}
