<template>
  <div
      :class="{ collapse: collapse }"
      :style="{ backgroundColor: sideTheme === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground }"
      class="sidebar-logo-container"
  >
    <transition
        :enter-active-class="animateUtil.logoAnimate.enter"
        mode="out-in"
    >
      <router-link
          v-if="collapse"
          key="collapse"
          class="sidebar-logo-link"
          to="/"
      >
        <img
            v-if="logo"
            :src="logo"
            alt=""
            class="sidebar-logo"
        >
        <h1
            v-else
            :style="{ color: sideTheme === 'theme-dark' ? variables.logoTitleColor : variables.logoLightTitleColor }"
            class="sidebar-title"
        >
          {{ title }}
        </h1>
      </router-link>
      <router-link
          v-else
          key="expand"
          class="sidebar-logo-link"
          to="/"
      >
        <img
            v-if="logo"
            :src="logo"
            alt="logo"
            class="sidebar-logo"
        >
        <h1
            :style="{ color: sideTheme === 'theme-dark' ? variables.logoTitleColor : variables.logoLightTitleColor }"
            class="sidebar-title"
        >
          {{ title }}
        </h1>
      </router-link>
    </transition>
  </div>
</template>

<script setup>
import logo from '@/assets/logo/logo.png'
import useSettingsStore from '@/store/settings'
import variables from '@/style/variables.module.scss'

defineProps({
  collapse: {
    type: Boolean,
    required: true
  }
})

const title = ref(import.meta.env.VITE_APP_TITLE)
const settingsStore = useSettingsStore()
const sideTheme = computed(() => settingsStore.sideTheme)
</script>

<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: #2b2f3a;
  text-align: center;
  overflow: hidden;

  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 32px;
      height: 32px;
      vertical-align: middle;
      margin-right: 12px;
    }

    & .sidebar-title {
      display: inline-block;
      margin: 0;
      color: #fff;
      font-weight: 600;
      line-height: 50px;
      font-size: 14px;
      font-family: Avenir,
      Helvetica Neue,
      Arial,
      Helvetica,
      sans-serif;
      vertical-align: middle;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0;
    }
  }
}
</style>
