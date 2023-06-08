package sijay.zheng.experience.mybatis.utils;

import org.apache.ibatis.io.*;
import org.apache.ibatis.session.*;

import java.io.*;
import java.util.*;

/**
 * @author Sijay
 */
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    // 获取SqlSessionFactory
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            Properties properties = new Properties();
            properties.put("", "");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}