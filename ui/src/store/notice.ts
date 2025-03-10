import {defineStore} from 'pinia'

export const useNoticeStore = defineStore('notice', () => {
    const state = reactive({
        notices: []
    })

    const addNotice = (notice) => {
        state.notices.push(notice)
    }

    const removeNotice = (notice) => {
        state.notices.splice(state.notices.indexOf(notice), 1)
    }

    //实现全部已读
    const readAll = () => {
        state.notices.forEach((item) => {
            item.read = true
        })
    }

    const clearNotice = () => {
        state.notices = []
    }
    return {
        state,
        addNotice,
        removeNotice,
        readAll,
        clearNotice
    }
})

export default useNoticeStore
