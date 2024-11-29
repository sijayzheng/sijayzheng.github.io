export const authApi = {
  async login(data) {
    return request({
      url: 'auth/login',
      method: 'post',
      data: data
    })
  },
  logout() {
  }
}
