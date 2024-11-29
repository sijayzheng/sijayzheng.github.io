/**
 * 角色后台接口
 */
export const sysRoleApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysRole/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'sysRole/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysRole/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysRole/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('sysRole/downloadTemplate', {}, '角色_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('sysRole/export', {}, '角色.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'sysRole/import',
      method: 'post',
      data: data
    })
  }
}
