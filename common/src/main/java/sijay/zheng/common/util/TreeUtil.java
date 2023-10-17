/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeUtil {
    /**
     * 生成树形结构
     */
    public static <T, K> List<TreeNode<K>> buildTree(List<T> list, K pid, ParseToTreeNode<T, K> parse) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<TreeNode<K>> results = new ArrayList<>();
        for (T t : list) {
            TreeNode<K> node = new TreeNode<>();
            parse.parse(t, node);
            if (null != pid && !pid.getClass().equals(node.getId().getClass())) {
                throw new IllegalArgumentException("rootId type is node.getId().getClass()!");
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

}
