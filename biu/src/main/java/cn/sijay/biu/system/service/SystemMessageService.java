package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemMessage;
import cn.sijay.biu.system.repository.SystemMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemMessageService
 * 系统消息逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemMessageService implements BaseService {
    private final SystemMessageRepository systemMessageRepository;

    /**
     * 查询系统消息
     *
     * @param id 主键
     * @return 系统消息
     */
    public SystemMessage getById(Long id) {
        return systemMessageRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统消息列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统消息分页列表
     */
    public Page<SystemMessage> page(SystemMessage entity, PageQuery pageQuery) {
        return systemMessageRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统消息列表
     *
     * @param entity 查询条件
     * @return 系统消息列表
     */
    public List<SystemMessage> list(SystemMessage entity) {
        return systemMessageRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存系统消息
     *
     * @param entity 系统消息
     */
    public void save(SystemMessage entity) {
        validate(entity);
        systemMessageRepository.save(entity);
    }

    /**
     * 批量保存系统消息
     *
     * @param list 系统消息集合
     */
    public void save(List<SystemMessage> list) {
        for (SystemMessage entity : list) {
            validate(entity);
        }
        systemMessageRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统消息
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemMessageRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemMessage> list) {
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
     * @param entity 系统消息
     */
    private void validate(SystemMessage entity) {
    }

    private Specification<SystemMessage> buildSpecification(SystemMessage entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("title", entity.getTitle()),
                equal("type", entity.getType()),
                equal("closed", entity.getClosed()),
                equal("publisher", entity.getPublisher())
        );
    }
}
