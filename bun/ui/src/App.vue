<script lang="ts" setup>
const settingStore = useSettingStore()
</script>

<template>
  <el-config-provider :size="settingStore.size">
    <router-view/>
  </el-config-provider>
</template>

<style></style>
