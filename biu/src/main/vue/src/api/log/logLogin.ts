/**
 * 登录日志定义
 */
export interface LogLogin extends IdEntity {
  userId?: number
  ip?: string
  location?: string
  browser?: string
  os?: string
  successful?: boolean
  msg?: string
  time?: string
}

/**
 * 登录日志接口
 */
export const logLoginApi = {
  // 查询登录日志
  getById(id: number): AxiosPromise<LogLogin> {
    return request({ url: '/logLogin/getById', method: 'get', params: { id } })
  },
  // 分页查询登录日志列表
  page(params: LogLogin): AxiosPromise<LogLogin> {
    return request({ url: '/logLogin/page', method: 'get', params })
  },
  // 查询登录日志列表
  list(params: LogLogin): AxiosPromise<LogLogin> {
    return request({ url: '/logLogin/list', method: 'get', params })
  },
  // 保存登录日志
  save(data: LogLogin): AxiosPromise<LogLogin> {
    return request({ url: '/logLogin/save', method: 'post', data })
  },
  // 批量删除登录日志信息
  remove(data: number[]): AxiosPromise<LogLogin> {
    return request({ url: '/logLogin/remove', method: 'delete', data })
  },
  // 导出登录日志
  exportData(data: LogLogin) {
    download('/logLogin/export', data, `登录日志_${getNowDate('_')}.xlsx`)
  },
  // 获取登录日志导入模板
  downloadTemplate() {
    downloadTemplate('/logLogin/downloadTemplate', `登录日志_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/logLogin/importData',
}
