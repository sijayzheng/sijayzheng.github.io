package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemUserAvatar;
import cn.sijay.biu.system.service.SystemUserAvatarService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemUserAvatarController
 * 用户头像请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemUserAvatar")
public class SystemUserAvatarController extends BaseController {
    private final SystemUserAvatarService systemUserAvatarService;

    /**
     * 查询用户头像
     *
     * @param id 主键
     * @return 用户头像
     */
    @GetMapping("/getById")
    public Result<SystemUserAvatar> getById(@RequestParam("id") Long id) {
        return success(systemUserAvatarService.getById(id));
    }

    /**
     * 分页查询用户头像列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 用户头像分页列表
     */
    @GetMapping("/page")
    public Result<SystemUserAvatar> page(SystemUserAvatar entity, PageQuery pageQuery) {
        return success(systemUserAvatarService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的用户头像列表
     *
     * @param entity 查询条件
     * @return 用户头像列表
     */
    @GetMapping("/list")
    public Result<SystemUserAvatar> list(SystemUserAvatar entity) {
        return success(systemUserAvatarService.list(entity));
    }

    /**
     * 保存用户头像
     *
     * @param entity 用户头像
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemUserAvatar entity) {
        systemUserAvatarService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除用户头像信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemUserAvatarService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出用户头像
     */
    @GetMapping("/export")
    public void export(SystemUserAvatar entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemUserAvatarService.list(entity), "用户头像数据", SystemUserAvatar.class, response);
    }

    /**
     * 导入用户头像数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemUserAvatarService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemUserAvatar.class));
        return success("导入成功");
    }

    /**
     * 获取用户头像导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "用户头像数据模板", SystemUserAvatar.class, response);
    }
}
