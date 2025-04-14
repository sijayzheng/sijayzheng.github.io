package cn.sijay.bun.gen.service;

import cn.sijay.bun.gen.entity.GenTable;
import cn.sijay.bun.gen.repository.GenTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <strong>IGenTableService</strong>
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
public class GenTableService {
    private final GenTableRepository genTableRepository;

    public List<GenTable> getAllTable() {
        return genTableRepository.getAllTable();
    }

    public List<GenTable> findAll() {
        return genTableRepository.findAll();
    }

    public List<GenTable> findAll(Specification<GenTable> spec) {
        return genTableRepository.findAll(spec);
    }

    public GenTable getById(Long id) {
        return genTableRepository.getReferenceById(id);
    }

    public GenTable save(GenTable entity) {
        return genTableRepository.save(entity);
    }
}
