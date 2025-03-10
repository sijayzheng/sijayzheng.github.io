package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemPost;
import cn.sijay.bun.system.service.SystemPostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemPostController</strong>
 * <p>
 * 系统岗位
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemPost> findById(@RequestParam("id") Long id) {
        return success(systemPostService.findById(id));
    }

    /**
     * 分页查询系统岗位列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统岗位分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemPost> page(SystemPost entity, PageQuery pageQuery) {
        return systemPostService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统岗位列表
     *
     * @param entity 查询条件
     * @return 系统岗位列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemPost>> list(@RequestBody SystemPost entity) {
        return success(systemPostService.list(entity));
    }

    /**
     * 保存系统岗位
     *
     * @param entity 系统岗位
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemPost entity) {
        systemPostService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统岗位信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemPostService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统岗位
     */
    @PostMapping("/export")
    public void export(SystemPost entity, HttpServletResponse response) {
        List<SystemPost> list = systemPostService.list(entity);
        ExcelUtil.exportExcel(list, "系统岗位数据", SystemPost.class, response);
    }

    /**
     * 导入系统岗位数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemPost> list = ExcelUtil.importExcel(file.getInputStream(), SystemPost.class);
        systemPostService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统岗位导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统岗位数据模板", SystemPost.class, response);
    }
}
