export const commonApi = {
  /**
   * 获取路由信息
   * @returns {*}
   */
  getRoutes() {
    return request({
      url: 'common/getRoutes',
      method: 'get',
    })
  },
}
