package cn.sijay.suap.data.service.impl;

import cn.sijay.suap.core.entity.Ids;
import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.util.BeanUtil;
import cn.sijay.suap.core.util.ExcelUtil;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.data.entity.DataDict;
import cn.sijay.suap.data.mapper.DataDictMapper;
import cn.sijay.suap.data.service.IDataDictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>DataDictService 数据字典服务层实现类</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DataDictServiceImpl extends ServiceImpl<DataDictMapper, DataDict> implements IDataDictService {

    private final DataDictMapper dataDictMapper;

    /**
     * 分页查询
     *
     * @param entity    实体类
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public Page<DataDict> page(DataDict entity, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(entity, DataDict.class));
    }

    /**
     * 新增
     *
     * @param entity 实体类
     * @return 新增结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean add(DataDict entity) {
        if (checkUnique(entity)) {
            return save(entity);
        }
        return false;
    }

    /**
     * 更新
     *
     * @param entity 实体类
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(DataDict entity) {
        if (checkUnique(entity) && exists(entity.getId())) {
            DataDict dataDict = getById(entity.getId());
            BeanUtil.copyEntityProperties(entity, dataDict);
            return updateById(dataDict);
        }
        return false;
    }

    /**
     * 删除
     *
     * @param ids id
     * @return 删除结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(Ids<Long> ids) {
        if (ObjectUtils.isNotEmpty(ids.getId())) {
            if (exists(ids.getId())) {
                return removeById(ids.getId());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, ids.getId());
            }
        } else if (CollectionUtils.isNotEmpty(ids.getIds())) {
            List<Long> list = ids.getIds().stream().filter(id -> !exists(id)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(list)) {
                return removeByIds(ids.getIds());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, list);
            }
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String importData(MultipartFile file) {
        int successCount = 0;
        int failCount = 0;
        for (DataDict entity : ExcelUtil.read(file, DataDict.class)) {
            if (add(entity)) {
                successCount++;
            } else {
                failCount++;
            }
        }
        log.info("导入成功{}条，失败{}条", successCount, failCount);
        return StringUtil.format("导入成功{}条，失败{}条", successCount, failCount);
    }

    @Override
    public DataDict getTreeDataByCode(String code) {
        DataDict dataDict = getByCode(code);
        List<DataDict> children = listChildren(dataDict.getId());
        dataDict.setChildren(children);
        return dataDict;
    }

    @Override
    public DataDict getByCode(String code) {
        return getOne(new LambdaQueryWrapper<DataDict>().eq(DataDict::getCode, code));
    }

    @Override
    public List<DataDict> listChildren(Long parentId) {
        return list(new LambdaQueryWrapper<DataDict>().eq(DataDict::getParentId, parentId));
    }

    @Override
    public List<Option<String>> getOptions(String code) {
        DataDict dataDict = getByCode(code);
        return listChildren(dataDict.getId()).stream().map(item -> new Option<>(item.getName(), item.getCode())).toList();
    }

    @Override
    public List<Option<Long>> getOptionData() {
        return list()
                .stream().map(item -> new Option<>(item.getName(), item.getId()))
                .collect(Collectors.toList());
    }

}
