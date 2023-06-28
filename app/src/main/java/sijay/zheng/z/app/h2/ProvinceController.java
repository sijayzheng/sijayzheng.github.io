package sijay.zheng.z.app.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sijay
 * @date 2023/6/28 23:11
 */
@RestController
@RequestMapping("province")
public class ProvinceController {
    @Autowired
    private ProvinceRepository provinceRepository;

    @GetMapping("listAll")
    public List<Province> listAll() {
        return provinceRepository.findAll();
    }
}
