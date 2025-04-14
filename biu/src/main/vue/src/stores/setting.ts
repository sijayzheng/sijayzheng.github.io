export const useSettingStore = defineStore('setting', () => {
  const sidebar = useStorage('sidebar', true)
  const toggleSidebar = () => {
    sidebar.value = !sidebar.value
  }
  const title = ref<string>(import.meta.env.VITE_APP_TITLE)
  const setTitle = (t: string) => {
    title.value = `${import.meta.env.VITE_APP_TITLE} - ${t}`
  }

  return {
    sidebar,
    toggleSidebar,
    title,
    setTitle,
  }
})

export default useSettingStore
