package sijay.zheng.experience;

import com.baomidou.mybatisplus.core.conditions.query.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import sijay.zheng.experience.mybatisplus.domain.*;
import sijay.zheng.experience.mybatisplus.service.*;

/**
 * @author Sijay
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    private IPetService petService;

    @Test
    public void list() {
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.ge("birthday", "2022-07-05");
        wrapper.le("birthday", "2022-07-05");
        for (Pet pet : petService.list(wrapper)) {
            System.out.println(pet);
        }
    }
}