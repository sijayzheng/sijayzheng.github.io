package cn.sijay.biu.system.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.system.entity.SystemMessage;
import cn.sijay.biu.system.service.SystemMessageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemMessageController
 * 系统消息请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/systemMessage")
public class SystemMessageController extends BaseController {
    private final SystemMessageService systemMessageService;

    /**
     * 查询系统消息
     *
     * @param id 主键
     * @return 系统消息
     */
    @GetMapping("/getById")
    public Result<SystemMessage> getById(@RequestParam("id") Long id) {
        return success(systemMessageService.getById(id));
    }

    /**
     * 分页查询系统消息列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统消息分页列表
     */
    @GetMapping("/page")
    public Result<SystemMessage> page(SystemMessage entity, PageQuery pageQuery) {
        return success(systemMessageService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的系统消息列表
     *
     * @param entity 查询条件
     * @return 系统消息列表
     */
    @GetMapping("/list")
    public Result<SystemMessage> list(SystemMessage entity) {
        return success(systemMessageService.list(entity));
    }

    /**
     * 保存系统消息
     *
     * @param entity 系统消息
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody SystemMessage entity) {
        systemMessageService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统消息信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        systemMessageService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统消息
     */
    @GetMapping("/export")
    public void export(SystemMessage entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(systemMessageService.list(entity), "系统消息数据", SystemMessage.class, response);
    }

    /**
     * 导入系统消息数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        systemMessageService.importData(ExcelUtil.importExcel(file.getInputStream(), SystemMessage.class));
        return success("导入成功");
    }

    /**
     * 获取系统消息导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统消息数据模板", SystemMessage.class, response);
    }
}
