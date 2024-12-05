import tab from './tab'
import cache from './cache'
import auth from './auth'
// 预设动画
import animate from '@/animate'

import { useDict } from '@/util/dict'
import { getConfigKey, updateConfigByKey } from '@/api/system/ConfigApi.ts'
import { addDateRange, handleTree, parseTime, selectDictLabel, selectDictLabels } from '@/util/ruoyi'

export default function installPlugin(app) {
  // 页签操作
  app.config.globalProperties.$tab = tab

  // 缓存对象
  app.config.globalProperties.$cache = cache

  // 认证对象
  app.config.globalProperties.$auth = auth

  // 全局方法挂载
  app.config.globalProperties.useDict = useDict
  app.config.globalProperties.getConfigKey = getConfigKey
  app.config.globalProperties.updateConfigByKey = updateConfigByKey
  app.config.globalProperties.parseTime = parseTime
  app.config.globalProperties.handleTree = handleTree
  app.config.globalProperties.addDateRange = addDateRange
  app.config.globalProperties.selectDictLabel = selectDictLabel
  app.config.globalProperties.selectDictLabels = selectDictLabels
  app.config.globalProperties.animate = animate
}
