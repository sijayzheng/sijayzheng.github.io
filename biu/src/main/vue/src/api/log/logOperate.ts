/**
 * 操作日志定义
 */
export interface LogOperate extends IdEntity {
  userId?: number
  title?: string
  method?: string
  requestMethod?: string
  url?: string
  ip?: string
  location?: string
  param?: string
  result?: string
  status?: number
  errorMsg?: string
  time?: string
  costTime?: number
}

/**
 * 操作日志接口
 */
export const logOperateApi = {
  // 查询操作日志
  getById(id: number): AxiosPromise<LogOperate> {
    return request({ url: '/logOperate/getById', method: 'get', params: { id } })
  },
  // 分页查询操作日志列表
  page(params: LogOperate): AxiosPromise<LogOperate> {
    return request({ url: '/logOperate/page', method: 'get', params })
  },
  // 查询操作日志列表
  list(params: LogOperate): AxiosPromise<LogOperate> {
    return request({ url: '/logOperate/list', method: 'get', params })
  },
  // 保存操作日志
  save(data: LogOperate): AxiosPromise<LogOperate> {
    return request({ url: '/logOperate/save', method: 'post', data })
  },
  // 批量删除操作日志信息
  remove(data: number[]): AxiosPromise<LogOperate> {
    return request({ url: '/logOperate/remove', method: 'delete', data })
  },
  // 导出操作日志
  exportData(data: LogOperate) {
    download('/logOperate/export', data, `操作日志_${getNowDate('_')}.xlsx`)
  },
  // 获取操作日志导入模板
  downloadTemplate() {
    downloadTemplate('/logOperate/downloadTemplate', `操作日志_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/logOperate/importData',
}
