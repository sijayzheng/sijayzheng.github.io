import {getToken, removeToken, setToken} from '@/utils'
import Request from '@/api/request'
import router from '@/router'
import {ElMessage} from 'element-plus'

export const useZStore = defineStore('z', () => {
    const token = ref(getToken())

    /**
     * 登录
     * @param data
     * @returns
     */
    const login = (data) => {
        Request.login(data).then(res => {
            setToken(res.data.token)
            token.value = res.data.token
            ElMessage({
                message: '登录成功',
                type: 'success',
                duration: 3 * 1000
            })
            router.push({path: data.path || '/'})
        }).catch(err => {
            router.replace({path: '/login'})
        })
    }


    // 注销
    const logout = () => Request.logout().then(res => {
        token.value = ''
        removeToken()
        router.replace({path: '/login'})
    }).catch(err => {
        router.replace({path: '/login'})
    })


    return {
        token,
        login,
        logout
    }
})


export default useZStore
