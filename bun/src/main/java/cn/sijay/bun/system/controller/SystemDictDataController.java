package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemDictData;
import cn.sijay.bun.system.service.SystemDictDataService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemDictDataController</strong>
 * <p>
 * 系统字典数据
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemDictData")
public class SystemDictDataController extends BaseController {
    private final SystemDictDataService systemDictDataService;

    /**
     * 查询系统字典数据
     *
     * @param id 主键
     * @return 系统字典数据
     */
    @GetMapping("/findById")
    public ResponseResult<SystemDictData> findById(@RequestParam("id") Long id) {
        return success(systemDictDataService.findById(id));
    }

    /**
     * 分页查询系统字典数据列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典数据分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemDictData> page(SystemDictData entity, PageQuery pageQuery) {
        return systemDictDataService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统字典数据列表
     *
     * @param entity 查询条件
     * @return 系统字典数据列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemDictData>> list(@RequestBody SystemDictData entity) {
        return success(systemDictDataService.list(entity));
    }

    /**
     * 保存系统字典数据
     *
     * @param entity 系统字典数据
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemDictData entity) {
        systemDictDataService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统字典数据信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemDictDataService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统字典数据
     */
    @PostMapping("/export")
    public void export(SystemDictData entity, HttpServletResponse response) {
        List<SystemDictData> list = systemDictDataService.list(entity);
        ExcelUtil.exportExcel(list, "系统字典数据数据", SystemDictData.class, response);
    }

    /**
     * 导入系统字典数据数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemDictData> list = ExcelUtil.importExcel(file.getInputStream(), SystemDictData.class);
        systemDictDataService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统字典数据导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统字典数据数据模板", SystemDictData.class, response);
    }
}
