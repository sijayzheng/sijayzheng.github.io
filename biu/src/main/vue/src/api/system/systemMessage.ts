/**
 * 系统消息定义
 */
export interface SystemMessage extends BaseEntity {
  title?: string
  type?: string
  content?: string
  closed?: boolean
  publisher?: number
  receiver?: number[]
}

/**
 * 系统消息接口
 */
export const systemMessageApi = {
  // 查询系统消息
  getById(id: number): AxiosPromise<SystemMessage> {
    return request({ url: '/systemMessage/getById', method: 'get', params: { id } })
  },
  // 分页查询系统消息列表
  page(params: SystemMessage): AxiosPromise<SystemMessage> {
    return request({ url: '/systemMessage/page', method: 'get', params })
  },
  // 查询系统消息列表
  list(params: SystemMessage): AxiosPromise<SystemMessage> {
    return request({ url: '/systemMessage/list', method: 'get', params })
  },
  // 保存系统消息
  save(data: SystemMessage): AxiosPromise<SystemMessage> {
    return request({ url: '/systemMessage/save', method: 'post', data })
  },
  // 批量删除系统消息信息
  remove(data: number[]): AxiosPromise<SystemMessage> {
    return request({ url: '/systemMessage/remove', method: 'delete', data })
  },
  // 导出系统消息
  exportData(data: SystemMessage) {
    download('/systemMessage/export', data, `系统消息_${getNowDate('_')}.xlsx`)
  },
  // 获取系统消息导入模板
  downloadTemplate() {
    downloadTemplate('/systemMessage/downloadTemplate', `系统消息_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemMessage/importData',
}
