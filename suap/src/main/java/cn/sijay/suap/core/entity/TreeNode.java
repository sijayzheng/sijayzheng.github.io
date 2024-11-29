package cn.sijay.suap.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <strong>TreeNode</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TreeNode<K> {
    private K id;
    private K value;
    private K parentId;
    private String label;
    private int sort;
    private List<TreeNode<K>> children;

    public TreeNode(String label, K id) {
        this.id = id;
        this.value = id;
        this.label = label;
    }

    public TreeNode<K> setId(K id) {
        this.id = id;
        this.value = id;
        return this;
    }
}

