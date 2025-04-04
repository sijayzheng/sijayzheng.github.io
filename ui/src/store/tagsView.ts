export const useTagsViewStore = defineStore('tagsView', () => {
    const visitedViews = ref([])
    const cachedViews = ref([])
    const iframeViews = ref([])

    const getVisitedViews = () => {
        return visitedViews.value
    }
    const getIframeViews = () => {
        return iframeViews.value
    }
    const getCachedViews = () => {
        return cachedViews.value
    }

    const addView = (view) => {
        addVisitedView(view)
        addCachedView(view)
    }

    const addIframeView = (view) => {
        if (iframeViews.value.some((v) => v.path === view.path)) {
            return
        }
        iframeViews.value.push(
            Object.assign({}, view, {
                title: view.meta?.title || 'no-name'
            })
        )
    }
    const delIframeView = (view) => {
        return new Promise((resolve) => {
            iframeViews.value = iframeViews.value.filter((item) => item.path !== view.path)
            resolve([...iframeViews.value])
        })
    }
    const addVisitedView = (view) => {
        if (visitedViews.value.some((v) => v.path === view.path)) {
            return
        }
        visitedViews.value.push(
            Object.assign({}, view, {
                title: view.meta?.title || 'no-name'
            })
        )
    }
    const delView = (view) => {
        return new Promise((resolve) => {
            delVisitedView(view)
            if (!isDynamicRoute(view)) {
                delCachedView(view)
            }
            resolve({
                visitedViews: [...visitedViews.value],
                cachedViews: [...cachedViews.value]
            })
        })
    }

    const delVisitedView = (view) => {
        return new Promise((resolve) => {
            for (const [i, v] of visitedViews.value.entries()) {
                if (v.path === view.path) {
                    visitedViews.value.splice(i, 1)
                    break
                }
            }
            resolve([...visitedViews.value])
        })
    }
    const delCachedView = (view) => {
        let viewName = ''
        if (view) {
            viewName = view.name
        }
        return new Promise((resolve) => {
            const index = cachedViews.value.indexOf(viewName)
            index > -1 && cachedViews.value.splice(index, 1)
            resolve([...cachedViews.value])
        })
    }
    const delOthersViews = (view) => {
        return new Promise((resolve) => {
            delOthersVisitedViews(view)
            delOthersCachedViews(view)
            resolve({
                visitedViews: [...visitedViews.value],
                cachedViews: [...cachedViews.value]
            })
        })
    }

    const delOthersVisitedViews = (view) => {
        return new Promise((resolve) => {
            visitedViews.value = visitedViews.value.filter((v) => {
                return v.meta?.affix || v.path === view.path
            })
            resolve([...visitedViews.value])
        })
    }
    const delOthersCachedViews = (view) => {
        const viewName = view.name
        return new Promise((resolve) => {
            const index = cachedViews.value.indexOf(viewName)
            if (index > -1) {
                cachedViews.value = cachedViews.value.slice(index, index + 1)
            } else {
                cachedViews.value = []
            }
            resolve([...cachedViews.value])
        })
    }

    const delAllViews = () => {
        return new Promise((resolve) => {
            delAllVisitedViews()
            delAllCachedViews()
            resolve({
                visitedViews: [...visitedViews.value],
                cachedViews: [...cachedViews.value]
            })
        })
    }
    const delAllVisitedViews = () => {
        return new Promise((resolve) => {
            visitedViews.value = visitedViews.value.filter((tag) => tag.meta?.affix)
            resolve([...visitedViews.value])
        })
    }

    const delAllCachedViews = () => {
        return new Promise((resolve) => {
            cachedViews.value = []
            resolve([...cachedViews.value])
        })
    }

    const updateVisitedView = (view) => {
        for (let v of visitedViews.value) {
            if (v.path === view.path) {
                v = Object.assign(v, view)
                break
            }
        }
    }
    const delRightTags = (view) => {
        return new Promise((resolve) => {
            const index = visitedViews.value.findIndex((v) => v.path === view.path)
            if (index === -1) {
                return
            }
            visitedViews.value = visitedViews.value.filter((item, idx) => {
                if (idx <= index || (item.meta && item.meta.affix)) {
                    return true
                }
                const i = cachedViews.value.indexOf(item.name)
                if (i > -1) {
                    cachedViews.value.splice(i, 1)
                }
                return false
            })
            resolve([...visitedViews.value])
        })
    }
    const delLeftTags = (view) => {
        return new Promise((resolve) => {
            const index = visitedViews.value.findIndex((v) => v.path === view.path)
            if (index === -1) {
                return
            }
            visitedViews.value = visitedViews.value.filter((item, idx) => {
                if (idx >= index || (item.meta && item.meta.affix)) {
                    return true
                }
                const i = cachedViews.value.indexOf(item.name)
                if (i > -1) {
                    cachedViews.value.splice(i, 1)
                }
                return false
            })
            resolve([...visitedViews.value])
        })
    }

    const addCachedView = (view) => {
        const viewName = view.name
        if (!viewName) {
            return
        }
        if (cachedViews.value.includes(viewName)) {
            return
        }
        if (!view.meta?.noCache) {
            cachedViews.value.push(viewName)
        }
    }

    const isDynamicRoute = (view) => {
        // 检查匹配的路由记录中是否有动态段
        return view.matched.some((m) => m.path.includes(':'))
    }

    return {
        visitedViews,
        cachedViews,
        iframeViews,

        getVisitedViews,
        getIframeViews,
        getCachedViews,

        addVisitedView,
        addCachedView,
        delVisitedView,
        delCachedView,
        updateVisitedView,
        addView,
        delView,
        delAllViews,
        delAllVisitedViews,
        delAllCachedViews,
        delOthersViews,
        delRightTags,
        delLeftTags,
        addIframeView,
        delIframeView
    }
})
export default useTagsViewStore
