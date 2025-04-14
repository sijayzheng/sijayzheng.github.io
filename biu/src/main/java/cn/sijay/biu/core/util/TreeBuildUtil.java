package cn.sijay.biu.core.util;

import cn.sijay.biu.core.entity.TreeNode;
import cn.sijay.biu.core.exception.UtilException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * 扩展 hutool TreeUtil 封装系统树构建
 *
 * @author Sijay
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeBuildUtil {

    /**
     * 集合转树集合
     *
     * @param list           集合
     * @param parentId       父id
     * @param idGetter       元素id getter
     * @param parentIdGetter 元素父id getter
     * @param childrenSetter 元素子类setter
     * @param <T>            集合元素类型
     * @param <K>            元素id类型
     * @return 树集合
     */
    public static <T, K> List<T> listToTree(List<T> list, K parentId, Function<T, K> idGetter, Function<T, K> parentIdGetter, ISetter<T, List<T>> childrenSetter) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        List<K> parentIds = list.stream()
                                .map(parentIdGetter)
                                .toList();
        for (T t : list.stream()
                       .filter(e -> parentIds.contains(parentId))
                       .toList()) {
            if (parentIdGetter.apply(t)
                              .equals(parentId)) {
                result.add(t);
                childrenSetter.apply(t, listToTree(list, idGetter.apply(t), idGetter, parentIdGetter, childrenSetter));
            }
        }
        return result.isEmpty() ? Collections.emptyList() : result;
    }

    /**
     * 集合转树集合
     *
     * @param list     集合
     * @param parentId 父id
     * @param parse    集合元素转数节点方法
     * @param <T>      集合元素类型
     * @param <K>      元素id类型
     * @return 树集合
     */
    public static <T, K> List<TreeNode<K>> buildTree(List<T> list, K parentId, TreeNodeConvert<T, K> parse) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<TreeNode<K>> results = new ArrayList<>();
        for (T t : list) {
            TreeNode<K> node = new TreeNode<>();
            parse.convert(t, node);
            if (null != parentId && !parentId.getClass().equals(node.getId().getClass())) {
                throw new UtilException("父id为null或类型错误");
            }
            results.add(node);
        }
        return createTree(results, parentId);
    }

    private static <K> List<TreeNode<K>> createTree(List<TreeNode<K>> list, K parentId) {
        List<TreeNode<K>> result = new ArrayList<>();
        for (TreeNode<K> t : list.stream().filter(e -> Objects.equals(e.getParentId(), parentId)).toList()) {
            if (t.getParentId()
                 .equals(parentId)) {
                result.add(t);
                t.setChildren(createTree(list, t.getId()));
            }
        }
        return result.isEmpty() ? Collections.emptyList() : result;
    }

    public interface ISetter<T, CH> {
        void apply(T t, CH r);
    }

    /**
     * TreeNodeConvert
     * <p>
     *
     * </p>
     *
     * @author Sijay
     * @since 2024-07-18
     */
    public interface TreeNodeConvert<T, K> {
        /**
         * @param entity   实体类
         * @param treeNode 树节点
         */
        void convert(T entity, TreeNode<K> treeNode);
    }
}
