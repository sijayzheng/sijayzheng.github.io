/**
 * 系统岗位定义
 */
export interface SystemPost extends BaseEntity {
  name?: string
  code?: string
  deptId?: number
  sort?: number
  enable?: boolean
}

/**
 * 系统岗位接口
 */
export const systemPostApi = {
  // 查询系统岗位
  getById(id: number): AxiosPromise<SystemPost> {
    return request({ url: '/systemPost/getById', method: 'get', params: { id } })
  },
  // 分页查询系统岗位列表
  page(params: SystemPost): AxiosPromise<SystemPost> {
    return request({ url: '/systemPost/page', method: 'get', params })
  },
  // 查询系统岗位列表
  list(params: SystemPost): AxiosPromise<SystemPost> {
    return request({ url: '/systemPost/list', method: 'get', params })
  },
  // 保存系统岗位
  save(data: SystemPost): AxiosPromise<SystemPost> {
    return request({ url: '/systemPost/save', method: 'post', data })
  },
  // 批量删除系统岗位信息
  remove(data: number[]): AxiosPromise<SystemPost> {
    return request({ url: '/systemPost/remove', method: 'delete', data })
  },
  // 导出系统岗位
  exportData(data: SystemPost) {
    download('/systemPost/export', data, `系统岗位_${getNowDate('_')}.xlsx`)
  },
  // 获取系统岗位导入模板
  downloadTemplate() {
    downloadTemplate('/systemPost/downloadTemplate', `系统岗位_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemPost/importData',
}
