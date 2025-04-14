package cn.sijay.biu.file.repository;

import cn.sijay.biu.file.entity.FileStorage;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * FileStorageRepository
 * 文件存储数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface FileStorageRepository extends JpaRepositoryImplementation<FileStorage, Long> {

}
