<template>
  <div
      :class="{ 'has-logo': showLogo }"
      :style="{ backgroundColor: bgColor }"
  >
    <logo
        v-if="showLogo"
        :collapse="isCollapse"
    />
    <el-scrollbar
        :class="sideTheme"
        wrap-class="scrollbar-wrapper"
    >
      <transition
          :enter-active-class="animateUtil.menuSearchAnimate.enter"
          mode="out-in"
      >
        <el-menu
            :active-text-color="theme"
            :background-color="bgColor"
            :collapse="isCollapse"
            :collapse-transition="false"
            :default-active="activeMenu"
            :text-color="textColor"
            :unique-opened="true"
            mode="vertical"
        >
          <sidebar-item
              v-for="(r, index) in sidebarRouters"
              :key="r.path + index"
              :base-path="r.path"
              :item="r"
          />
        </el-menu>
      </transition>
    </el-scrollbar>
  </div>
</template>

<script setup>
import useAppStore from '@/store/app'
import useSettingsStore from '@/store/settings'
import usePermissionStore from '@/store/permission'
import Logo from '@/layout/Logo.vue'
import SidebarItem from '@/layout/SidebarItem.vue'
import variables from '@/style/variables.module.scss'

const route = useRoute()
const appStore = useAppStore()
const settingsStore = useSettingsStore()
const permissionStore = usePermissionStore()
const sidebarRouters = computed(() => permissionStore.getSidebarRoutes())
const showLogo = computed(() => settingsStore.sidebarLogo)
const sideTheme = computed(() => settingsStore.sideTheme)
const theme = computed(() => settingsStore.theme)
const isCollapse = computed(() => !appStore.sidebar.opened)

const activeMenu = computed(() => {
  const {
    meta,
    path
  } = route
  // if set path, the sidebar will highlight the path you set
  if (meta.activeMenu) {
    return meta.activeMenu
  }
  return path
})

const bgColor = computed(() => (sideTheme.value === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground))
const textColor = computed(() => (sideTheme.value === 'theme-dark' ? variables.menuColor : variables.menuLightColor))
</script>
