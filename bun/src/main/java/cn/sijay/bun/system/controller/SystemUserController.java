package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.dto.UserInfo;
import cn.sijay.bun.system.entity.SystemUser;
import cn.sijay.bun.system.service.SystemUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemUserController</strong>
 * <p>
 * 系统用户
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemUser> findById(@RequestParam("id") Long id) {
        return success(systemUserService.findById(id));
    }

    /**
     * 分页查询系统用户列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统用户分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemUser> page(SystemUser entity, PageQuery pageQuery) {
        return systemUserService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统用户列表
     *
     * @param entity 查询条件
     * @return 系统用户列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemUser>> list(@RequestBody SystemUser entity) {
        return success(systemUserService.list(entity));
    }

    /**
     * 保存系统用户
     *
     * @param entity 系统用户
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemUser entity) {
        systemUserService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统用户信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemUserService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统用户
     */
    @PostMapping("/export")
    public void export(SystemUser entity, HttpServletResponse response) {
        List<SystemUser> list = systemUserService.list(entity);
        ExcelUtil.exportExcel(list, "系统用户数据", SystemUser.class, response);
    }

    /**
     * 导入系统用户数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemUser> list = ExcelUtil.importExcel(file.getInputStream(), SystemUser.class);
        systemUserService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统用户导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统用户数据模板", SystemUser.class, response);
    }

    @GetMapping("getUserInfo")
    public ResponseResult<UserInfo> getUserInfo() {
        UserInfo userInfo = new UserInfo();
        SystemUser user = new SystemUser();
        user.setId(1L);
        user.setUserName("admin");
        userInfo.setUser(user);
        userInfo.setRoles(List.of(""));
        userInfo.setPermissions(List.of(""));
        return success(userInfo);
    }
}
