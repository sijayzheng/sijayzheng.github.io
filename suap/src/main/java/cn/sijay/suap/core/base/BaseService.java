package cn.sijay.suap.core.base;

import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.Triple;
import cn.sijay.suap.core.enums.QueryType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <strong>BaseService</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface BaseService<T> {
    default Specification<T> buildSpecification(T entity) {
        Class<?> clazz = entity.getClass();
        List<Triple<QueryType, String, String>> list = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            QueryColumn annotation = field.getAnnotation(QueryColumn.class);
            if (annotation != null) {
                String fieldName = field.getName();
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(entity);
                    if (!Objects.isNull(fieldValue)) {
                        list.add(new Triple<>(annotation.value(), fieldName, String.valueOf(fieldValue)));
                    }
                } catch (IllegalAccessException e) {
                    throw new BaseException(ExceptionConstant.REFLECT_ERROR, clazz.getName());
                }
            }
        }
        return (root, query, builder) -> builder.and(list.stream().map(triple ->
            switch (triple.x()) {
                case like -> builder.like(root.get(triple.y()), "%" + triple.z() + "%");
                case equal -> builder.equal(root.get(triple.y()), triple.z());
                case greater_than -> builder.greaterThan(root.get(triple.y()), triple.z());
                case greater_or_equal -> builder.greaterThanOrEqualTo(root.get(triple.y()), triple.z());
                case less_than -> builder.lessThan(root.get(triple.y()), triple.z());
                case less_or_equal -> builder.lessThanOrEqualTo(root.get(triple.y()), triple.z());
                case between -> builder.between(root.get(triple.y()), triple.z(), triple.z());
                case in -> root.get(triple.y()).in(triple.z());
            }
        ).toArray(Predicate[]::new));
    }

}
