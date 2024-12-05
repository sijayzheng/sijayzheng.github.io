const TokenKey = 'accessToken'

const tokenStorage = useStorage(TokenKey, null)

export const getToken = () => tokenStorage.value

export const setToken = (accessToken) => (tokenStorage.value = accessToken)

export const removeToken = () => (tokenStorage.value = null)
