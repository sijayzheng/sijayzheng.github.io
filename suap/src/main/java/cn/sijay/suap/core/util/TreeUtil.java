package cn.sijay.suap.core.util;

import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.entity.TreeNode;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.interfaces.ParseToTreeNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * <em>TreeUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/25 15:21
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeUtil {

    public static <T, K> List<TreeNode<K>> buildTree(List<T> list, K pid, ParseToTreeNode<T, K> parse) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<TreeNode<K>> results = new ArrayList<>();
        for (T t : list) {
            TreeNode<K> node = new TreeNode<>();
            parse.parse(t, node);
            if (null != pid && !pid.getClass().equals(node.getId().getClass())) {
                throw new BaseException("父id为null或类型错误");
            }
            results.add(node);
        }
        return createTree(results, pid);
    }

    private static <K> List<TreeNode<K>> createTree(List<TreeNode<K>> list, K pid) {
        List<TreeNode<K>> result = new ArrayList<>();
        for (TreeNode<K> t : list.stream().filter(e -> Objects.equals(e.getPid(), pid)).toList()) {
            if (t.getPid().equals(pid)) {
                result.add(t);
                t.setChildren(createTree(list, t.getId()));
            }
        }
        return result.isEmpty() ? Collections.emptyList() : result;
    }

    public static <K> List<Option<K>> convertOptionTree(List<TreeNode<K>> list) {
        return list.stream().map(item -> new Option<>(item.getName(), item.getId(), convertOptionTree(item.getChildren()))).toList();
    }

}
