export const authApi = {
  login: (data: LoginParam) => {
    return request({ url: '/auth/login', method: 'post', data })
  },
  logout: () => {

  },
  getUserInfo: () => {
    return request({ url: '/auth/userinfo', method: 'get' })
  },
  getCaptchaCode: () => {
    return request({ url: '/auth/code', method: 'get' })
  },
}
