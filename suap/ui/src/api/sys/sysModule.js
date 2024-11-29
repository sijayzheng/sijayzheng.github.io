/**
 * 模块后台接口
 */
export const sysModuleApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysModule/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'sysModule/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysModule/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysModule/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('sysModule/downloadTemplate', {}, '模块_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('sysModule/export', {}, '模块.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'sysModule/import',
      method: 'post',
      data: data
    })
  }
}
