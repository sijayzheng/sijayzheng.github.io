package cn.sijay.bun.common.util;

import cn.sijay.bun.core.function.SerialFunction;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * <strong>Spec</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-12-11
 */
@NoArgsConstructor
public class Spec {

    public static <T> Specification<T> equal(SerialFunction<T, ?> getter, Object value) {
        return (root, query, builder) -> builder.equal(root.get(getter.getFieldName()), value);
    }

    public static <T> Specification<T> like(SerialFunction<T, ?> getter, Object value) {
        return (root, query, builder) -> builder.like(root.get(getter.getFieldName()), "%" + value + "%");
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> between(SerialFunction<T, ?> getter, Y start, Y end) {
        return (root, query, builder) -> builder.between(root.get(getter.getFieldName()), start, end);
    }

}
