import {authApi} from '@/api/auth'

export const useUserStore = defineStore('user', () => {
  const token = ref(getToken())
  const name = ref('')
  const nickname = ref('')
  const userId = ref('')
  const avatar = ref('')
  const roles = ref([]) // 用户角色编码集合 → 判断路由权限
  const permissions = ref([]) // 用户权限编码集合 → 判断按钮权限
  const reLogin = ref(true)
  /**
   * 登录
   * @returns
   * @param loginParam
   */
  const login = async (loginParam: LoginParam) => {
    const res = call(authApi.login(loginParam))
    console.log(res)
    if (res) {
      const data = res.data
      setToken(data.access_token)
      token.value = data.access_token
      return Promise.resolve()
    }
  }

  // 获取用户信息
  const getInfo = async () => {
    // const [err, res] = await ato(loginApi.getUserInfo())
    // if (res) {
    //   const data = res.data
    //   const user = data.user
    //   const profile = user.avatar === '' || user.avatar === null ? defaultAvatar : user.avatar
    //
    //   if (data.roles && data.roles.length > 0) {
    //     // 验证返回的roles是否是一个非空数组
    //     roles.value = data.roles
    //     permissions.value = data.permissions
    //   } else {
    //     roles.value = ['ROLE_DEFAULT']
    //   }
    //   name.value = user.userName
    //   nickname.value = user.nickName
    //   avatar.value = profile
    //   userId.value = user.userId

    return Promise.resolve()
    // }
    // return Promise.reject(err)
  }

  // 注销
  const logout = async () => {
    await authApi.logout()
    // token.value = ''
    // roles.value = []
    // permissions.value = []
    // removeToken()
  }

  const setAvatar = (value: string) => {
    avatar.value = value
  }
  const setReLogin = (re: boolean) => {
    reLogin.value = re
  }

  return {
    userId,
    token,
    nickname,
    avatar,
    roles,
    permissions,
    login,
    getInfo,
    logout,
    setAvatar,
    reLogin,
    setReLogin,
  }
})

export default useUserStore

// 非setup
// export function useUserStoreHook() {
//   return useUserStore(store)
// }
