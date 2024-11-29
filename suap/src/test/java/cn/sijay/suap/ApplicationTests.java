package cn.sijay.suap;

import cn.sijay.suap.gen.service.GenService;
import cn.sijay.suap.sys.service.ISysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class ApplicationTests {
    @Autowired
    private GenService genService;
    @Autowired
    private ISysMenuService sysMenuService;

    @Test
    void test() {
//        genService.autoImport();
        genService.generate(null);
//        SysMenu sysMenu = new SysMenu();
//        sysMenu.setName("用户");
//        System.out.println(JsonUtil.toPrettyJsonString(sysMenuService.listTree(sysMenu)));
    }

}
