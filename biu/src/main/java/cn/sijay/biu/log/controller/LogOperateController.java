package cn.sijay.biu.log.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.log.entity.LogOperate;
import cn.sijay.biu.log.service.LogOperateService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * LogOperateController
 * 操作日志请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/logOperate")
public class LogOperateController extends BaseController {
    private final LogOperateService logOperateService;

    /**
     * 查询操作日志
     *
     * @param id 主键
     * @return 操作日志
     */
    @GetMapping("/getById")
    public Result<LogOperate> getById(@RequestParam("id") Long id) {
        return success(logOperateService.getById(id));
    }

    /**
     * 分页查询操作日志列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 操作日志分页列表
     */
    @GetMapping("/page")
    public Result<LogOperate> page(LogOperate entity, PageQuery pageQuery) {
        return success(logOperateService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的操作日志列表
     *
     * @param entity 查询条件
     * @return 操作日志列表
     */
    @GetMapping("/list")
    public Result<LogOperate> list(LogOperate entity) {
        return success(logOperateService.list(entity));
    }

    /**
     * 保存操作日志
     *
     * @param entity 操作日志
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody LogOperate entity) {
        logOperateService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除操作日志信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        logOperateService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出操作日志
     */
    @GetMapping("/export")
    public void export(LogOperate entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(logOperateService.list(entity), "操作日志数据", LogOperate.class, response);
    }

    /**
     * 导入操作日志数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        logOperateService.importData(ExcelUtil.importExcel(file.getInputStream(), LogOperate.class));
        return success("导入成功");
    }

    /**
     * 获取操作日志导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "操作日志数据模板", LogOperate.class, response);
    }
}
