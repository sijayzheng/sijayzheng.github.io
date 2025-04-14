package cn.sijay.bun.system.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.system.entity.SystemMessage;
import cn.sijay.bun.system.service.SystemMessageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemMessageController</strong>
 * <p>
 * 系统消息
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
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
    @GetMapping("/findById")
    public ResponseResult<SystemMessage> findById(@RequestParam("id") Long id) {
        return success(systemMessageService.findById(id));
    }

    /**
     * 分页查询系统消息列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统消息分页列表
     */
    @GetMapping("/page")
    public ResponseResult<SystemMessage> page(SystemMessage entity, PageQuery pageQuery) {
        return systemMessageService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的系统消息列表
     *
     * @param entity 查询条件
     * @return 系统消息列表
     */
    @PostMapping("/list")
    public ResponseResult<List<SystemMessage>> list(@RequestBody SystemMessage entity) {
        return success(systemMessageService.list(entity));
    }

    /**
     * 保存系统消息
     *
     * @param entity 系统消息
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody SystemMessage entity) {
        systemMessageService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除系统消息信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        systemMessageService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出系统消息
     */
    @PostMapping("/export")
    public void export(SystemMessage entity, HttpServletResponse response) {
        List<SystemMessage> list = systemMessageService.list(entity);
        ExcelUtil.exportExcel(list, "系统消息数据", SystemMessage.class, response);
    }

    /**
     * 导入系统消息数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<SystemMessage> list = ExcelUtil.importExcel(file.getInputStream(), SystemMessage.class);
        systemMessageService.save(list);
        return success("导入成功");
    }

    /**
     * 获取系统消息导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "系统消息数据模板", SystemMessage.class, response);
    }
}
