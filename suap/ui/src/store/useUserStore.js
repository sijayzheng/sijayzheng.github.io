export const useUserStore = defineStore('user', () => {
  const token = computed(() => getToken())
  const userId = ref('')
  const permissions = ref([]) // 用户权限编码集合 → 判断按钮权限
  const userInfo = ref({}) // 用户信息 → 用于显示用户信息

  /**
   * 登录
   * @param data
   * @returns
   */
  const login = async (data) => {
    const [res, err] = await awaitTo(authApi.login(data))
    if (res?.code === 200) {
      setToken(res.data.accessToken)
      return Promise.resolve()
    }
    return Promise.reject(err)
  }

  // 获取用户信息
  const getUserInfo = async () => {
    let [res, err] = await awaitTo(sysUserApi.getUserInfo())
    if (res) {
      const data = res.data
      permissions.value = data.permissions
      userInfo.value = data
      userId.value = data.id
      return Promise.resolve()
    }
    return Promise.reject(err)
  }

  // 注销
  const logout = async () => {
    userId.value = undefined
    permissions.value = []
    userInfo.value = {}
    removeToken()
  }

  return {
    userId,
    token,
    permissions,
    userInfo,
    login,
    getUserInfo,
    logout,
  }
})

export default useUserStore

