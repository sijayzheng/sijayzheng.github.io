export const useSettingStore = defineStore('setting', () => {
  const size = useStorage<'large' | 'default' | 'small'>('size', 'default')
  const sidebar = useStorage<boolean>('sidebar', true)
  const toggleSidebar = () => {
    sidebar.value = !sidebar.value
  }
  const setSize = (s: 'large' | 'default' | 'small') => {
    size.value = s
  }
  const title = ref(import.meta.env.VITE_APP_TITLE)
  const setTitle = (t: string) => {
    title.value = `${import.meta.env.VITE_APP_TITLE} - ${t}`
  }

  return {
    size,
    sidebar,
    toggleSidebar,
    setSize,
    title,
    setTitle,
  }
})

export default useSettingStore
