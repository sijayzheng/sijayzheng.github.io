package cn.sijay.biu.file.entity;

import cn.sijay.biu.core.base.BaseEntity;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Comment;

/**
 * FileStorage
 * 文件存储实体类
 *
 * @author Sijay
 * @since 2025-04-14
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("文件存储")
@Entity
@Table(name = "file_storage")
public class FileStorage extends BaseEntity {

    /**
     * 文件名
     */
    @Size(max = 255, message = "文件名最大长度为{max}")
    @NotNull
    @Comment("文件名")
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    /**
     * 原名
     */
    @Size(max = 255, message = "原名最大长度为{max}")
    @NotNull
    @Comment("原名")
    @Column(name = "original_name", nullable = false, unique = true, length = 255)
    private String originalName;

    /**
     * 文件后缀名
     */
    @Size(max = 10, message = "文件后缀名最大长度为{max}")
    @NotNull
    @Comment("文件后缀名")
    @Column(name = "suffix", nullable = false, unique = true, length = 10)
    private String suffix;

    /**
     * 文件访问地址
     */
    @Size(max = 255, message = "文件访问地址最大长度为{max}")
    @NotNull
    @Comment("文件访问地址")
    @Column(name = "url", nullable = false, unique = true, length = 255)
    private String url;

    /**
     * 文件存储路径
     */
    @Size(max = 255, message = "文件存储路径最大长度为{max}")
    @NotNull
    @Comment("文件存储路径")
    @Column(name = "store_path", nullable = false, unique = true, length = 255)
    private String storePath;

}
