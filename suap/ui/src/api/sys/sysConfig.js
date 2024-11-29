/**
 * 系统配置后台接口
 */
export const sysConfigApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysConfig/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'sysConfig/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysConfig/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysConfig/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('sysConfig/downloadTemplate', {}, '系统配置_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('sysConfig/export', {}, '系统配置.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'sysConfig/import',
      method: 'post',
      data: data
    })
  }
}
