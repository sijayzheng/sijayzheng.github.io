import { useSettingsStore } from '@/store/settings'

/**
 * 动态修改标题
 */
export const useDynamicTitle = () => {
  const settingsStore = useSettingsStore()
  if (settingsStore.dynamicTitle) {
    document.title = settingsStore.title + ' - ' + import.meta.env.VITE_APP_TITLE
  } else {
    document.title = import.meta.env.VITE_APP_TITLE
  }
}
