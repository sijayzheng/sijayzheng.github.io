package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.sys.entity.SysDept;
import cn.sijay.suap.sys.service.ISysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <strong>SysDeptController</strong>
 * <p>
 * 部门控制层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Tag(name = "部门")
@RestController
@RequestMapping("/sysDept")
@RequiredArgsConstructor
public class SysDeptController extends BaseController {
    private final ISysDeptService sysDeptService;

    /**
     * 查询所有记录
     */
    @Operation(summary = "查询所有记录")
    @GetMapping("/listTree")
    public Res<List<SysDept>> listTree(SysDept sysDept) {
        return success(sysDeptService.listTree(sysDept));
    }

    /**
     * 根据id查询
     */
    @Operation(summary = "根据id查询")
    @GetMapping("/getById")
    public Res<SysDept> getById(@RequestParam("id") Long id) {
        return success(sysDeptService.getById(id));
    }

    /**
     * 保存
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    public Res<Void> save(@Validated @RequestBody SysDept sysDept) {
        sysDeptService.save(sysDept);
        return success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Res<Void> remove(@RequestBody List<Long> ids) {
        sysDeptService.remove(ids);
        return success();
    }

}
