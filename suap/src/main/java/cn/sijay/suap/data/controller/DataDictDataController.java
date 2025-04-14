package cn.sijay.suap.data.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.data.entity.DataDictData;
import cn.sijay.suap.data.service.IDataDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>DataDictDataController</strong>
 * <p>
 * 数据字典项控制层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Tag(name = "数据字典项")
@RestController
@RequestMapping("/dataDictData")
@RequiredArgsConstructor
public class DataDictDataController extends BaseController {
    private final IDataDictDataService dataDictDataService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public PageResult<DataDictData> page(DataDictData dataDictData, PageQuery pageQuery) {
        return toPageResult(dataDictDataService.page(dataDictData, pageQuery));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<DataDictData> getById(@RequestParam("id") Long id) {
        return success(dataDictDataService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody DataDictData dataDictData) {
        dataDictDataService.save(dataDictData);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        dataDictDataService.remove(ids);
        return success();
    }

    /**
     * 导入
     */
    @Operation(summary = "导入")
    @PostMapping("/import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        dataDictDataService.importData(file);
        return success();
    }

    /**
     * 导出
     */
    @Operation(summary = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        ExcelUtil.export(dataDictDataService.list(null), "数据字典项", DataDictData.class, response);
    }

    /**
     * 下载数据字典项_模板
     */
    @Operation(summary = "下载数据字典项_模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("数据字典项_模板", DataDictData.class, response);
    }

}
