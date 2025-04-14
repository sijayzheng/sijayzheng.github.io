const TOKEN_KEY = 'token'
const tokenStorage = useStorage(TOKEN_KEY, '')

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

export function getNowDate(delimiter: string = '-') {
  const now = new Date()
  const year = now.getFullYear()
  const month = now.getMonth() + 1
  const day = now.getDate()
  return `${year}${delimiter}${month}${delimiter}${day}`
}

export function getNowTime() {
  const now = new Date()
  const hour = now.getHours()
  const minute = now.getMinutes()
  const second = now.getSeconds()
  return `${hour}:${minute}:${second}`
}

export function getNowDateTime() {
  const now = new Date()
  const year = now.getFullYear()
  const month = now.getMonth() + 1
  const day = now.getDate()
  const hour = now.getHours()
  const minute = now.getMinutes()
  const second = now.getSeconds()
  return `${year}-${month}-${day} ${hour}:${minute}:${second}`
}

export function parseTime(time: any, pattern?: string) {
  if (!time) {
    return ''
  }
  const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object'
  ) {
    date = time
  } else {
    if (typeof time === 'string' && /^\d+$/.test(time)) {
      time = Number.parseInt(time)
    } else if (typeof time === 'string') {
      time = time
        .replace(/-/g, '/')
        .replace('T', ' ')
        .replace(/\.\d{3}/g, '')
    }
    if (typeof time === 'number' && time.toString().length === 10) {
      time = time * 1000
    }
    date = new Date(time)
  }
  const formatObj: { [key: string]: any } = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay(),
  }
  return format.replace(/\{([ymdhisa])+\}/g, (result: string, key: string) => {
    let value = formatObj[key]
    if (key === 'a') {
      return ['日', '一', '二', '三', '四', '五', '六'][value]
    }
    if (result.length > 0 && value < 10) {
      value = `0${value}`
    }
    return value || 0
  })
}
export function formatNumber(num: any, suffix: string = '') {
  // 检查是否是有效的数字
  if (Number.isNaN(num)) {
    return num + suffix
  } else if (num === null || num === undefined) {
    return num
  }
  // 使用 toLocaleString() 进行千位分隔，并保留两位小数
  else {
    return Number(num).toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 }) + suffix
  }
}
