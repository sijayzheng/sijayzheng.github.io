package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemDict;
import cn.sijay.bun.system.service.SystemDictService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemDictController</strong>
 * <p>
 * 系统字典
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemDict")
public class SystemDictController extends BaseController {
    private final SystemDictService systemDictService;

    /**
     * 查询系统字典
     *
     * @param id 主键
     * @return 系统字典
     */
    @GetMapping("/findById")
    public ResponseResult<SystemDict> findById(@RequestParam("id") Long id) {
        return success(systemDictService.findById(id));
    }

    /**
     * 分页查询系统字典列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemDict> page(SystemDict entity, PageQuery pageQuery) {
        return systemDictService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统字典列表
     *
     * @param entity 查询条件
     * @return 系统字典列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemDict>> list(@RequestBody SystemDict entity) {
        return success(systemDictService.list(entity));
    }

    /**
     * 保存系统字典
     *
     * @param entity 系统字典
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemDict entity) {
        systemDictService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统字典信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemDictService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统字典
     */
    @PostMapping("/export")
    public void export(SystemDict entity, HttpServletResponse response) {
        List<SystemDict> list = systemDictService.list(entity);
        ExcelUtil.exportExcel(list, "系统字典数据", SystemDict.class, response);
    }

    /**
     * 导入系统字典数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemDict> list = ExcelUtil.importExcel(file.getInputStream(), SystemDict.class);
        systemDictService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统字典导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统字典数据模板", SystemDict.class, response);
    }
}
