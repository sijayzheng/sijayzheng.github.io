package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemPost;
import cn.sijay.biu.system.service.SystemPostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemPostController
 * 系统岗位请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemPost")
public class SystemPostController extends BaseController {
    private final SystemPostService systemPostService;

    /**
     * 查询系统岗位
     *
     * @param id 主键
     * @return 系统岗位
     */
    @GetMapping("/getById")
    public Result<SystemPost> getById(@RequestParam("id") Long id) {
        return success(systemPostService.getById(id));
    }

    /**
     * 分页查询系统岗位列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统岗位分页列表
     */
    @GetMapping("/page")
    public Result<SystemPost> page(SystemPost entity, PageQuery pageQuery) {
        return success(systemPostService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统岗位列表
     *
     * @param entity 查询条件
     * @return 系统岗位列表
     */
    @GetMapping("/list")
    public Result<SystemPost> list(SystemPost entity) {
        return success(systemPostService.list(entity));
    }

    /**
     * 保存系统岗位
     *
     * @param entity 系统岗位
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemPost entity) {
        systemPostService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统岗位信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemPostService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统岗位
     */
    @GetMapping("/export")
    public void export(SystemPost entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemPostService.list(entity), "系统岗位数据", SystemPost.class, response);
    }

    /**
     * 导入系统岗位数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemPostService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemPost.class));
        return success("导入成功");
    }

    /**
     * 获取系统岗位导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统岗位数据模板", SystemPost.class, response);
    }
}
