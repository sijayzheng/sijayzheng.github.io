<template>
  <el-config-provider
    :button="{ autoInsertSpace: true }"
    :locale="zhCn"
    :message="message"
    :size="size"
  >
    <router-view />
  </el-config-provider>
</template>

<script setup>
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import useSettingsStore from '@/store/settings'
import { handleThemeStyle } from '@/util/theme'
import useAppStore from '@/store/app'

const size = computed(() => 'default')
const message = {
  max: 3,
  duration: 2500,
  showClose: true
}

const appStore = useAppStore()

onMounted(() => {
  nextTick(() => {
    // 初始化主题样式
    handleThemeStyle(useSettingsStore().theme)
  })
})
</script>

<style scoped></style>
