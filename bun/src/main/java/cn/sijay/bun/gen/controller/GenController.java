package cn.sijay.bun.gen.controller;

import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.core.base.BaseController;
import cn.sijay.bun.gen.entity.GenTable;
import cn.sijay.bun.gen.service.GenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <strong>GenController</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-01
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/gen")
public class GenController extends BaseController {
    private final GenService genService;

    @GetMapping("/getTableById")
    public ResponseResult<GenTable> getTableById() {
        GenTable tableById = genService.getTableById(4L);
        return success(tableById);
    }

}
