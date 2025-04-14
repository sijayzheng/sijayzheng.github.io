package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemDictData;
import cn.sijay.biu.system.service.SystemDictDataService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemDictDataController
 * 系统字典数据请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
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
    @GetMapping("/getById")
    public Result<SystemDictData> getById(@RequestParam("id") Long id) {
        return success(systemDictDataService.getById(id));
    }

    /**
     * 分页查询系统字典数据列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典数据分页列表
     */
    @GetMapping("/page")
    public Result<SystemDictData> page(SystemDictData entity, PageQuery pageQuery) {
        return success(systemDictDataService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统字典数据列表
     *
     * @param entity 查询条件
     * @return 系统字典数据列表
     */
    @GetMapping("/list")
    public Result<SystemDictData> list(SystemDictData entity) {
        return success(systemDictDataService.list(entity));
    }

    /**
     * 保存系统字典数据
     *
     * @param entity 系统字典数据
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemDictData entity) {
        systemDictDataService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统字典数据信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemDictDataService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统字典数据
     */
    @GetMapping("/export")
    public void export(SystemDictData entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemDictDataService.list(entity), "系统字典数据数据", SystemDictData.class, response);
    }

    /**
     * 导入系统字典数据数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemDictDataService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemDictData.class));
        return success("导入成功");
    }

    /**
     * 获取系统字典数据导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统字典数据数据模板", SystemDictData.class, response);
    }
}
