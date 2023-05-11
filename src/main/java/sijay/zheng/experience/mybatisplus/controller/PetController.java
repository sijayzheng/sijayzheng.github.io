package sijay.zheng.experience.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import sijay.zheng.experience.mybatisplus.domain.*;
import sijay.zheng.experience.mybatisplus.service.*;

import java.util.*;

/**
 * @author Sijay
 */
@RestController
@RequestMapping("pet")
public class PetController {

    @Autowired
    private IPetService petService;

    @PostMapping("list")
    public List<Pet> listAll() {
        return petService.list();
    }

    @PostMapping("list2")
    public List<Pet> listAll2() {
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.lt("age", 5)
                .and(qw -> qw.like("name", "a")
                        .or()
                        .like("type", "a"));
        return petService.list(wrapper);
    }

    @PostMapping("test")
    public void test() {
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.ge("date(birthday)", "2022-07-05");
        wrapper.le("date(birthday)", "2022-07-05");
        for (Pet pet : petService.list(wrapper)) {
            System.out.println(pet);
        }
    }
}