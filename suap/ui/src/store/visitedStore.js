export const useVisitedStore = defineStore('visited', () => {
  const visited = ref({})

  const add = (view) => {
    if (view.path !== '/index' && !Object.keys(visited.value).includes(view.path)) {
      const v = view.matched.filter(item => item.path === view.path)[0]
      visited.value[v.path] = {
        key: v.name,
        path: v.path,
        name: v.meta.title
      }
    }
  }
  const remove = (view) => {
    delete visited.value[view.path]
  }
  return {
    visited,
    add,
    remove,
  }
})

export default useVisitedStore
