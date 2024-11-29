import dayjs from 'dayjs'
import {useStorage} from '@vueuse/core'
import {to} from 'await-to-js'

const TokenKey = 'token'

const tokenStorage = useStorage(TokenKey, null)

export const getToken = () => tokenStorage.value

export const setToken = (access_token) => (tokenStorage.value = access_token)

export const removeToken = () => (tokenStorage.value = null)

export const isUrl = (url) => {
  return !(url.startsWith('http://') || url.startsWith('https://'))
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

export const awaitTo = async (fn) => {
  const [err, res] = await to(fn)
  return [res, err]
}

/**
 * 数字格式化 number 需要格式化的数字 digits 格式化后的数字位数
 */
export const formatDigits = (number, digits) => {
  number += ''
  if (number.length < digits) {
    let frontZero = ''
    for (let i = number.length; i < digits; i++) {
      frontZero += '0'
    }
    return frontZero + number
  } else {
    return number
  }
}

/**
 * 对Date的扩展，将Date转化为指定格式的
 * String (new Date()).format("yyyy-MM-DD HH:mm:ss.S d") ==> 2019-10-18 17:20:43.94 星期五
 */
Date.prototype.format = function (formatter) {
  const weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
  const quarters = ['一季度', '二季度', '三季度', '四季度']
  formatter = formatter || 'yyyy-MM-DD HH:mm:ss.S w q'
  const opt = {
    'y+': this.getFullYear(), // 年
    'M+': this.getMonth() + 1, // 月份
    'D+': this.getDate(), // 日
    'H+': this.getHours(), // 小时
    'm+': this.getMinutes(), // 分
    's+': this.getSeconds(), // 秒
    S: this.getMilliseconds(), // 毫秒
    'q+': quarters[Math.floor(this.getMonth() / 3)], // 季度
    'w+': weeks[this.getDay()], // 星期
  }
  for (const key in opt) {
    let value = String(opt[key])
    const regex = new RegExp(key)
    if (regex.test(formatter)) {
      formatter = formatter.replace(regex, formatter.match(regex)[0].length === 1 ? value : ('00' + value).substring(value.length))
    }
  }
  return formatter
}

/**
 * 获取date的次日日期
 */
export const getNextDate = (date) => {
  return new Date(new Date(date).getTime() + 24 * 60 * 60 * 1000).format('YYYY-MM-DD')
}

/**
 * 获取date的前一日日期
 */
export const getPreviousDate = (date) => {
  return new Date(new Date(date).getTime() - 24 * 60 * 60 * 1000).format('YYYY-MM-DD')
}
