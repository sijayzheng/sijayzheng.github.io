package sijay.zheng.experience;

import org.apache.ibatis.session.*;
import org.junit.jupiter.api.*;
import sijay.zheng.experience.mybatis.mapper.*;
import sijay.zheng.experience.mybatis.utils.*;

/**
 * @author Sijay
 * @date 2022/2/8 17:54
 */
public class MTeacherMapperTest {
    @Test
    public void listTeacher() {
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            // 获取接口
            MTeacherMapper mapper = sqlSession.getMapper(MTeacherMapper.class);
            // 执行SQL
            mapper.listTeacher().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
