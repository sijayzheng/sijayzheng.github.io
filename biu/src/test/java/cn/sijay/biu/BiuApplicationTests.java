package cn.sijay.biu;

import cn.sijay.biu.system.entity.SystemDept;
import cn.sijay.biu.system.service.SystemDeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BiuApplicationTests {
    @Autowired
    private SystemDeptService systemDeptService;

    @Test
    void contextLoads() {
        SystemDept dept = new SystemDept();
        dept.setName("测试部门11");
        dept.setParentId(0L);
        dept.setAncestors(List.of(0L));
        dept.setSort(0);
        dept.setLeader(5L);
        dept.setPhone("12312345678");
        dept.setEmail("XXXTENTACION");
        dept.setEnable(true);
        systemDeptService.save(dept);
    }
}
