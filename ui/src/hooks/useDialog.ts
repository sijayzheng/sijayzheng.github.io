export default (ops) => {
    const visible = ref(false)
    const title = ref(ops.title || '')

    const openDialog = () => {
        visible.value = true
    }

    const closeDialog = () => {
        visible.value = false
    }

    return {
        title,
        visible,

        openDialog,
        closeDialog
    }
};
