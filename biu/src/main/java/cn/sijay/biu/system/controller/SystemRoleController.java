package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemRole;
import cn.sijay.biu.system.service.SystemRoleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemRoleController
 * 系统权限请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemRole")
public class SystemRoleController extends BaseController {
    private final SystemRoleService systemRoleService;

    /**
     * 查询系统权限
     *
     * @param id 主键
     * @return 系统权限
     */
    @GetMapping("/getById")
    public Result<SystemRole> getById(@RequestParam("id") Long id) {
        return success(systemRoleService.getById(id));
    }

    /**
     * 分页查询系统权限列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统权限分页列表
     */
    @GetMapping("/page")
    public Result<SystemRole> page(SystemRole entity, PageQuery pageQuery) {
        return success(systemRoleService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统权限列表
     *
     * @param entity 查询条件
     * @return 系统权限列表
     */
    @GetMapping("/list")
    public Result<SystemRole> list(SystemRole entity) {
        return success(systemRoleService.list(entity));
    }

    /**
     * 保存系统权限
     *
     * @param entity 系统权限
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemRole entity) {
        systemRoleService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统权限信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemRoleService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统权限
     */
    @GetMapping("/export")
    public void export(SystemRole entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemRoleService.list(entity), "系统权限数据", SystemRole.class, response);
    }

    /**
     * 导入系统权限数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemRoleService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemRole.class));
        return success("导入成功");
    }

    /**
     * 获取系统权限导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统权限数据模板", SystemRole.class, response);
    }
}
