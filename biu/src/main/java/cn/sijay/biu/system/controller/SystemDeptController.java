package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemDept;
import cn.sijay.biu.system.service.SystemDeptService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemDeptController
 * 系统部门请求控制层
 *
 * @author Sijay
 * @since 2025-04-09
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemDept")
public class SystemDeptController extends BaseController {
    private final SystemDeptService systemDeptService;

    /**
     * 查询系统部门
     *
     * @param id 主键
     * @return 系统部门
     */
    @GetMapping("/getById")
    public Result<SystemDept> getById(@RequestParam("id") Long id) {
        return success(systemDeptService.getById(id));
    }

    /**
     * 分页查询系统部门列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统部门分页列表
     */
    @GetMapping("/page")
    public Result<SystemDept> page(SystemDept entity, PageQuery pageQuery) {
        return success(systemDeptService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统部门列表
     *
     * @param entity 查询条件
     * @return 系统部门列表
     */
    @GetMapping("/list")
    public Result<SystemDept> list(SystemDept entity) {
        return success(systemDeptService.list(entity));
    }

    /**
     * 保存系统部门
     *
     * @param entity 系统部门
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemDept entity) {
        systemDeptService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统部门信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemDeptService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统部门
     */
    @GetMapping("/export")
    public void export(SystemDept entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemDeptService.list(entity), "系统部门数据", SystemDept.class, response);
    }

    /**
     * 导入系统部门数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemDeptService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemDept.class));
        return success("导入成功");
    }

    /**
     * 获取系统部门导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统部门数据模板", SystemDept.class, response);
    }
}
