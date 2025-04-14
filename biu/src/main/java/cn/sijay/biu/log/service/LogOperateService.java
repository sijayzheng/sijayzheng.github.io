package cn.sijay.biu.log.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.log.entity.LogOperate;
import cn.sijay.biu.log.repository.LogOperateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * LogOperateService
 * 操作日志逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class LogOperateService implements BaseService {
    private final LogOperateRepository logOperateRepository;

    /**
     * 查询操作日志
     *
     * @param id 主键
     * @return 操作日志
     */
    public LogOperate getById(Long id) {
        return logOperateRepository.getReferenceById(id);
    }

    /**
     * 分页查询操作日志列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 操作日志分页列表
     */
    public Page<LogOperate> page(LogOperate entity, PageQuery pageQuery) {
        return logOperateRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的操作日志列表
     *
     * @param entity 查询条件
     * @return 操作日志列表
     */
    public List<LogOperate> list(LogOperate entity) {
        return logOperateRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存操作日志
     *
     * @param entity 操作日志
     */
    public void save(LogOperate entity) {
        validate(entity);
        logOperateRepository.save(entity);
    }

    /**
     * 批量保存操作日志
     *
     * @param list 操作日志集合
     */
    public void save(List<LogOperate> list) {
        for (LogOperate entity : list) {
            validate(entity);
        }
        logOperateRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统操作日志
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        logOperateRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<LogOperate> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            list = list.stream()
                       .peek(item -> {
                       })
                       .toList();
            save(list);
        }
    }

    /**
     * 数据校验
     *
     * @param entity 操作日志
     */
    private void validate(LogOperate entity) {
    }

    private Specification<LogOperate> buildSpecification(LogOperate entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                equal("userId", entity.getUserId()),
                like("title", entity.getTitle()),
                like("method", entity.getMethod()),
                like("requestMethod", entity.getRequestMethod()),
                like("url", entity.getUrl()),
                like("ip", entity.getIp()),
                like("location", entity.getLocation()),
                between("time", entity.getTimeStart(), entity.getTimeEnd())
        );
    }
}
