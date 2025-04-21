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
   * @param loginParam
   */
  const login = async (loginParam: LoginParam) => {
    const res = await authApi.login(loginParam)
    if (res) {
      setToken(res.data)
      token.value = res.data
      return Promise.resolve()
    }
  }

  // 获取用户信息
  const getInfo = () => {
    authApi.getUserInfo().then((res) => {
      console.log(res)
    })
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
    //   name.value = user.username
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
    token.value = ''
    roles.value = []
    permissions.value = []
    clearToken()
  }

  const setAvatar = (value) => {
    avatar.value = value
  }
  const setReLogin = (re) => {
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
