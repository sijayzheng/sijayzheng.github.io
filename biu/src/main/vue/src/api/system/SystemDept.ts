/**
 * 系统部门定义
 */
export interface SystemDept extends BaseEntity {
  name?: string
  parentId?: number
  ancestors?: number[]
  sort?: number
  leader?: number
  phone?: string
  email?: string
  enable?: boolean
}

/**
 * 系统部门接口
 */
export const systemDeptApi = {
  // 查询系统部门
  getById(id: number): AxiosPromise<SystemDept> {
    return request({ url: '/systemDept/getById', method: 'get', params: { id } })
  },
  // 分页查询系统部门列表
  page(params: SystemDept): AxiosPromise<SystemDept> {
    return request({ url: '/systemDept/page', method: 'get', params })
  },
  // 查询系统部门列表
  list(params: SystemDept): AxiosPromise<SystemDept> {
    return request({ url: '/systemDept/list', method: 'get', params })
  },
  // 保存系统部门
  save(data: SystemDept): AxiosPromise<SystemDept> {
    return request({ url: '/systemDept/save', method: 'post', data })
  },
  // 批量删除系统部门信息
  remove(data: number[]): AxiosPromise<SystemDept> {
    return request({ url: '/systemDept/remove', method: 'delete', data })
  },
  // 导出系统部门
  exportData(data: SystemDept) {
    download('/systemDept/export', data, `系统部门_${getNowDate('_')}.xlsx`)
  },
  // 获取系统部门导入模板
  downloadTemplate() {
    downloadTemplate('/systemDept/downloadTemplate', `系统部门_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemDept/importData',
}
