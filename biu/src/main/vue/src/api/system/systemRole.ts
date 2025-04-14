/**
 * 系统权限定义
 */
export interface SystemRole extends BaseEntity {
  code?: string
  name?: string
  sort?: number
  enable?: boolean
  menus?: number[]
  menuCheckStrictly?: boolean
  departments?: number[]
  deptCheckStrictly?: boolean
  dataScope?: string
}

/**
 * 系统权限接口
 */
export const systemRoleApi = {
  // 查询系统权限
  getById(id: number): AxiosPromise<SystemRole> {
    return request({ url: '/systemRole/getById', method: 'get', params: { id } })
  },
  // 分页查询系统权限列表
  page(params: SystemRole): AxiosPromise<SystemRole> {
    return request({ url: '/systemRole/page', method: 'get', params })
  },
  // 查询系统权限列表
  list(params: SystemRole): AxiosPromise<SystemRole> {
    return request({ url: '/systemRole/list', method: 'get', params })
  },
  // 保存系统权限
  save(data: SystemRole): AxiosPromise<SystemRole> {
    return request({ url: '/systemRole/save', method: 'post', data })
  },
  // 批量删除系统权限信息
  remove(data: number[]): AxiosPromise<SystemRole> {
    return request({ url: '/systemRole/remove', method: 'delete', data })
  },
  // 导出系统权限
  exportData(data: SystemRole) {
    download('/systemRole/export', data, `系统权限_${getNowDate('_')}.xlsx`)
  },
  // 获取系统权限导入模板
  downloadTemplate() {
    downloadTemplate('/systemRole/downloadTemplate', `系统权限_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemRole/importData',
}
