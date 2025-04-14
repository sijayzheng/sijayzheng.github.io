package cn.sijay.biu.log.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.log.entity.LogLogin;
import cn.sijay.biu.log.repository.LogLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * LogLoginService
 * 登录日志逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class LogLoginService implements BaseService {
    private final LogLoginRepository logLoginRepository;

    /**
     * 查询登录日志
     *
     * @param id 主键
     * @return 登录日志
     */
    public LogLogin getById(Long id) {
        return logLoginRepository.getReferenceById(id);
    }

    /**
     * 分页查询登录日志列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 登录日志分页列表
     */
    public Page<LogLogin> page(LogLogin entity, PageQuery pageQuery) {
        return logLoginRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的登录日志列表
     *
     * @param entity 查询条件
     * @return 登录日志列表
     */
    public List<LogLogin> list(LogLogin entity) {
        return logLoginRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存登录日志
     *
     * @param entity 登录日志
     */
    public void save(LogLogin entity) {
        validate(entity);
        logLoginRepository.save(entity);
    }

    /**
     * 批量保存登录日志
     *
     * @param list 登录日志集合
     */
    public void save(List<LogLogin> list) {
        for (LogLogin entity : list) {
            validate(entity);
        }
        logLoginRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统登录日志
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        logLoginRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<LogLogin> list) {
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
     * @param entity 登录日志
     */
    private void validate(LogLogin entity) {
    }

    private Specification<LogLogin> buildSpecification(LogLogin entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                equal("userId", entity.getUserId()),
                like("ip", entity.getIp()),
                like("location", entity.getLocation()),
                like("browser", entity.getBrowser()),
                like("os", entity.getOs()),
                equal("successful", entity.getSuccessful()),
                between("time", entity.getTimeStart(), entity.getTimeEnd())
        );
    }
}
