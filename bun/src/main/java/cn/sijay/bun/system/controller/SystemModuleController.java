package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemModule;
import cn.sijay.bun.system.service.SystemModuleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemModuleController</strong>
 * <p>
 * 系统模块
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemModule> findById(@RequestParam("id") Long id) {
        return success(systemModuleService.findById(id));
    }

    /**
     * 分页查询系统模块列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统模块分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemModule> page(SystemModule entity, PageQuery pageQuery) {
        return systemModuleService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统模块列表
     *
     * @param entity 查询条件
     * @return 系统模块列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemModule>> list(@RequestBody SystemModule entity) {
        return success(systemModuleService.list(entity));
    }

    /**
     * 保存系统模块
     *
     * @param entity 系统模块
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemModule entity) {
        systemModuleService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统模块信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemModuleService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统模块
     */
    @PostMapping("/export")
    public void export(SystemModule entity, HttpServletResponse response) {
        List<SystemModule> list = systemModuleService.list(entity);
        ExcelUtil.exportExcel(list, "系统模块数据", SystemModule.class, response);
    }

    /**
     * 导入系统模块数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemModule> list = ExcelUtil.importExcel(file.getInputStream(), SystemModule.class);
        systemModuleService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统模块导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统模块数据模板", SystemModule.class, response);
    }
}
