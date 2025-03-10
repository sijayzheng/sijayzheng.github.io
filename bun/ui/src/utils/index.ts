const TOKEN_KEY: string = 'token'
const tokenStorage = useStorage<null | string>(TOKEN_KEY, null)

export function setToken(value: string) {
  tokenStorage.value = value
}

export function getToken() {
  return tokenStorage.value ?? ''
}

export function clearToken() {
  tokenStorage.value = null
}

/**
 * 判断url是否是http或https
 * @returns {boolean}
 * @param url
 */
export function isHttp(url: string): boolean {
  return url.includes('http://') || url.includes('https://')
}
