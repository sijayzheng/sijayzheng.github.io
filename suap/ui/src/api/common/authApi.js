// 数据字典

export const authApi = {
    login(data) {
        return request({
            url: '/auth/login',
            method: 'post',
            data: data,
        })
    },
    logout() {
        return request({
            url: '/auth/logout',
            method: 'post',
        })
    },
}