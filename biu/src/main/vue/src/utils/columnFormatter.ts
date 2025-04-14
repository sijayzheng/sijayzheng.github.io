import { formatNumber } from '@/utils/index'

export const columnFormatter = {
  date: (row: any, column: any, value: any) => {
    return parseTime(value, '{y}-{m}-{d}')
  },
  zhDate: (row: any, column: any, value: any) => {
    return parseTime(value, '{y}年{m}月{d}日')
  },
  dateTime: (row: any, column: any, value: any) => {
    return parseTime(value, '{y}-{m}-{d} {h}:{i}:{s}')
  },
  zhDateTime: (row: any, column: any, value: any) => {
    return parseTime(value, '{y}年{m}月{d}日 {h}时{i}分{s}秒')
  },
  time: (row: any, column: any, value: any) => {
    return parseTime(value, '{y}-{m}-{d} {h}:{i}:{s}')
  },
  zhTime: (row: any, column: any, value: any) => {
    return parseTime(value, '{y}年{m}月{d}日 {h}时{i}分{s}秒')
  },
  number: (row: any, column: any, value: any) => {
    return formatNumber(value)
  },
  yuan: (row: any, column: any, value: any) => {
    return `${value}元`
  },
  wanYuan: (row: any, column: any, value: any) => {
    return `${value}万元`
  },
  percent: (row: any, column: any, value: any) => {
    return `${value}%`
  },
}
