package cn.sijay.biu.file.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.file.entity.FileStorage;
import cn.sijay.biu.file.repository.FileStorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FileStorageService
 * 文件存储逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class FileStorageService implements BaseService {
    private final FileStorageRepository fileStorageRepository;

    /**
     * 查询文件存储
     *
     * @param id 主键
     * @return 文件存储
     */
    public FileStorage getById(Long id) {
        return fileStorageRepository.getReferenceById(id);
    }

    /**
     * 分页查询文件存储列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 文件存储分页列表
     */
    public Page<FileStorage> page(FileStorage entity, PageQuery pageQuery) {
        return fileStorageRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的文件存储列表
     *
     * @param entity 查询条件
     * @return 文件存储列表
     */
    public List<FileStorage> list(FileStorage entity) {
        return fileStorageRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存文件存储
     *
     * @param entity 文件存储
     */
    public void save(FileStorage entity) {
        validate(entity);
        fileStorageRepository.save(entity);
    }

    /**
     * 批量保存文件存储
     *
     * @param list 文件存储集合
     */
    public void save(List<FileStorage> list) {
        for (FileStorage entity : list) {
            validate(entity);
        }
        fileStorageRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统文件存储
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        fileStorageRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<FileStorage> list) {
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
     * @param entity 文件存储
     */
    private void validate(FileStorage entity) {
    }

    private Specification<FileStorage> buildSpecification(FileStorage entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("originalName", entity.getOriginalName())
        );
    }
}
