package cn.sijay.suap.data.service;

import cn.sijay.suap.core.base.IBaseService;
import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.data.entity.DataDict;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * <em>IDataDictService 数据字典服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface IDataDictService extends IService<DataDict>, IBaseService<DataDict> {

    /**
     * 唯一性校验
     *
     * @param entity 待校验的对象
     * @return true-校验通过，false-校验不通过
     */
    default boolean checkUnique(DataDict entity) {
        if (StringUtil.isNotBlank(entity.getCode())) {
            QueryWrapper<DataDict> wrapper = new QueryWrapper<>();
            wrapper.eq("code", entity.getCode());
            if (ObjectUtils.isNotEmpty(entity.getId())) {
                wrapper.ne("id", entity.getId());
            }
            if (exists(wrapper)) {
                throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "code", entity.getCode());
            }
        }
        return true;
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     * @return true-存在，false-不存在
     */
    default boolean exists(Long id) {
        return exists(new LambdaQueryWrapper<DataDict>().eq(DataDict::getId, id));
    }

    /**
     * 构建查询条件构造器
     *
     * @param entity 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<DataDict> buildWrapper(DataDict entity) {
        LambdaQueryWrapper<DataDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(entity.getName()), DataDict::getName, entity.getName());
        wrapper.like(StringUtil.isNotBlank(entity.getCode()), DataDict::getCode, entity.getCode());
        return wrapper;
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    String importData(MultipartFile file);

    DataDict getTreeDataByCode(String code);

    DataDict getByCode(String code);

    List<DataDict> listChildren(Long parentId);

    List<Option<String>> getOptions(String code);

    List<Option<Long>> getOptionData();
}
