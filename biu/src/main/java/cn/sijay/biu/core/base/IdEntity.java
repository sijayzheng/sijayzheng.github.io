package cn.sijay.biu.core.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * IdEntity
 *
 * @author Sijay
 * @since 2025-03-13
 */
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class IdEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3974079746569537798L;

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    @Column(name = "id", nullable = false)
    private Long id;
}
