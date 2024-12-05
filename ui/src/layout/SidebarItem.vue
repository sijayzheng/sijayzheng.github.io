<template>
  <div v-if="!item.hidden">
    <template v-if="hasOneShowingChild(item, item.children) && (!onlyOneChild.children || onlyOneChild.noShowingChildren) && !item.alwaysShow">
      <app-link
        v-if="onlyOneChild.meta"
        :to="resolvePath(onlyOneChild.path, onlyOneChild.query)"
      >
        <el-menu-item
          :class="{ 'submenu-title-noDropdown': !isNest }"
          :index="resolvePath(onlyOneChild.path)"
        >
          <svg-icon :icon-class="onlyOneChild.meta.icon || (item.meta && item.meta.icon)" />
          <template #title>
            <span
              :title="hasTitle(onlyOneChild.meta.title)"
              class="menu-title"
            >{{ onlyOneChild.meta.title }}</span>
          </template>
        </el-menu-item>
      </app-link>
    </template>

    <el-sub-menu
      v-else
      ref="subMenu"
      :index="resolvePath(item.path)"
      teleported
    >
      <template
        v-if="item.meta"
        #title
      >
        <svg-icon :icon-class="item.meta ? item.meta.icon : ''" />
        <span
          :title="hasTitle(item.meta?.title)"
          class="menu-title"
        >{{ item.meta?.title }}</span>
      </template>

      <sidebar-item
        v-for="(child, index) in item.children"
        :key="child.path + index"
        :base-path="resolvePath(child.path)"
        :is-nest="true"
        :item="child"
        class="nest-menu"
      />
    </el-sub-menu>
  </div>
</template>

<script setup>
import AppLink from './Link.vue'
import { getNormalPath } from '@/util/ruoyi'
import { validateUtil } from '@/util/validate.ts'

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

const hasOneShowingChild = (parent, children) => {
  if (!children) {
    children = []
  }
  const showingChildren = children.filter((item) => {
    if (item.hidden) {
      return false
    } else {
      // Temp set(will be used if only has one showing child)
      onlyOneChild.value = item
      return true
    }
  })

  // When there is only one child router, the child router is displayed by default
  if (showingChildren.length === 1) {
    return true
  }

  // Show parent if there are no child router to display
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

const resolvePath = (routePath, routeQuery) => {
  if (validateUtil.isExternal(routePath)) {
    return routePath
  }
  if (validateUtil.isExternal(props.basePath)) {
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
