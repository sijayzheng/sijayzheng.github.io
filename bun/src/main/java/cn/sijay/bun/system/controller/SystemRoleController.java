package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemRole;
import cn.sijay.bun.system.service.SystemRoleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemRoleController</strong>
 * <p>
 * 系统权限
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemRole> findById(@RequestParam("id") Long id) {
        return success(systemRoleService.findById(id));
    }

    /**
     * 分页查询系统权限列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统权限分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemRole> page(SystemRole entity, PageQuery pageQuery) {
        return systemRoleService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统权限列表
     *
     * @param entity 查询条件
     * @return 系统权限列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemRole>> list(@RequestBody SystemRole entity) {
        return success(systemRoleService.list(entity));
    }

    /**
     * 保存系统权限
     *
     * @param entity 系统权限
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemRole entity) {
        systemRoleService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统权限信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemRoleService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统权限
     */
    @PostMapping("/export")
    public void export(SystemRole entity, HttpServletResponse response) {
        List<SystemRole> list = systemRoleService.list(entity);
        ExcelUtil.exportExcel(list, "系统权限数据", SystemRole.class, response);
    }

    /**
     * 导入系统权限数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemRole> list = ExcelUtil.importExcel(file.getInputStream(), SystemRole.class);
        systemRoleService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统权限导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统权限数据模板", SystemRole.class, response);
    }
}
