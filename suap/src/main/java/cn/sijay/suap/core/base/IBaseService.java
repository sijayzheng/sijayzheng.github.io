package cn.sijay.suap.core.base;

import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.entity.Ids;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.enums.QueryType;
import cn.sijay.suap.core.util.LogUtil;
import cn.sijay.suap.core.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * <p>
 * <em>IBaseService</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 16:37
 */
public interface IBaseService<T> extends IService<T> {
    /**
     * 分页查询
     *
     * @param entity    实体类
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    Page<T> page(T entity, PageQuery pageQuery);

    /**
     * 构建查询条件构造器
     *
     * @param entity      实体类
     * @param entityClass 实体类类型
     * @return 查询条件构造器
     */
    default QueryWrapper<T> buildQueryWrapper(T entity, Class<T> entityClass) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (Field field : entityClass.getDeclaredFields()) {
            QueryColumn annotation = field.getAnnotation(QueryColumn.class);
            String fieldName = field.getName();
            if (annotation != null) {
                String columnName = StringUtil.toUnderlineCase(fieldName);
                System.out.println(columnName);
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(entity);
                } catch (IllegalAccessException e) {
                    LogUtil.warnLog("字段{}取值错误", columnName);
                }
                if (value != null) {
                    if (Objects.requireNonNull(annotation.value()) == QueryType.LIKE) {
                        wrapper.like(columnName, value);
                    } else if (annotation.value() == QueryType.EQUAL) {
                        wrapper.eq(columnName, value);
                    } else if (annotation.value() == QueryType.GREATER_THAN) {
                        wrapper.gt(columnName, value);
                    } else if (annotation.value() == QueryType.GREATER_OR_EQUAL) {
                        wrapper.ge(columnName, value);
                    } else if (annotation.value() == QueryType.LESS_THAN) {
                        wrapper.lt(columnName, value);
                    } else if (annotation.value() == QueryType.LESS_OR_EQUAL) {
                        wrapper.le(columnName, value);
                    } else if (annotation.value() == QueryType.BETWEEN) {
                        try {
                            Field startField = entityClass.getDeclaredField(fieldName + "Start");
                            Field endField = entityClass.getDeclaredField(fieldName + "End");
                            startField.setAccessible(true);
                            endField.setAccessible(true);
                            wrapper.between(columnName, startField.get(entity), endField.get(entity));
                        } catch (NoSuchFieldException e) {
                            LogUtil.warnLog("字段{}或{}不存在", fieldName + "Start", fieldName + "End");
                        } catch (IllegalAccessException e) {
                            LogUtil.warnLog("字段{}或{}取值错误", fieldName + "Start", fieldName + "End");
                        }
                    } else if (annotation.value() == QueryType.IN) {
                        wrapper.in(columnName, StringUtil.toList(String.valueOf(value)));
                    }
                }
            }
            if (fieldName.equals("sort")) {
                wrapper.orderByAsc("sort");
            }
        }
        return wrapper;
    }

    /**
     * 新增
     *
     * @param entity 实体类
     * @return 新增结果，true为成功，false为失败
     */
    boolean add(T entity);

    /**
     * 更新
     *
     * @param entity 实体类
     * @return 更新结果，true为成功，false为失败
     */
    boolean update(T entity);

    /**
     * 删除
     *
     * @param id id
     * @return 删除结果，true为成功，false为失败
     */
    boolean remove(Ids<Long> id);

}
