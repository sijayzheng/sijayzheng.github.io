package cn.sijay.bun.auth.controller;

import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.entity.Router;
import cn.sijay.bun.core.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>RouterController</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-19
 */
@RequiredArgsConstructor
@RestController
@RequestMapping
public class RouterController extends BaseController {

    @GetMapping("getRouters")
    public ResponseResult<List<Router>> getRouters() {
        List<Router> list = new ArrayList<>();
        return success(list);
    }
}

