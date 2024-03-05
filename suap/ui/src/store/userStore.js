import {getToken, removeToken, setToken} from '@/utils/common.js'
import {defineStore} from "pinia";

export const useUserStore = defineStore('user', () => {
    const token = ref(getToken())
    const name = ref('')
    const nickname = ref('')
    const userId = ref('')
    const roles = ref([]) // 用户角色编码集合 → 判断路由权限
    const permissions = ref([]) // 用户权限编码集合 → 判断按钮权限
    const userInfo = ref({}) // 用户信息 → 用于显示用户信息

    /**
     * 登录
     * @param data
     * @returns
     */
    const login = async (data) => {
        const res = await authApi.login(data)
        if (res?.code === '0000') {
            setToken(res.data.accessToken)
            token.value = res.data.accessToken
            return Promise.resolve()
        }
        return Promise.reject()
    }

    // 获取用户信息
    const getUserInfo = async () => {
        const data = {
            data: {}
        }//= await userInfoApi.getUserInfo()
        if (data) {
            userInfo.value = data
            if (data.roles && data.roles.length > 0) {
                // 验证返回的roles是否是一个非空数组
                roles.value = data.roles
                permissions.value = data.permissions
            } else {
                roles.value = ['ROLE_DEFAULT']
            }
            name.value = data.userName
            nickname.value = data.nickName
            userId.value = data.id
            return Promise.resolve()
        }
        return Promise.reject()
    }

    // 注销
    const logout = async () => {
        // await authApi.logout()
        token.value = ''
        roles.value = []
        permissions.value = []
        removeToken()
    }

    return {
        userId,
        token,
        nickname,
        roles,
        permissions,
        login,
        getUserInfo,
        logout,
        userInfo
    }
})

export default useUserStore

