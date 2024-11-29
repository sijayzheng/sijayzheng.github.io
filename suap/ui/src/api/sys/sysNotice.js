/**
 * 通知公告后台接口
 */
export const sysNoticeApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysNotice/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'sysNotice/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysNotice/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysNotice/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('sysNotice/downloadTemplate', {}, '通知公告_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('sysNotice/export', {}, '通知公告.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'sysNotice/import',
      method: 'post',
      data: data
    })
  }
}
