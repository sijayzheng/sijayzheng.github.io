package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemDept;
import cn.sijay.bun.system.service.SystemDeptService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemDeptController</strong>
 * <p>
 * 系统部门
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemDept> findById(@RequestParam("id") Long id) {
        return success(systemDeptService.findById(id));
    }

    /**
     * 分页查询系统部门列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统部门分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemDept> page(SystemDept entity, PageQuery pageQuery) {
        return systemDeptService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统部门列表
     *
     * @param entity 查询条件
     * @return 系统部门列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemDept>> list(@RequestBody SystemDept entity) {
        return success(systemDeptService.list(entity));
    }

    /**
     * 保存系统部门
     *
     * @param entity 系统部门
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemDept entity) {
        systemDeptService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统部门信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemDeptService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统部门
     */
    @PostMapping("/export")
    public void export(SystemDept entity, HttpServletResponse response) {
        List<SystemDept> list = systemDeptService.list(entity);
        ExcelUtil.exportExcel(list, "系统部门数据", SystemDept.class, response);
    }

    /**
     * 导入系统部门数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemDept> list = ExcelUtil.importExcel(file.getInputStream(), SystemDept.class);
        systemDeptService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统部门导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统部门数据模板", SystemDept.class, response);
    }
}
