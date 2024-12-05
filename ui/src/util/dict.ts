import { getDicts } from '@/api/system/dict/data'
import { useDictStore } from '@/store/dict'

/**
 * 获取字典数据
 */
export const useDict = (...args) => {
  const res = ref({})
  return (() => {
    args.forEach(async (dictType) => {
      res.value[dictType] = []
      const dicts = useDictStore().getDict(dictType)
      if (dicts) {
        res.value[dictType] = dicts
      } else {
        await getDicts(dictType).then((resp) => {
          res.value[dictType] = resp.data.map((p) => ({
            label: p.dictLabel,
            value: p.dictValue,
            elTagType: p.listClass,
            elTagClass: p.cssClass
          }))
          useDictStore().setDict(dictType, res.value[dictType])
        })
      }
    })
    return res.value
  })()
}
