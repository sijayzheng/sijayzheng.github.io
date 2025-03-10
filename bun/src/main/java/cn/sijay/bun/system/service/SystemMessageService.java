package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemMessage;
import cn.sijay.bun.system.repository.SystemMessageRepository;
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
 * <strong>SystemMessageService</strong>
 * <p>
 * 系统消息
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemMessageService {
    private final SystemMessageRepository systemMessageRepository;

    /**
     * 查询系统消息
     *
     * @param id 主键
     * @return 系统消息
     */
    public SystemMessage findById(Long id) {
        return systemMessageRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统消息列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统消息分页列表
     */
    public ResponseResult<SystemMessage> page(SystemMessage entity, PageQuery pageQuery) {
        Specification<SystemMessage> specification = buildSpecification(entity);
        Page<SystemMessage> page = systemMessageRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统消息列表
     *
     * @param entity 查询条件
     * @return 系统消息列表
     */
    public List<SystemMessage> list(SystemMessage entity) {
        Specification<SystemMessage> specification = buildSpecification(entity);
        return systemMessageRepository.findAll(specification);
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
        List<Specification<SystemMessage>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getTitle())) {
            list.add(Spec.like(SystemMessage::getTitle, entity.getTitle()));
        }
//        if(StringUtils.isNotBlank(entity.getType())) {
//            list.add(Spec.like(SystemMessage::getType, entity.getType()));
//        }
        if (StringUtils.isNotBlank(entity.getContent())) {
            list.add(Spec.like(SystemMessage::getContent, entity.getContent()));
        }
        if (ObjectUtils.isNotEmpty(entity.getClosed())) {
            list.add(Spec.equal(SystemMessage::getClosed, entity.getClosed()));
        }
        if (ObjectUtils.isNotEmpty(entity.getPublisher())) {
            list.add(Spec.equal(SystemMessage::getPublisher, entity.getPublisher()));
        }
        return Specification.allOf(list);
    }
}
