package cn.sijay.suap.data.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.data.entity.DataDictType;
import cn.sijay.suap.data.service.IDataDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>DataDictTypeController</strong>
 * <p>
 * 数据字典类型控制层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Tag(name = "数据字典类型")
@RestController
@RequestMapping("/dataDictType")
@RequiredArgsConstructor
public class DataDictTypeController extends BaseController {
    private final IDataDictTypeService dataDictTypeService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public PageResult<DataDictType> page(DataDictType dataDictType, PageQuery pageQuery) {
        return toPageResult(dataDictTypeService.page(dataDictType, pageQuery));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<DataDictType> getById(@RequestParam("id") Long id) {
        return success(dataDictTypeService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody DataDictType dataDictType) {
        dataDictTypeService.save(dataDictType);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        dataDictTypeService.remove(ids);
        return success();
    }

    /**
     * 导入
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        dataDictTypeService.importData(file);
        return success();
    }

    /**
     * 导出
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(dataDictTypeService.list(null), "数据字典类型", DataDictType.class, response);
    }

    /**
     * 下载数据字典类型_模板
     */
    @Operation(summary = "下载数据字典类型_模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("数据字典类型_模板", DataDictType.class, response);
    }

}
