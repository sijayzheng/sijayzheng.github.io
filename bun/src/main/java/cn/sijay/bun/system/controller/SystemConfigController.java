package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemConfig;
import cn.sijay.bun.system.service.SystemConfigService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemConfigController</strong>
 * <p>
 * 系统配置
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemConfig")
public class SystemConfigController extends BaseController {
    private final SystemConfigService systemConfigService;

    /**
     * 查询系统配置
     *
     * @param id 主键
     * @return 系统配置
     */
    @GetMapping("/findById")
    public ResponseResult<SystemConfig> findById(@RequestParam("id") Long id) {
        return success(systemConfigService.findById(id));
    }

    /**
     * 分页查询系统配置列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统配置分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemConfig> page(SystemConfig entity, PageQuery pageQuery) {
        return systemConfigService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统配置列表
     *
     * @param entity 查询条件
     * @return 系统配置列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemConfig>> list(@RequestBody SystemConfig entity) {
        return success(systemConfigService.list(entity));
    }

    /**
     * 保存系统配置
     *
     * @param entity 系统配置
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemConfig entity) {
        systemConfigService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统配置信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemConfigService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统配置
     */
    @PostMapping("/export")
    public void export(SystemConfig entity, HttpServletResponse response) {
        List<SystemConfig> list = systemConfigService.list(entity);
        ExcelUtil.exportExcel(list, "系统配置数据", SystemConfig.class, response);
    }

    /**
     * 导入系统配置数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemConfig> list = ExcelUtil.importExcel(file.getInputStream(), SystemConfig.class);
        systemConfigService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统配置导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统配置数据模板", SystemConfig.class, response);
    }
}
