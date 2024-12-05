import { defineStore } from 'pinia'
import { useDynamicTitle } from '@/util/dynamicTitle'

export const defaultSettings = {
  theme: '#30b08f',
  dark: false
}

export const useSettingsStore = defineStore('setting', () => {
  const storageSetting = useStorage('layout-setting', defaultSettings)
  const title = ref(import.meta.env.VITE_APP_TITLE)
  const theme = ref(storageSetting.value.theme)
  const dark = ref(storageSetting.value.dark)
  const sideTheme = ref('theme-light')
  const showSettings = ref(true)
  const topNav = ref(false)
  const tagsView = ref(true)
  const fixedHeader = ref(true)
  const sidebarLogo = ref(true)
  const dynamicTitle = ref(true)
  const animationEnable = ref(true)
  const setTitle = (value) => {
    title.value = value
    useDynamicTitle()
  }
  return {
    title,
    theme,
    sideTheme,
    showSettings,
    topNav,
    tagsView,
    fixedHeader,
    sidebarLogo,
    dynamicTitle,
    animationEnable,
    dark,
    setTitle
  }
})

export default useSettingsStore
