/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/6/14 11:36
 */
public class GenTest {
    static String dbUrl = "jdbc:mysql://localhost:3306/sijay?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    static String dbUsername = "root";
    static String dbPassword = "root";

    public static void main(String[] args) {
        List<String> dtoIgnoreFields = Arrays.asList("create_user", "create_time", "update_user", "update_time", "version");
        String[] tablesName = {"shirts"};
        String moduleName = "system";

        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(dbUrl, dbUsername, dbPassword);
        String parentPackage = "sijay.zheng.a";
        FastAutoGenerator.create(dataSourceConfigBuilder)
                // 全局配置
                .globalConfig(builder -> builder
                        .outputDir("~\\tmp")
                        .author("generator")
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd HH:ss:mm")
                )
                // 包配置
                .packageConfig(builder -> builder.parent(parentPackage)
                        .moduleName(moduleName)
                        .service("service.base")
                        .serviceImpl("service.base.impl")
                )
                // 策略配置
                .strategyConfig(builder -> builder.addInclude(tablesName) // 设置需要生成的表名
                        .enableCapitalMode()// 开启大写命名
                        .addTablePrefix(moduleName)

                        .controllerBuilder()
                        .enableRestStyle() // 开启驼峰转连字符
                        .enableHyphenStyle() // 开启生成@RestController 控制器
                        .enableFileOverride()

                        .serviceBuilder() // service配置
                        .formatServiceFileName("IBase%sService")
                        .formatServiceImplFileName("Base%sServiceImp")
                        .enableFileOverride()

                        .mapperBuilder() // mapper层配置
                        .superClass(BaseMapper.class)
                        .enableBaseColumnList() //启用 BaseColumnList
                        .enableBaseResultMap() // 启用 BaseResultMap 生成
                        .enableFileOverride()

                        .entityBuilder() // 实体类配置
                        .enableColumnConstant()
                        .enableChainModel()
                        .enableLombok() // 开启lombok注解
                        .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                        .enableFileOverride()
                )
                //注入配置
                .injectionConfig(builder -> builder.beforeOutputFile((tableInfo, objectMap) -> {
                            HashMap<String, Object> customProperties = new HashMap<>();
                            customProperties.put("dtoPackage", "dto");
                            customProperties.put("dtoFields", tableInfo.getFields().stream().filter(field -> !dtoIgnoreFields.contains(field.getColumnName())).collect(Collectors.toList()));
                            customProperties.put("dtoImportPackages", tableInfo.getImportPackages().stream().filter(packageName -> !packageName.startsWith("com.baomidou.mybatisplus.")).collect(Collectors.toList()));
                            objectMap.put("cp", customProperties);
                            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                        }).customFile(new CustomFile.Builder()
                                .fileName("DTO.java")
                                .templatePath("/templates/dto.java.vm")
                                .packageName("dto")
                                .enableFileOverride()
                                .build()
                        )
                ).execute();
    }
}
