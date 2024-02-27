package cn.sijay.suap.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * <em>Option</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/25 14:35
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
}
