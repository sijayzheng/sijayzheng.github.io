/**
 * 用户后台接口
 */
export const sysUserApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysUser/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'sysUser/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysUser/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysUser/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('sysUser/downloadTemplate', {}, '用户_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('sysUser/export', {}, '用户.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'sysUser/import',
      method: 'post',
      data: data
    })
  },
  getUserInfo() {
    return request({
      url: 'sysUser/getUserInfo',
      method: 'get',
    })
  }
}
