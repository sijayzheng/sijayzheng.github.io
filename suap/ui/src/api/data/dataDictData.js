/**
 * 数据字典项后台接口
 */
export const dataDictDataApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'dataDictData/getById',
      method: 'get',
      params: {id: id}
    })
  },
  /**
   * 分页查询
   */
  page(queryParam) {
    return request({
      url: 'dataDictData/page',
      method: 'get',
      params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'dataDictData/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'dataDictData/remove',
      method: 'post',
      data: data
    })
  },
  /**
   * 下载导入模板
   */
  downloadTemplate() {
    download('dataDictData/downloadTemplate', {}, '数据字典项_模板.xlsx')
  },
  /**
   * 导出数据
   */
  exportData() {
    download('dataDictData/export', {}, '数据字典项.xlsx')
  },
  /**
   * 导入数据
   */
  importData(data) {
    return request({
      url: 'dataDictData/import',
      method: 'post',
      data: data
    })
  }
}
