export const modal = {
  alert(message?: string) {
    return ElMessageBox.alert(message, '系统提示', { type: 'warning' })
  },
  confirm(message?: string) {
    return ElMessageBox.confirm(message, '系统提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
  },
  prompt(message?: string) {
    return ElMessageBox.prompt(message, '系统提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
  },

}
