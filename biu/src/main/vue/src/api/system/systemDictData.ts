/**
 * 系统字典数据定义
 */
export interface SystemDictData extends BaseEntity {
  dictId?: number
  label?: string
  value?: string
  sort?: number
  className?: string
  showType?: string
  defaultValue?: boolean
}

/**
 * 系统字典数据接口
 */
export const systemDictDataApi = {
  // 查询系统字典数据
  getById(id: number): AxiosPromise<SystemDictData> {
    return request({ url: '/systemDictData/getById', method: 'get', params: { id } })
  },
  // 分页查询系统字典数据列表
  page(params: SystemDictData): AxiosPromise<SystemDictData> {
    return request({ url: '/systemDictData/page', method: 'get', params })
  },
  // 查询系统字典数据列表
  list(params: SystemDictData): AxiosPromise<SystemDictData> {
    return request({ url: '/systemDictData/list', method: 'get', params })
  },
  // 保存系统字典数据
  save(data: SystemDictData): AxiosPromise<SystemDictData> {
    return request({ url: '/systemDictData/save', method: 'post', data })
  },
  // 批量删除系统字典数据信息
  remove(data: number[]): AxiosPromise<SystemDictData> {
    return request({ url: '/systemDictData/remove', method: 'delete', data })
  },
  // 导出系统字典数据
  exportData(data: SystemDictData) {
    download('/systemDictData/export', data, `系统字典数据_${getNowDate('_')}.xlsx`)
  },
  // 获取系统字典数据导入模板
  downloadTemplate() {
    downloadTemplate('/systemDictData/downloadTemplate', `系统字典数据_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemDictData/importData',
}
