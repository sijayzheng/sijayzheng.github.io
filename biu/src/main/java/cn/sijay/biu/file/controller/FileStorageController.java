package cn.sijay.biu.file.controller;

import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.util.ExcelUtil;
import cn.sijay.biu.file.entity.FileStorage;
import cn.sijay.biu.file.service.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * FileStorageController
 * 文件存储请求控制层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/fileStorage")
public class FileStorageController extends BaseController {
    private final FileStorageService fileStorageService;

    /**
     * 查询文件存储
     *
     * @param id 主键
     * @return 文件存储
     */
    @GetMapping("/getById")
    public Result<FileStorage> getById(@RequestParam("id") Long id) {
        return success(fileStorageService.getById(id));
    }

    /**
     * 分页查询文件存储列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 文件存储分页列表
     */
    @GetMapping("/page")
    public Result<FileStorage> page(FileStorage entity, PageQuery pageQuery) {
        return success(fileStorageService.page(entity, pageQuery));
    }

    /**
     * 查询符合条件的文件存储列表
     *
     * @param entity 查询条件
     * @return 文件存储列表
     */
    @GetMapping("/list")
    public Result<FileStorage> list(FileStorage entity) {
        return success(fileStorageService.list(entity));
    }

    /**
     * 保存文件存储
     *
     * @param entity 文件存储
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody FileStorage entity) {
        fileStorageService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除文件存储信息
     *
     * @param ids 待删除的主键集合
     */
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestBody List<Long> ids) {
        fileStorageService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出文件存储
     */
    @GetMapping("/export")
    public void export(FileStorage entity, HttpServletResponse response) {
        ExcelUtil.exportExcel(fileStorageService.list(entity), "文件存储数据", FileStorage.class, response);
    }

    /**
     * 导入文件存储数据
     *
     * @param file 导入文件
     */
    @GetMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        fileStorageService.importData(ExcelUtil.importExcel(file.getInputStream(), FileStorage.class));
        return success("导入成功");
    }

    /**
     * 获取文件存储导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "文件存储数据模板", FileStorage.class, response);
    }
}
