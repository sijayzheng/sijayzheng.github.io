/**
 * 系统字典定义
 */
export interface SystemDict extends BaseEntity {
  code?: string
  name?: string
}

/**
 * 系统字典接口
 */
export const systemDictApi = {
  // 查询系统字典
  getById(id: number): AxiosPromise<SystemDict> {
    return request({ url: '/systemDict/getById', method: 'get', params: { id } })
  },
  // 分页查询系统字典列表
  page(params: SystemDict): AxiosPromise<SystemDict> {
    return request({ url: '/systemDict/page', method: 'get', params })
  },
  // 查询系统字典列表
  list(params: SystemDict): AxiosPromise<SystemDict> {
    return request({ url: '/systemDict/list', method: 'get', params })
  },
  // 保存系统字典
  save(data: SystemDict): AxiosPromise<SystemDict> {
    return request({ url: '/systemDict/save', method: 'post', data })
  },
  // 批量删除系统字典信息
  remove(data: number[]): AxiosPromise<SystemDict> {
    return request({ url: '/systemDict/remove', method: 'delete', data })
  },
  // 导出系统字典
  exportData(data: SystemDict) {
    download('/systemDict/export', data, `系统字典_${getNowDate('_')}.xlsx`)
  },
  // 获取系统字典导入模板
  downloadTemplate() {
    downloadTemplate('/systemDict/downloadTemplate', `系统字典_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemDict/importData',
}
