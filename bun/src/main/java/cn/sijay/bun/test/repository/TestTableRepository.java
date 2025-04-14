package cn.sijay.bun.test.repository;

import cn.sijay.bun.test.entity.TestTable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * <strong>TestTableRepository</strong>
 * <p>
 * 测试数据
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
public interface TestTableRepository extends JpaRepositoryImplementation<TestTable, Long> {

}
