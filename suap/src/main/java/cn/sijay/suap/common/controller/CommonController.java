package cn.sijay.suap.common.controller;

import cn.sijay.suap.core.annotation.Log;
import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.entity.Result;
import cn.sijay.suap.core.enums.OperateType;
import cn.sijay.suap.core.util.ReflectUtil;
import cn.sijay.suap.data.service.IDataDictService;
import cn.sijay.suap.gen.service.GenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * <em>CommonController 公共数据接口</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/26 10:26
 */
@Tag(name = "公共数据接口", description = "/common")
@RequiredArgsConstructor
@RestController
@RequestMapping("common")
public class CommonController extends BaseController {

    private final IDataDictService dataDictService;
    private final GenService genService;

    /**
     * 查询字典数据
     *
     * @param code 字典编码
     * @return 返回选项集合
     */
    @Log(value = "查询字典数据", operateType = OperateType.SELECT)
    @Operation(summary = "查询字典数据")
    @Parameter(name = "code", description = "字典编码")
    @ApiResponse(responseCode = "200", description = "选项集合")
    @GetMapping("listDictDataOptions")
    public Result<List<Option<String>>> listDictDataOptions(@RequestParam("code") String code) {
        return success(dataDictService.getOptions(code));
    }

    /**
     * 查询枚举数据
     *
     * @param enumClass 枚举类名
     * @return 返回选项集合
     */
    @Log(value = "查询枚举数据", operateType = OperateType.SELECT)
    @Operation(summary = "查询枚举数据")
    @Parameter(name = "enumClass", description = "枚举类名")
    @ApiResponse(responseCode = "200", description = "选项集合")
    @GetMapping("listEnumDataOptions")
    public Result<List<Option<String>>> listEnumDataOptions(@RequestParam("enumClass") String enumClass) {
        return success(ReflectUtil.enumToOption(enumClass));
    }

    /**
     * 查询表数据数据
     *
     * @param tableName 表名
     * @return 返回选项集合
     */
    @Log(value = "查询表数据数据", operateType = OperateType.SELECT)
    @Operation(summary = "查询表数据数据")
    @Parameter(name = "tableName", description = "表名")
    @ApiResponse(responseCode = "200", description = "选项集合")
    @GetMapping("listTableDataOptions")
    public Result<List<Option<Long>>> listTableDataOptions(@RequestParam("tableName") String tableName) {
        return success(genService.getTableData(tableName));
    }

}
