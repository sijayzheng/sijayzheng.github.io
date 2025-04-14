package cn.sijay.demos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <strong>SystemMessageService</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-12
 */
@RequiredArgsConstructor
@Service
public class SystemMessageService {
    private final SystemMessageRepository repository;

    public List<SystemMessage> findAll(Specification<SystemMessage> notice) {
        return repository.findAll(notice);
    }
}
