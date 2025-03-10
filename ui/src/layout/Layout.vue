<template>
  <div
      :class="classObj"
      :style="{ '--current-color': theme }"
      class="app-wrapper"
  >
    <div
        v-if="sidebar.opened"
        class="drawer-bg"
        @click="handleClickOutside"
    />
    <side-bar
        v-if="!sidebar.hide"
        class="sidebar-container"
    />
    <div
        :class="{ hasTagsView: needTagsView, sidebarHide: sidebar.hide }"
        class="main-container"
    >
      <div :class="{ 'fixed-header': true }">
        <navbar
            ref="navbarRef"
            @set-layout="setLayout"
        />
        <tags-view v-if="needTagsView"/>
      </div>
      <app-main/>
      <settings ref="settingRef"/>
    </div>
  </div>
</template>

<script setup>
import useAppStore from '@/store/app'
import useSettingsStore from '@/store/settings'
import {initSSE} from '@/util/sse'
import AppMain from '@/layout/AppMain.vue'
import Navbar from '@/layout/Navbar.vue'
import SideBar from '@/layout/Sidebar.vue'
import Settings from '@/layout/Settings.vue'
import TagsView from '@/layout/TagsView.vue'

const settingsStore = useSettingsStore()
const theme = computed(() => settingsStore.theme)
const sidebar = computed(() => useAppStore().sidebar)
const needTagsView = computed(() => settingsStore.tagsView)

const classObj = computed(() => ({
  hideSidebar: !sidebar.value.opened,
  openSidebar: sidebar.value.opened,
  withoutAnimation: sidebar.value.withoutAnimation
}))

const navbarRef = ref()
const settingRef = ref()


onMounted(() => {
  initSSE('/resource/sse')
})

const handleClickOutside = () => {
  useAppStore().closeSideBar({withoutAnimation: false})
}

const setLayout = () => {
  settingRef.value?.openSetting()
}
</script>

<style lang="scss" scoped>
@use '@/style/variables.module.scss' as variables;

.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
}

.drawer-bg {
  background: #000;
  opacity: 0.3;
  width: 100%;
  top: 0;
  height: 100%;
  position: absolute;
  z-index: 999;
}

.fixed-header {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 9;
  width: calc(100% - #{variables.$base-sidebar-width});
  transition: width 0.28s;
  background: variables.$fixed-header-bg;
}

.hideSidebar .fixed-header {
  width: calc(100% - 54px);
}

.sidebarHide .fixed-header {
  width: 100%;
}
</style>
