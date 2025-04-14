/**
 * 用户头像定义
 */
export interface SystemUserAvatar extends IdEntity {
  avatar?: any
}

/**
 * 用户头像接口
 */
export const systemUserAvatarApi = {
  // 查询用户头像
  getById(id: number): AxiosPromise<SystemUserAvatar> {
    return request({ url: '/systemUserAvatar/getById', method: 'get', params: { id } })
  },
  // 分页查询用户头像列表
  page(params: SystemUserAvatar): AxiosPromise<SystemUserAvatar> {
    return request({ url: '/systemUserAvatar/page', method: 'get', params })
  },
  // 查询用户头像列表
  list(params: SystemUserAvatar): AxiosPromise<SystemUserAvatar> {
    return request({ url: '/systemUserAvatar/list', method: 'get', params })
  },
  // 保存用户头像
  save(data: SystemUserAvatar): AxiosPromise<SystemUserAvatar> {
    return request({ url: '/systemUserAvatar/save', method: 'post', data })
  },
  // 批量删除用户头像信息
  remove(data: number[]): AxiosPromise<SystemUserAvatar> {
    return request({ url: '/systemUserAvatar/remove', method: 'delete', data })
  },
  // 导出用户头像
  exportData(data: SystemUserAvatar) {
    download('/systemUserAvatar/export', data, `用户头像_${getNowDate('_')}.xlsx`)
  },
  // 获取用户头像导入模板
  downloadTemplate() {
    downloadTemplate('/systemUserAvatar/downloadTemplate', `用户头像_模板_${getNowDate('_')}.xlsx`)
  },
  importUrl: '/systemUserAvatar/importData',
}
