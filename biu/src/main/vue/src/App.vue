<template>
  <el-config-provider :button="{ autoInsertSpace: true }" :locale="zhCn" :message="messageConfig" size="default">
    <router-view />
  </el-config-provider>
</template>

<script setup>
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const messageConfig = {
  max: 5,
  duration: 2000,
  showClose: true,
}
</script>

<style></style>
