export const msg = {
  // 消息提示
  info(message: string) {
    ElMessage.info(message)
  },
  // 错误消息
  error(message: string) {
    ElMessage.error(message)
  },
  // 成功消息
  success(message: string) {
    ElMessage.success(message)
  },
  // 警告消息
  warning(message: string) {
    ElMessage.warning(message)
  },
}
