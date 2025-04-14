package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemMenu;
import cn.sijay.biu.system.service.SystemMenuService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemMenuController
 * 系统菜单请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemMenu")
public class SystemMenuController extends BaseController {
    private final SystemMenuService systemMenuService;

    /**
     * 查询系统菜单
     *
     * @param id 主键
     * @return 系统菜单
     */
    @GetMapping("/getById")
    public Result<SystemMenu> getById(@RequestParam("id") Long id) {
        return success(systemMenuService.getById(id));
    }

    /**
     * 分页查询系统菜单列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统菜单分页列表
     */
    @GetMapping("/page")
    public Result<SystemMenu> page(SystemMenu entity, PageQuery pageQuery) {
        return success(systemMenuService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统菜单列表
     *
     * @param entity 查询条件
     * @return 系统菜单列表
     */
    @GetMapping("/list")
    public Result<SystemMenu> list(SystemMenu entity) {
        return success(systemMenuService.list(entity));
    }

    /**
     * 保存系统菜单
     *
     * @param entity 系统菜单
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemMenu entity) {
        systemMenuService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统菜单信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemMenuService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统菜单
     */
    @GetMapping("/export")
    public void export(SystemMenu entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemMenuService.list(entity), "系统菜单数据", SystemMenu.class, response);
    }

    /**
     * 导入系统菜单数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemMenuService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemMenu.class));
        return success("导入成功");
    }

    /**
     * 获取系统菜单导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统菜单数据模板", SystemMenu.class, response);
    }
}
