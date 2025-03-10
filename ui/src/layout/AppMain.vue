<template>
  <section class="app-main">
    <router-view v-slot="{ Component, route }">
      <transition
          v-if="!route.meta.noCache"
          :enter-active-class="animante"
          mode="out-in"
      >
        <keep-alive
            v-if="!route.meta.noCache"
            :include="tagsViewStore.cachedViews"
        >
          <component
              :is="Component"
              v-if="!route.meta.link"
              :key="route.path"
          />
        </keep-alive>
      </transition>
      <transition
          v-if="route.meta.noCache"
          :enter-active-class="animante"
          mode="out-in"
      >
        <component
            :is="Component"
            v-if="!route.meta.link && route.meta.noCache"
            :key="route.path"
        />
      </transition>
    </router-view>
    <iframe-toggle/>
  </section>
</template>

<script setup>
import useSettingsStore from '@/store/settings'
import useTagsViewStore from '@/store/tagsView'

import IframeToggle from '@/layout/IframeToggle.vue'

const tagsViewStore = useTagsViewStore()

// 随机动画集合
const animante = ref('')
const animationEnable = ref(useSettingsStore().animationEnable)
watch(
    () => useSettingsStore().animationEnable,
    (val) => {
      animationEnable.value = val
      if (val) {
        animante.value = animateUtil.animateList[Math.round(Math.random() * animateUtil.animateList.length)]
      } else {
        animante.value = animateUtil.defaultAnimate
      }
    },
    {immediate: true}
)
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header + .app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header + .app-main {
    padding-top: 84px;
  }
}
</style>
<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 6px;
  }
}

::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background-color: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background-color: #c0c0c0;
  border-radius: 3px;
}
</style>
