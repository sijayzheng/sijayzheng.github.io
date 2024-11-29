/**
 * 菜单后台接口
 */
export const sysMenuApi = {
  /**
   * 根据id查询
   */
  getById(id) {
    return request({
      url: 'sysMenu/getById',
      method: 'get',
      params: {id: id}
    })
  },
  listTree(queryParam) {
    return request({
    url: 'sysMenu/listTree',
    method: 'get',
    params: queryParam
    })
  },
  /**
   * 新增或修改
   */
  save(data) {
    return request({
      url: 'sysMenu/save',
      method: 'post',
      data: data
    })
  },
  /**
   * 删除
   */
  remove(data) {
    return request({
      url: 'sysMenu/remove',
      method: 'post',
      data: data
    })
  },
}
