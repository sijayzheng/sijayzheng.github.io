import { LoginParam } from '@/types/types.ts'

export const loginApi = {
  /**
   * @param data
   * @returns
   */
  login(data: LoginParam) {
    return request({
      url: '/auth/login',
      headers: {
        isToken: false
        // isEncrypt: true,
        // repeatSubmit: false
      },
      method: 'post',
      data: data
    })
  },
  // 注册方法
  register(data) {
    return request({
      url: '/auth/register',
      headers: {
        isToken: false
        // isEncrypt: true,
        // repeatSubmit: false
      },
      method: 'post',
      data: data
    })
  },
  /**
   * 注销
   */
  logout() {
    request({
      url: '/resource/sse/close',
      method: 'get'
    }).then(r => console.log(r))
    return request({
      url: '/auth/logout',
      method: 'post'
    })
  },
  /**
   * 获取验证码
   */
  getCodeImg() {
    return request({
      url: '/auth/code',
      headers: {
        isToken: false
      },
      method: 'get',
      timeout: 20000
    })
  },
  // 获取用户详细信息
  getUserInfo() {
    return request({
      url: '/system/user/getInfo',
      method: 'get'
    })
  }
}
