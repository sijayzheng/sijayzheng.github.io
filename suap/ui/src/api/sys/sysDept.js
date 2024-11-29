/**
 * 部门后台接口
 */
export const sysDeptApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysDept/getById',
      method: 'get',
      params: {id: id}
    })
  },
  listTree(queryParam) {
    return request({
    url: 'sysDept/listTree',
    method: 'get',
    params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysDept/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysDept/remove',
      method: 'post',
      data: data
    })
  },
}
