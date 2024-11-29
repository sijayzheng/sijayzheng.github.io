package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysNotice;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>SysNoticeRepository</strong>
 * <p>
 * 通知公告数据层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Repository
public interface SysNoticeRepository extends JpaRepositoryImplementation<SysNotice, Long> {

}
