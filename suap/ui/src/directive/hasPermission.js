/**
 * 操作权限处理
 */
export const hasPermission = {
  mounted(el, binding, vnode) {
    const {permissions} = useUserStore()
    if (permissions.includes('*:*:*')) {
      return true
    }
    if (binding.value && permissions.includes(binding.value)) {
      return true
    } else {
      el.parentNode.removeChild(el)
      return false
    }
  }
}

