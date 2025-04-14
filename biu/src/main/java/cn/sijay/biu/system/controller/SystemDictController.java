package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemDict;
import cn.sijay.biu.system.service.SystemDictService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemDictController
 * 系统字典请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemDict")
public class SystemDictController extends BaseController {
    private final SystemDictService systemDictService;

    /**
     * 查询系统字典
     *
     * @param id 主键
     * @return 系统字典
     */
    @GetMapping("/getById")
    public Result<SystemDict> getById(@RequestParam("id") Long id) {
        return success(systemDictService.getById(id));
    }

    /**
     * 分页查询系统字典列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典分页列表
     */
    @GetMapping("/page")
    public Result<SystemDict> page(SystemDict entity, PageQuery pageQuery) {
        return success(systemDictService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统字典列表
     *
     * @param entity 查询条件
     * @return 系统字典列表
     */
    @GetMapping("/list")
    public Result<SystemDict> list(SystemDict entity) {
        return success(systemDictService.list(entity));
    }

    /**
     * 保存系统字典
     *
     * @param entity 系统字典
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemDict entity) {
        systemDictService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统字典信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemDictService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统字典
     */
    @GetMapping("/export")
    public void export(SystemDict entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemDictService.list(entity), "系统字典数据", SystemDict.class, response);
    }

    /**
     * 导入系统字典数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemDictService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemDict.class));
        return success("导入成功");
    }

    /**
     * 获取系统字典导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统字典数据模板", SystemDict.class, response);
    }
}
