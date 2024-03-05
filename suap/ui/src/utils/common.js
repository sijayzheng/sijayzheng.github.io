import dayjs from "dayjs";
import {useStorage} from "@vueuse/core";

const TokenKey = 'token'

const tokenStorage = useStorage(TokenKey, null)

export const getToken = () => tokenStorage.value

export const setToken = (access_token) => (tokenStorage.value = access_token)

export const removeToken = () => (tokenStorage.value = null)

export const isUrl = (url) => {
    return url.indexOf('http://') !== -1 || url.indexOf('https://') !== -1
}
export const getNormalPath = (p) => {
    if (p.length === 0 || !p || p === 'undefined') {
        return p
    }
    const res = p.replace('//', '/')
    if (res[res.length - 1] === '/') {
        return res.slice(0, res.length - 1)
    }
    return res
}
export const parseTime = (time, formatter = 'YYYY-MM-DD HH:mm:ss') => {
    return dayjs(time).format(formatter)
}
