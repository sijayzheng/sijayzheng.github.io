package cn.sijay.suap;

import cn.sijay.suap.core.util.JsonUtil;
import cn.sijay.suap.data.service.IDataRegionService;
import cn.sijay.suap.sys.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private IDataRegionService dataRegionService;

    private static void parse(List<SysMenu> menus) {
        Map<Long, String> map = menus.stream().collect(Collectors.toMap(SysMenu::getId, SysMenu::getName));
        for (SysMenu menu : menus) {
            menu.setParentName(map.get(menu.getParentId()));
        }
        System.out.println(JsonUtil.toPrettyJsonString(menus));
    }

    @Test
    void test1() {
        String s = """
                [{"id":2,"creator":1,"createTime":"2024-02-04 15:36:05","updater":1,"updateTime":"2024-02-04 15:45:03","parentId":0,"name":"管理系统","type":"应用","icon":"tool","sort":1,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":3,"creator":1,"createTime":"2024-02-04 15:38:30","parentId":0,"name":"OA系统","type":"应用","icon":"tool","sort":2,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":4,"creator":1,"createTime":"2024-02-04 15:44:58","updater":1,"updateTime":"2024-02-04 15:45:17","parentId":0,"name":"考试系统","type":"应用","icon":"tool","sort":3,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":5,"creator":1,"createTime":"2024-02-04 15:57:43","parentId":2,"name":"系统管理","type":"目录","path":"sys","icon":"tool","sort":1,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":6,"creator":1,"createTime":"2024-02-04 16:35:04","parentId":2,"name":"数据管理","type":"目录","path":"data","icon":"tool","sort":2,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":7,"creator":1,"createTime":"2024-02-04 16:36:43","parentId":2,"name":"系统工具","type":"目录","path":"tool","icon":"tool","sort":3,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":8,"creator":1,"createTime":"2024-02-05 10:12:33","parentId":6,"name":"数据字典","type":"菜单","path":"dataDict","component":"data/dataDict","perms":"data:dataDict","icon":"tool","sort":1,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":9,"creator":1,"createTime":"2024-02-05 10:14:39","parentId":6,"name":"行政区划","type":"菜单","path":"dataRegion","component":"data/dataRegion","perms":"data:dataRegion","icon":"tool","sort":2,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":10,"creator":1,"createTime":"2024-02-05 10:38:14","parentId":5,"name":"菜单管理","type":"菜单","path":"sysMenu","component":"sys/sysMenu","perms":"sys:sysMenu","icon":"tool","sort":1,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":11,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"系统配置","type":"菜单","path":"sysConfig","component":"sys/sysConfig","perms":"sys:sysConfig","icon":"tool","sort":2,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":12,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"部门管理","type":"菜单","path":"sysDept","component":"sys/sysDept","perms":"sys:sysDept","icon":"tool","sort":3,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":13,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"通知公告","type":"菜单","path":"sysNotice","component":"sys/sysNotice","perms":"sys:sysNotice","icon":"tool","sort":8,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":14,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"岗位管理","type":"菜单","path":"sysPost","component":"sys/sysPost","perms":"sys:sysPost","icon":"tool","sort":4,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":15,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"角色管理","type":"菜单","path":"sysRole","component":"sys/sysRole","perms":"sys:sysRole","icon":"tool","sort":5,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":16,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"角色菜单","type":"菜单","path":"sysRoleMenu","component":"sys/sysRoleMenu","perms":"sys:sysRoleMenu","icon":"tool","sort":9,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":17,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"登录用户","type":"菜单","path":"sysUser","component":"sys/sysUser","perms":"sys:sysUser","icon":"tool","sort":6,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":18,"creator":1,"createTime":"2024-02-05 10:43:50","parentId":5,"name":"用户管理","type":"菜单","path":"sysUserInfo","component":"sys/sysUserInfo","perms":"sys:sysUserInfo","icon":"tool","sort":7,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":19,"creator":1,"createTime":"2024-02-05 10:43:51","parentId":5,"name":"用户岗位","type":"菜单","path":"sysUserPost","component":"sys/sysUserPost","perms":"sys:sysUserPost","icon":"tool","sort":9,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":20,"creator":1,"createTime":"2024-02-05 10:43:51","parentId":5,"name":"用户角色","type":"菜单","path":"sysUserRole","component":"sys/sysUserRole","perms":"sys:sysUserRole","icon":"tool","sort":9,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"},{"id":21,"creator":1,"createTime":"2024-02-05 11:32:38","parentId":5,"name":"代码生成","type":"菜单","path":"gen","component":"gen/index","perms":"tool:gen","icon":"tool","sort":1,"link":"否","cache":"是","visible":"是","enabled":"是","deleted":"否"}]
                """;
        List<SysMenu> menus = JsonUtil.parseArray(s, SysMenu.class);
        parse(menus);
        System.out.println(JsonUtil.toJsonString(menus));
    }

    @Test
    void test() {

    }

}
