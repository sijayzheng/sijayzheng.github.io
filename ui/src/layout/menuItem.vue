<template>
  <div>
    <template
        v-if="hasOneShowingChild(item.children, item)">
      <app-link v-if="onlyOneChild" :to="resolvePath(onlyOneChild.path)">
        <el-menu-item :index="resolvePath(onlyOneChild.path)" :class="{ 'submenu-title-noDropdown': !isNest }">
          <template #title>
            <span class="menu-title" :title="hasTitle(onlyOneChild.title)">{{ onlyOneChild.meta.title }}</span>
          </template>
        </el-menu-item>
      </app-link>
    </template>

    <el-sub-menu v-else ref="subMenu" :index="resolvePath(item.path)" teleported>
      <template v-if="item" #title>
        <span class="menu-title" :title="hasTitle(item.meta?.title)">{{ item.meta?.title }}</span>
      </template>
      <menuItem
          v-for="child in item.children"
          :key="child.path"
          :is-nest="true"
          :item="child  "
          :base-path="resolvePath(child.path)"
          class="nest-menu"
      ></menuItem>
    </el-sub-menu>
  </div>
</template>

<script setup name="menuItem">
import {getNormalPath, isExternal} from '@/utils'

import AppLink from '@/layout/appLink.vue'

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

const hasOneShowingChild = (children, parent) => {
  if (!children) {
    onlyOneChild.value = {
      ...parent,
      path: '',
      noShowingChildren: true
    }
    return true
  }
  if (children.length == 1) {
    onlyOneChild.value = children[0]
  }
  return children.length == 1
}

const resolvePath = (routePath) => {
  if (isExternal(routePath)) {
    return routePath
  }
  if (isExternal(props.basePath)) {
    return props.basePath
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
