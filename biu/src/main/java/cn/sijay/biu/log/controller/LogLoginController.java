package cn.sijay.biu.log.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.log.entity.LogLogin;
import cn.sijay.biu.log.service.LogLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * LogLoginController
 * 登录日志请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/logLogin")
public class LogLoginController extends BaseController {
    private final LogLoginService logLoginService;

    /**
     * 查询登录日志
     *
     * @param id 主键
     * @return 登录日志
     */
    @GetMapping("/getById")
    public Result<LogLogin> getById(@RequestParam("id") Long id) {
        return success(logLoginService.getById(id));
    }

    /**
     * 分页查询登录日志列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 登录日志分页列表
     */
    @GetMapping("/page")
    public Result<LogLogin> page(LogLogin entity, PageQuery pageQuery) {
        return success(logLoginService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的登录日志列表
     *
     * @param entity 查询条件
     * @return 登录日志列表
     */
    @GetMapping("/list")
    public Result<LogLogin> list(LogLogin entity) {
        return success(logLoginService.list(entity));
    }

    /**
     * 保存登录日志
     *
     * @param entity 登录日志
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody LogLogin entity) {
        logLoginService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除登录日志信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        logLoginService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出登录日志
     */
    @GetMapping("/export")
    public void export(LogLogin entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(logLoginService.list(entity), "登录日志数据", LogLogin.class, response);
    }

    /**
     * 导入登录日志数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        logLoginService.importData(ExcelUtil.importExcel(file.getInputStream(), LogLogin.class));
        return success("导入成功");
    }

    /**
     * 获取登录日志导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "登录日志数据模板", LogLogin.class, response);
    }
}
