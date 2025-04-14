/**
 * 系统模块定义
 */
export interface SystemModule extends BaseEntity {
  code?: string
  name?: string
  roles?: number[]
  menus?: number[]
  enable?: boolean
}

/**
 * 系统模块接口
 */
export const systemModuleApi = {
  // 查询系统模块
  getById(id: number): AxiosPromise<SystemModule> {
    return request({ url: '/systemModule/getById', method: 'get', params: { id } })
  },
  // 分页查询系统模块列表
  page(params: SystemModule): AxiosPromise<SystemModule> {
    return request({ url: '/systemModule/page', method: 'get', params })
  },
  // 查询系统模块列表
  list(params: SystemModule): AxiosPromise<SystemModule> {
    return request({ url: '/systemModule/list', method: 'get', params })
  },
  // 保存系统模块
  save(data: SystemModule): AxiosPromise<SystemModule> {
    return request({ url: '/systemModule/save', method: 'post', data })
  },
  // 批量删除系统模块信息
  remove(data: number[]): AxiosPromise<SystemModule> {
    return request({ url: '/systemModule/remove', method: 'delete', data })
  },
  // 导出系统模块
  exportData(data: SystemModule) {
    download('/systemModule/export', data, `系统模块_${getNowDate('_')}.xlsx`)
  },
  // 获取系统模块导入模板
  downloadTemplate() {
    downloadTemplate('/systemModule/downloadTemplate', `系统模块_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemModule/importData',
}
