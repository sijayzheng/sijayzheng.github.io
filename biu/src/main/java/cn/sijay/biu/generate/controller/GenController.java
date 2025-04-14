package cn.sijay.biu.generate.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.sijay.biu.core.annotation.Log;
import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.enums.BusinessType;
import cn.sijay.biu.core.exception.BaseException;
import cn.sijay.biu.core.util.StringUtil;
import cn.sijay.biu.generate.entity.GenColumn;
import cn.sijay.biu.generate.entity.GenTable;
import cn.sijay.biu.generate.enums.TemplateEnum;
import cn.sijay.biu.generate.service.GenService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * GenController
 *
 * @author Sijay
 * @since 2025-02-14
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/gen")
public class GenController extends BaseController {
    private final GenService genService;

    /**
     * 查询代码生成列表
     */
    @SaCheckPermission("tool:gen:list")
    @GetMapping("/list")
    public Result<GenTable> genList(GenTable genTable, PageQuery pageQuery) {
        return success(genService.page(genTable, pageQuery));
    }

    /**
     * 获取表id获取表和列信息
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:query")
    @GetMapping("/getById")
    public Result<GenTable> getInfo(@RequestParam("tableId") Long tableId) {
        GenTable table = genService.getById(tableId);
        List<GenColumn> list = genService.listColumnByTableId(tableId);
        table.setColumns(list);
        return success(table);
    }

    /**
     * 查询数据库列表
     */
    @SaCheckPermission("tool:gen:list")
    @GetMapping("/db/list")
    public Result<GenTable> dataList(@RequestParam("tableName") String tableName) {
        return success(genService.selectTableList(tableName));
    }

    /**
     * 查询数据表字段列表
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:list")
    @GetMapping("/listColumn")
    public Result<GenColumn> listColumn(@RequestParam("tableId") Long tableId) {
        Result<GenColumn> dataInfo = new Result<>();
        List<GenColumn> list = genService.listColumnByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构
     *
     * @param tableNames 表名串
     */
    @SaCheckPermission("tool:gen:import")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public Result<Void> importTable(@RequestBody List<String> tableNames) {
        genService.importGenTable(tableNames);
        return success();
    }

    /**
     * 修改保存代码生成业务
     */
    @SaCheckPermission("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/save")
    public Result<Void> save(@Validated @RequestBody GenTable genTable) {
        if (TemplateEnum.TREE == genTable.getTemplateType() && StringUtil.isAnyBlank(genTable.getTreeKey(), genTable.getTreeLabel(), genTable.getTreeParentKey())) {
            throw new BaseException("树的父字段不能为空");
        }
        genService.updateGenTable(genTable);
        return success();
    }

    /**
     * 删除代码生成
     */
    @SaCheckPermission("tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public Result<Void> remove(@RequestBody List<Long> ids) {
        genService.deleteGenTableByIds(ids);
        return success();
    }

    /**
     * 预览代码
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:preview")
    @GetMapping("/preview")
    public Result<Map<String, String>> preview(@RequestParam("tableId") Long tableId) {
        Map<String, String> dataMap = genService.previewCode(tableId);
        return success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download")
    public void download(@RequestParam("tableId") Long tableId, HttpServletResponse response) throws IOException {
        byte[] data = genService.downloadCode(tableId);
        genCode(data, response);
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode")
    public Result<Void> genCode(@RequestParam("tableId") Long tableId) {
        genService.generatorCode(tableId);
        return success();
    }

    /**
     * 同步数据库
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/syncDb")
    public Result<Void> syncDb(@RequestParam("tableId") Long tableId) {
        genService.syncDb(tableId);
        return success();
    }

    /**
     * 批量生成代码
     *
     * @param tableIds 表ID串
     */
    @SaCheckPermission("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(List<Long> tableIds, HttpServletResponse response) {
        byte[] data = genService.downloadCode(tableIds);
        genCode(data, response);
    }

    /**
     * 生成zip文件
     */
    private void genCode(byte[] data, HttpServletResponse response) {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

