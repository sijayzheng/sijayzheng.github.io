/**
 * 系统配置定义
 */
export interface SystemConfig extends BaseEntity {
  name?: string
  code?: string
  value?: string
  internal?: boolean
}

/**
 * 系统配置接口
 */
export const systemConfigApi = {
  // 查询系统配置
  getById(id: number): AxiosPromise<SystemConfig> {
    return request({ url: '/systemConfig/getById', method: 'get', params: { id } })
  },
  // 分页查询系统配置列表
  page(params: SystemConfig): AxiosPromise<SystemConfig> {
    return request({ url: '/systemConfig/page', method: 'get', params })
  },
  // 查询系统配置列表
  list(params: SystemConfig): AxiosPromise<SystemConfig> {
    return request({ url: '/systemConfig/list', method: 'get', params })
  },
  // 保存系统配置
  save(data: SystemConfig): AxiosPromise<SystemConfig> {
    return request({ url: '/systemConfig/save', method: 'post', data })
  },
  // 批量删除系统配置信息
  remove(data: number[]): AxiosPromise<SystemConfig> {
    return request({ url: '/systemConfig/remove', method: 'delete', data })
  },
  // 导出系统配置
  exportData(data: SystemConfig) {
    download('/systemConfig/export', data, `系统配置_${getNowDate('_')}.xlsx`)
  },
  // 获取系统配置导入模板
  downloadTemplate() {
    downloadTemplate('/systemConfig/downloadTemplate', `系统配置_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemConfig/importData',
}
