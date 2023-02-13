package sijay.zheng.experience.common.util;

import sijay.zheng.experience.common.entity.*;

import java.util.*;

public class TreeUtils {
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
