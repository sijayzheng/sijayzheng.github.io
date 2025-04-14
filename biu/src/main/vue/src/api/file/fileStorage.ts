/**
 * 文件存储定义
 */
export interface FileStorage extends BaseEntity {
  name?: string
  originalName?: string
  suffix?: string
  url?: string
  storePath?: string
}

/**
 * 文件存储接口
 */
export const fileStorageApi = {
  // 查询文件存储
  getById(id: number): AxiosPromise<FileStorage> {
    return request({ url: '/fileStorage/getById', method: 'get', params: { id } })
  },
  // 分页查询文件存储列表
  page(params: FileStorage): AxiosPromise<FileStorage> {
    return request({ url: '/fileStorage/page', method: 'get', params })
  },
  // 查询文件存储列表
  list(params: FileStorage): AxiosPromise<FileStorage> {
    return request({ url: '/fileStorage/list', method: 'get', params })
  },
  // 保存文件存储
  save(data: FileStorage): AxiosPromise<FileStorage> {
    return request({ url: '/fileStorage/save', method: 'post', data })
  },
  // 批量删除文件存储信息
  remove(data: number[]): AxiosPromise<FileStorage> {
    return request({ url: '/fileStorage/remove', method: 'delete', data })
  },
  // 导出文件存储
  exportData(data: FileStorage) {
    download('/fileStorage/export', data, `文件存储_${getNowDate('_')}.xlsx`)
  },
  // 获取文件存储导入模板
  downloadTemplate() {
    downloadTemplate('/fileStorage/downloadTemplate', `文件存储_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/fileStorage/importData',
}
