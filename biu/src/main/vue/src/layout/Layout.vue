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
        <el-row align="middle">
          <el-col :span="1">
            <el-button :icon="settingStore.sidebar ? Fold : Expand" class="text-xl" link @click="settingStore.toggleSidebar()" />
          </el-col>
          <el-col :span="20">

          </el-col>
          <el-col :span="3">
            <el-button link @click="toggleDark">
              <Icon class="text-xl" dark:i-ep-moon i-ep-sunny />
            </el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import logo from '@/assets/logo.png'
import { Expand, Fold } from '@element-plus/icons-vue'
import { Icon } from '@iconify/vue'

const settingStore = useSettingStore()
const permissionStore = usePermissionStore()

const activeMenu = computed(() => {
  return useRoute().path
})

const isDark = useDark()

const isAppearanceTransition = typeof document.startViewTransition === 'function'
  && !window.matchMedia('(prefers-reduced-motion: reduce)').matches

function toggleDark(event: MouseEvent) {
  if (!isAppearanceTransition || !event) {
    isDark.value = !isDark.value
    return
  }
  const x = event.clientX
  const y = event.clientY
  const endRadius = Math.hypot(Math.max(x, innerWidth - x), Math.max(y, innerHeight - y))
  const transition = document.startViewTransition(async () => {
    isDark.value = !isDark.value
    await nextTick()
  })
  transition.ready.then(() => {
    const clipPath = [`circle(0px at ${x}px ${y}px)`, `circle(${endRadius}px at ${x}px ${y}px)`]
    document.documentElement.animate(
      { clipPath: isDark.value ? [...clipPath].reverse() : clipPath },
      {
        duration: 500,
        easing: 'ease-in',
        pseudoElement: isDark.value ? '::view-transition-old(root)' : '::view-transition-new(root)',
      },
    )
  })
}

onMounted(() => {

  // settingStore.toggleSidebar()
  //
})
</script>

<style lang="scss" scoped></style>
