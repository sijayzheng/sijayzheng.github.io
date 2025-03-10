// 初始化
import {getToken} from './auth'
import useNoticeStore from '../store/notice'

export const initSSE = (url: string) => {
    url = url + '?Authorization=Bearer ' + getToken()
    const {data, error} = useEventSource(url, [], {
        autoReconnect: {
            retries: 10,
            delay: 3000,
            onFailed() {
                console.log('Failed to connect after 10 retries')
            }
        }
    })

    watch(error, () => {
        console.log('SSE connection error:', error.value)
        error.value = null
    })

    watch(data, () => {
        if (!data.value) {
            return
        }
        useNoticeStore().addNotice({
            message: data.value,
            read: false,
            time: new Date().toLocaleString()
        })
        ElNotification({
            title: '消息',
            message: data.value,
            type: 'success',
            duration: 3000
        })
        data.value = null
    })
}
