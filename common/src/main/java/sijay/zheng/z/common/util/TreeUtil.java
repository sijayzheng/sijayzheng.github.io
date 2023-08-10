/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

import lombok.NoArgsConstructor;
import sijay.zheng.z.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TreeUtil {
    /**
     * 生成树形结构
     */
    public static <T extends TreeNode<T>> List<T> createTree(List<T> list,
                                                             Integer pid) {
        List<T> result = new ArrayList<>();
        for (T t : list.stream().filter(e -> Objects.equals(e.getPid(), pid)).toList()) {
            if (t.getPid().equals(pid)) {
                result.add(t);
                t.setChildren(createTree(list, t.getId()));
            }
        }
        return result.isEmpty() ? Collections.emptyList() : result;
    }

}
