package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemUser;
import cn.sijay.biu.system.service.SystemUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemUserController
 * 系统用户请求控制层
 *
 * @author Sijay
 * @since 2025-04-09
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemUser")
public class SystemUserController extends BaseController {
    private final SystemUserService systemUserService;

    /**
     * 查询系统用户
     *
     * @param id 主键
     * @return 系统用户
     */
    @GetMapping("/getById")
    public Result<SystemUser> getById(@RequestParam("id") Long id) {
        return success(systemUserService.getById(id));
    }

    /**
     * 分页查询系统用户列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统用户分页列表
     */
    @GetMapping("/page")
    public Result<SystemUser> page(SystemUser entity, PageQuery pageQuery) {
        return success(systemUserService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统用户列表
     *
     * @param entity 查询条件
     * @return 系统用户列表
     */
    @GetMapping("/list")
    public Result<SystemUser> list(SystemUser entity) {
        return success(systemUserService.list(entity));
    }

    /**
     * 保存系统用户
     *
     * @param entity 系统用户
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemUser entity) {
        systemUserService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统用户信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemUserService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统用户
     */
    @GetMapping("/export")
    public void export(SystemUser entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemUserService.list(entity), "系统用户数据", SystemUser.class, response);
    }

    /**
     * 导入系统用户数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemUserService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemUser.class));
        return success("导入成功");
    }

    /**
     * 获取系统用户导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统用户数据模板", SystemUser.class, response);
    }
}
