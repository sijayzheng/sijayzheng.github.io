package cn.sijay.suap.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <strong>SelectOption</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Option<T> {
    private String label;
    private T value;
    private List<Option<T>> children;

    public Option(String label, T value) {
        this.label = label;
        this.value = value;
    }

    public Option append(Option<T> option) {
        this.children.add(option);
        return this;
    }
}
