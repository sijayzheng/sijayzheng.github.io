<template>
  <div v-if="!item.hidden">
    <template v-if="hasOneShowingChild(item, item.children) && (!onlyOneChild.children || onlyOneChild.noShowingChildren) ">
      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path, onlyOneChild.query)">
        <el-menu-item :class="{ 'submenu-title-noDropdown': !isNest }" :index="resolvePath(onlyOneChild.path)">
          <svg-icon :class-name="'nav-icon'" :icon-class="onlyOneChild.meta.icon || (item.meta && item.meta.icon)"/>
          <template #title>
            <span :title="hasTitle(onlyOneChild.meta.title)" class="menu-title">{{ onlyOneChild.meta.title }}</span>
          </template>
        </el-menu-item>
      </app-link>
    </template>

    <el-sub-menu v-else ref="subMenu" :index="resolvePath(item.path)" teleported>
      <template v-if="item.meta" #title>
        <svg-icon :class-name="'nav-icon'" :icon-class="item.meta ? item.meta.icon : ''"/>
        <span :title="hasTitle(item.meta?.title)" class="menu-title">{{ item.meta?.title }}</span>
      </template>
      <menuItem
          v-for="(child) in item.children"
          :key="child.path"
          :base-path="resolvePath(child.path)"
          :is-nest="true"
          :item="child"
          class="nest-menu"
      />
    </el-sub-menu>
  </div>
</template>

<script name="menuItem" setup>


import AppLink from "@/layout/appLink.vue";
import SvgIcon from "@/components/svgIcon.vue";
import {getNormalPath, isUrl} from "@/utils/common.js";

const props = defineProps({
  item: {
    type: Object,
    required: true
  },
  isNest: {
    type: Boolean,
    default: false
  },
  basePath: {
    type: String,
    default: ''
  }
})

const onlyOneChild = ref({})

const hasOneShowingChild = (parent, children = []) => {
  const showingChildren = children.filter(item => {
    if (item.hidden) {
      return false
    } else {
      onlyOneChild.value = item
      return true
    }
  })
  if (showingChildren.length === 1) {
    return true
  }
  if (showingChildren.length === 0) {
    onlyOneChild.value = {
      ...parent,
      path: '',
      noShowingChildren: true
    }
    return true
  }
  return false
}

const resolvePath = (routePath, routeQuery = null) => {
  if (isUrl(routePath)) {
    return routePath
  }
  if (isUrl(props.basePath)) {
    return props.basePath
  }
  if (routeQuery) {
    let query = JSON.parse(routeQuery)
    return {
      path: getNormalPath(props.basePath + '/' + routePath),
      query: query
    }
  }
  return getNormalPath(props.basePath + '/' + routePath)
}

const hasTitle = (title) => {
  if (!title || title.length <= 5) {
    return ''
  }
  return title
}

</script>

<style scoped>

</style>
