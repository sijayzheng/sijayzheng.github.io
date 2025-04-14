/**
 * 系统菜单定义
 */
export interface SystemMenu extends BaseEntity {
  parentId?: number
  name?: string
  permsCode?: string
  sort?: number
  path?: string
  component?: string
  type?: string
  externalLink?: boolean
  cacheable?: boolean
  visible?: boolean
  enable?: boolean
  icon?: string
}

/**
 * 系统菜单接口
 */
export const systemMenuApi = {
  // 查询系统菜单
  getById(id: number): AxiosPromise<SystemMenu> {
    return request({ url: '/systemMenu/getById', method: 'get', params: { id } })
  },
  // 分页查询系统菜单列表
  page(params: SystemMenu): AxiosPromise<SystemMenu> {
    return request({ url: '/systemMenu/page', method: 'get', params })
  },
  // 查询系统菜单列表
  list(params: SystemMenu): AxiosPromise<SystemMenu> {
    return request({ url: '/systemMenu/list', method: 'get', params })
  },
  // 保存系统菜单
  save(data: SystemMenu): AxiosPromise<SystemMenu> {
    return request({ url: '/systemMenu/save', method: 'post', data })
  },
  // 批量删除系统菜单信息
  remove(data: number[]): AxiosPromise<SystemMenu> {
    return request({ url: '/systemMenu/remove', method: 'delete', data })
  },
  // 导出系统菜单
  exportData(data: SystemMenu) {
    download('/systemMenu/export', data, `系统菜单_${getNowDate('_')}.xlsx`)
  },
  // 获取系统菜单导入模板
  downloadTemplate() {
    downloadTemplate('/systemMenu/downloadTemplate', `系统菜单_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemMenu/importData',
}
