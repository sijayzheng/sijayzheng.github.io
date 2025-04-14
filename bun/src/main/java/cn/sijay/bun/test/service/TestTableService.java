package cn.sijay.bun.test.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.test.entity.TestTable;
import cn.sijay.bun.test.repository.TestTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>TestTableService</strong>
 * <p>
 * 测试数据
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class TestTableService {
    private final TestTableRepository testTableRepository;

    /**
     * 查询测试数据
     *
     * @param id 主键
     * @return 测试数据
     */
    public TestTable findById(Long id) {
        return testTableRepository.getReferenceById(id);
    }

    /**
     * 分页查询测试数据列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 测试数据分页列表
     */
    public ResponseResult<TestTable> page(TestTable entity, PageQuery pageQuery) {
        Specification<TestTable> specification = buildSpecification(entity);
        Page<TestTable> page = testTableRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的测试数据列表
     *
     * @param entity 查询条件
     * @return 测试数据列表
     */
    public List<TestTable> list(TestTable entity) {
        Specification<TestTable> specification = buildSpecification(entity);
        return testTableRepository.findAll(specification);
    }

    /**
     * 保存测试数据
     *
     * @param entity 测试数据
     */
    public void save(TestTable entity) {
        validate(entity);
        testTableRepository.save(entity);
    }

    /**
     * 批量保存测试数据
     *
     * @param list 测试数据集合
     */
    public void save(List<TestTable> list) {
        for (TestTable entity : list) {
            validate(entity);
        }
        testTableRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统测试数据
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        testTableRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 测试数据
     */
    private void validate(TestTable entity) {
    }

    private Specification<TestTable> buildSpecification(TestTable entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<TestTable>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getCInput())) {
            list.add(Spec.equal(TestTable::getCInput, entity.getCInput()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCNumberInput())) {
            list.add(Spec.equal(TestTable::getCNumberInput, entity.getCNumberInput()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCRadio())) {
            list.add(Spec.equal(TestTable::getCRadio, entity.getCRadio()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCSwitch())) {
            list.add(Spec.equal(TestTable::getCSwitch, entity.getCSwitch()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCSelect())) {
            list.add(Spec.equal(TestTable::getCSelect, entity.getCSelect()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCTreeSelect())) {
            list.add(Spec.equal(TestTable::getCTreeSelect, entity.getCTreeSelect()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCDatetimePick())) {
            list.add(Spec.equal(TestTable::getCDatetimePick, entity.getCDatetimePick()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCDatePick())) {
            list.add(Spec.equal(TestTable::getCDatePick, entity.getCDatePick()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCTimePick())) {
            list.add(Spec.equal(TestTable::getCTimePick, entity.getCTimePick()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCBetweenDatetimePick()) && ObjectUtils.isNotEmpty(entity.getCBetweenDatetimePick())) {
            list.add(Spec.between(TestTable::getCBetweenDatetimePick, entity.getCBetweenDatetimePickStrat(), entity.getCBetweenDatetimePickEnd()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCBetweenDatePick()) && ObjectUtils.isNotEmpty(entity.getCBetweenDatePick())) {
            list.add(Spec.between(TestTable::getCBetweenDatePick, entity.getCBetweenDatePickStrat(), entity.getCBetweenDatePickEnd()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCBetweenTimePick()) && ObjectUtils.isNotEmpty(entity.getCBetweenTimePick())) {
            list.add(Spec.between(TestTable::getCBetweenTimePick, entity.getCBetweenTimePickStrat(), entity.getCBetweenTimePickEnd()));
        }
        return Specification.allOf(list);
    }
}
