package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemConfig;
import cn.sijay.biu.system.service.SystemConfigService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemConfigController
 * 系统配置请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
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
    @GetMapping("/getById")
    public Result<SystemConfig> getById(@RequestParam("id") Long id) {
        return success(systemConfigService.getById(id));
    }

    /**
     * 分页查询系统配置列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统配置分页列表
     */
    @GetMapping("/page")
    public Result<SystemConfig> page(SystemConfig entity, PageQuery pageQuery) {
        return success(systemConfigService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统配置列表
     *
     * @param entity 查询条件
     * @return 系统配置列表
     */
    @GetMapping("/list")
    public Result<SystemConfig> list(SystemConfig entity) {
        return success(systemConfigService.list(entity));
    }

    /**
     * 保存系统配置
     *
     * @param entity 系统配置
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemConfig entity) {
        systemConfigService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统配置信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemConfigService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统配置
     */
    @GetMapping("/export")
    public void export(SystemConfig entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemConfigService.list(entity), "系统配置数据", SystemConfig.class, response);
    }

    /**
     * 导入系统配置数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemConfigService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemConfig.class));
        return success("导入成功");
    }

    /**
     * 获取系统配置导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统配置数据模板", SystemConfig.class, response);
    }
}
