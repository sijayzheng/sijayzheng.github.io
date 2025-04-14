package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemModule;
import cn.sijay.biu.system.service.SystemModuleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemModuleController
 * 系统模块请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemModule")
public class SystemModuleController extends BaseController {
    private final SystemModuleService systemModuleService;

    /**
     * 查询系统模块
     *
     * @param id 主键
     * @return 系统模块
     */
    @GetMapping("/getById")
    public Result<SystemModule> getById(@RequestParam("id") Long id) {
        return success(systemModuleService.getById(id));
    }

    /**
     * 分页查询系统模块列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统模块分页列表
     */
    @GetMapping("/page")
    public Result<SystemModule> page(SystemModule entity, PageQuery pageQuery) {
        return success(systemModuleService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统模块列表
     *
     * @param entity 查询条件
     * @return 系统模块列表
     */
    @GetMapping("/list")
    public Result<SystemModule> list(SystemModule entity) {
        return success(systemModuleService.list(entity));
    }

    /**
     * 保存系统模块
     *
     * @param entity 系统模块
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemModule entity) {
        systemModuleService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统模块信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemModuleService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统模块
     */
    @GetMapping("/export")
    public void export(SystemModule entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemModuleService.list(entity), "系统模块数据", SystemModule.class, response);
    }

    /**
     * 导入系统模块数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemModuleService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemModule.class));
        return success("导入成功");
    }

    /**
     * 获取系统模块导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统模块数据模板", SystemModule.class, response);
    }
}
