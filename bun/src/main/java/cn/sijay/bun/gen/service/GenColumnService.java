package cn.sijay.bun.gen.service;

import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.gen.entity.GenColumn;
import cn.sijay.bun.gen.repository.GenColumnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <strong>IGenColumnService</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-19
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class GenColumnService {
    private final GenColumnRepository genColumnRepository;

    public List<GenColumn> getColumnByTableName(String tableName) {
        return genColumnRepository.getColumnByTableName(tableName);
    }

    public List<GenColumn> getColumnByTableId(Long tableId) {
        return genColumnRepository.findAll(Spec.equal(GenColumn::getTableId, tableId));
    }

    public List<GenColumn> findAll() {
        return genColumnRepository.findAll();
    }

    public List<GenColumn> findAll(Specification<GenColumn> spec) {
        return genColumnRepository.findAll(spec);
    }

    public GenColumn getById(Long id) {
        return genColumnRepository.getReferenceById(id);
    }

    public GenColumn save(GenColumn entity) {
        return genColumnRepository.save(entity);
    }
}
