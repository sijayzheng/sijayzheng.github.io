package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.enums.QueryType;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <strong>SpecificationUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificationUtil {

    public static <T> Specification<T> buildSpecification(T entity, Class<T> clazz) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Field field : clazz.getDeclaredFields()) {
                QueryColumn annotation = field.getAnnotation(QueryColumn.class);
                String fieldName = field.getName();
                if (annotation != null) {
                    String columnName = StringUtil.toLowerSnakeCase(fieldName);
                    field.setAccessible(true);
                    try {
                        Object obj = field.get(entity);
                        if (obj != null) {
                            String value = String.valueOf(obj);
                            if (Objects.requireNonNull(annotation.value()) == QueryType.like) {
                                predicates.add(builder.like(root.get(columnName), "%" + value + "%"));
                            } else if (annotation.value() == QueryType.equal) {
                                predicates.add(builder.equal(root.get(columnName), value));
                            } else if (annotation.value() == QueryType.greater_than) {
                                predicates.add(builder.greaterThan(root.get(columnName), Double.valueOf(value)));
                            } else if (annotation.value() == QueryType.greater_or_equal) {
                                predicates.add(builder.greaterThanOrEqualTo(root.get(columnName), Double.valueOf(value)));
                            } else if (annotation.value() == QueryType.less_than) {
                                predicates.add(builder.lessThan(root.get(columnName), Double.valueOf(value)));
                            } else if (annotation.value() == QueryType.less_or_equal) {
                                predicates.add(builder.lessThanOrEqualTo(root.get(columnName), Double.valueOf(value)));
                            } else if (annotation.value() == QueryType.between) {
                                try {
                                    Field startField = clazz.getDeclaredField(fieldName + "Start");
                                    Field endField = clazz.getDeclaredField(fieldName + "End");
                                    startField.setAccessible(true);
                                    endField.setAccessible(true);
                                    Object startObj = startField.get(entity);
                                    Object endObj = endField.get(entity);
                                    if (startObj != null && endObj != null) {
                                        predicates.add(builder.between(root.get(columnName), String.valueOf(startObj), String.valueOf(endObj)));
                                    }
                                } catch (NoSuchFieldException e) {
                                    log.error("字段{}或{}不存在", fieldName + "Start", fieldName + "End");
                                } catch (IllegalAccessException e) {
                                    log.error("字段{}或{}取值错误", fieldName + "Start", fieldName + "End");
                                }
                            } else if (annotation.value() == QueryType.in) {
                                predicates.add(builder.in(root.get("userId")).value(value));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        log.error("字段{}取值错误", columnName);
                    }
                }
            }
            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
