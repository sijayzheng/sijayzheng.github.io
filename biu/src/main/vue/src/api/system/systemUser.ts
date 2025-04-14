/**
 * 系统用户定义
 */
export interface SystemUser extends BaseEntity {
  username?: string
  deptId?: number
  realName?: string
  email?: string
  phone?: string
  gender?: string
  avatar?: number
  password?: string
  roles?: number[]
  posts?: number[]
  modules?: number[]
  enable?: boolean
  pwdChanged?: boolean
}

/**
 * 系统用户接口
 */
export const systemUserApi = {
  // 查询系统用户
  getById(id: number): AxiosPromise<SystemUser> {
    return request({ url: '/systemUser/getById', method: 'get', params: { id } })
  },
  // 分页查询系统用户列表
  page(params: SystemUser): AxiosPromise<SystemUser> {
    return request({ url: '/systemUser/page', method: 'get', params })
  },
  // 查询系统用户列表
  list(params: SystemUser): AxiosPromise<SystemUser> {
    return request({ url: '/systemUser/list', method: 'get', params })
  },
  // 保存系统用户
  save(data: SystemUser): AxiosPromise<SystemUser> {
    return request({ url: '/systemUser/save', method: 'post', data })
  },
  // 批量删除系统用户信息
  remove(data: number[]): AxiosPromise<SystemUser> {
    return request({ url: '/systemUser/remove', method: 'delete', data })
  },
  // 导出系统用户
  exportData(data: SystemUser) {
    download('/systemUser/export', data, `系统用户_${getNowDate('_')}.xlsx`)
  },
  // 获取系统用户导入模板
  downloadTemplate() {
    downloadTemplate('/systemUser/downloadTemplate', `系统用户_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemUser/importData',
}
