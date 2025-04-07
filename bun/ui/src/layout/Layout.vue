<script lang="ts" setup>
import { toggleDark } from '@/composables/dark'

const settingStore = useSettingStore()
const permissionStore = usePermissionStore()

const activeMenu = computed(() => {
  return useRoute().path
})

onMounted(() => {

  // settingStore.toggleSidebar()
  //
})

const s = ref(0)

function csize() {
  settingStore.setSize(Math.random() > 0.7 ? 'large' : Math.random() > 0.5 ? 'default' : 'small')
}
</script>

<template>
  <el-container class="layout">
    <el-aside :width="`${settingStore.sidebar ? 200 : 64}px`">
      <div class="sys-logo">
        <router-link to="/">
          <img :src="logo" alt="logo" width="48px">
        </router-link>
        <router-link v-if="settingStore.sidebar" to="/">
          <strong>系统</strong>
        </router-link>
      </div>
      <div class="sys-menu">
        <el-scrollbar>
          <div v-for="item in permissionStore.routes" :key="item.name">
            {{ item.name }}
          </div>
          <el-menu :default-active="activeMenu" unique-opened />
        </el-scrollbar>
      </div>
    </el-aside>
    <el-container>
      <el-header>
        Header
        {{ settingStore.sidebar }}
        {{ settingStore.size }}
        <el-button @click="settingStore.toggleSidebar()">
          切换侧栏
        </el-button>
        <el-button @click="csize">
          切换size
        </el-button>
        <el-button type="primary" @click="toggleDark">
          <span dark:i-ri-moon-line i-ri-sun-line icon-btn />
        </el-button>
      </el-header>
      <el-main>
        main
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped></style>
