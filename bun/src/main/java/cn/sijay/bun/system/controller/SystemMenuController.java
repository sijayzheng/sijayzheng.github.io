package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemMenu;
import cn.sijay.bun.system.service.SystemMenuService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemMenuController</strong>
 * <p>
 * 系统菜单
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemMenu> findById(@RequestParam("id") Long id) {
        return success(systemMenuService.findById(id));
    }

    /**
     * 分页查询系统菜单列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统菜单分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemMenu> page(SystemMenu entity, PageQuery pageQuery) {
        return systemMenuService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统菜单列表
     *
     * @param entity 查询条件
     * @return 系统菜单列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemMenu>> list(@RequestBody SystemMenu entity) {
        return success(systemMenuService.list(entity));
    }

    /**
     * 保存系统菜单
     *
     * @param entity 系统菜单
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemMenu entity) {
        systemMenuService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统菜单信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemMenuService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统菜单
     */
    @PostMapping("/export")
    public void export(SystemMenu entity, HttpServletResponse response) {
        List<SystemMenu> list = systemMenuService.list(entity);
        ExcelUtil.exportExcel(list, "系统菜单数据", SystemMenu.class, response);
    }

    /**
     * 导入系统菜单数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemMenu> list = ExcelUtil.importExcel(file.getInputStream(), SystemMenu.class);
        systemMenuService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统菜单导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统菜单数据模板", SystemMenu.class, response);
    }
}
