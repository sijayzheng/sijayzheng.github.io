/**
 * 数据字典类型后台接口
 */
export const dataDictTypeApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'dataDictType/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'dataDictType/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'dataDictType/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'dataDictType/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('dataDictType/downloadTemplate', {}, '数据字典类型_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('dataDictType/export', {}, '数据字典类型.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'dataDictType/import',
      method: 'post',
      data: data
    })
  }
}
