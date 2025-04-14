package cn.sijay.biu.core.base;

import cn.sijay.biu.core.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * BaseService
 *
 * @author Sijay
 * @since 2025-03-05
 */
public interface BaseService {

    static boolean isNotBlank(String value) {
        return StringUtil.isNotBlank(value);
    }

    private static <T> boolean notNull(T value) {
        return !Objects.isNull(value);
    }

    default <T> Specification<T> conjunction() {
        return (root, query, builder) -> builder.conjunction();
    }

    default <T> Specification<T> like(String field, String value) {
        return isNotBlank(value) ? ((root, query, builder) -> builder.like(root.get(field), like(value))) : conjunction();
    }

    default String like(String value) {
        return "%" + StringUtil.defaultString(value) + "%";
    }

    default <T> Specification<T> equal(String field, String value) {
        return isNotBlank(value) ? ((root, query, builder) -> builder.equal(root.get(field), value)) : conjunction();
    }

    default <T> Specification<T> equal(String field, Long value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, Integer value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, BigDecimal value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, Character value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, LocalDateTime value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, LocalDate value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, LocalTime value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, Boolean value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, Float value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, Double value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> equal(String field, Object value) {
        return notNull(value) ? (root, query, builder) -> builder.equal(root.get(field), value) : conjunction();
    }

    default <T> Specification<T> between(String field, LocalDateTime left, LocalDateTime right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }

    default <T> Specification<T> between(String field, LocalDate left, LocalDate right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }

    default <T> Specification<T> between(String field, LocalTime left, LocalTime right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }

    default <T> Specification<T> between(String field, Long left, Long right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }

    default <T> Specification<T> between(String field, Integer left, Integer right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }

    default <T> Specification<T> between(String field, Float left, Float right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }

    default <T> Specification<T> between(String field, Double left, Double right) {
        return notNull(left) && notNull(right) ? (root, query, builder) -> builder.between(root.get(field), left, right) : conjunction();
    }
}
