package sijay.zheng.experience;

import com.alibaba.fastjson2.*;
import org.apache.ibatis.session.*;
import org.junit.jupiter.api.*;
import sijay.zheng.experience.mybatis.mapper.*;
import sijay.zheng.experience.mybatis.utils.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Sijay
 * @date 2022/2/8 17:54
 */
public class MapperTest {
    @Test
    public void listAll() {
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory()
                .openSession()) {
            // 获取接口
            SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
            List<HashMap<String, Object>> list = mapper.listAll("m_user", "all", "p3");
            for (HashMap<String, Object> hashMap : list) {
                System.out.println(JSON.toJSONString(hashMap, JSONWriter.Feature.WriteMapNullValue));
                System.out.println(hashMap.values()
                        .stream()
                        .map(value -> Optional.ofNullable(value)
                                .map(Object::toString)
                                .orElse(""))
                        .collect(Collectors.joining("+++")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}