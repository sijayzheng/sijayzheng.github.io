package cn.sijay.bun.test.controller;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.ExcelUtil;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.test.entity.TestTable;
import cn.sijay.bun.test.service.TestTableService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>TestTableController</strong>
 * <p>
 * 测试数据
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/testTable")
public class TestTableController extends BaseController {
    private final TestTableService testTableService;

    /**
     * 查询测试数据
     *
     * @param id 主键
     * @return 测试数据
     */
    @GetMapping("/findById")
    public ResponseResult<TestTable> findById(@RequestParam("id") Long id) {
        return success(testTableService.findById(id));
    }

    /**
     * 分页查询测试数据列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 测试数据分页列表
     */
    @GetMapping("/page")
    public ResponseResult<TestTable> page(TestTable entity, PageQuery pageQuery) {
        return testTableService.page(entity, pageQuery);
    }

    /**
     * 查询符合条件的测试数据列表
     *
     * @param entity 查询条件
     * @return 测试数据列表
     */
    @PostMapping("/list")
    public ResponseResult<List<TestTable>> list(@RequestBody TestTable entity) {
        return success(testTableService.list(entity));
    }

    /**
     * 保存测试数据
     *
     * @param entity 测试数据
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody TestTable entity) {
        testTableService.save(entity);
        return success("保存成功");
    }

    /**
     * 校验并批量删除测试数据信息
     *
     * @param ids 待删除的主键集合
     * @return 是否删除成功
     */
    @PostMapping("/remove")
    public ResponseResult<String> remove(@RequestBody List<Long> ids) {
        testTableService.remove(ids);
        return success("删除成功");
    }

    /**
     * 导出测试数据
     */
    @PostMapping("/export")
    public void export(TestTable entity, HttpServletResponse response) {
        List<TestTable> list = testTableService.list(entity);
        ExcelUtil.exportExcel(list, "测试数据数据", TestTable.class, response);
    }

    /**
     * 导入测试数据数据
     *
     * @param file 导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<String> importData(@RequestPart("file") MultipartFile file) throws Exception {
        List<TestTable> list = ExcelUtil.importExcel(file.getInputStream(), TestTable.class);
        testTableService.save(list);
        return success("导入成功");
    }

    /**
     * 获取测试数据导入模板
     */
    @PostMapping("/template")
    public void template(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "测试数据数据模板", TestTable.class, response);
    }
}
