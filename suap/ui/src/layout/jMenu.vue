<template>
  <div v-for="item in props.data">
    <el-sub-menu v-if="item.children?.length>0" :index="item.name">
      <template #title>
        <JIcon :icon="item.meta.icon"/>
        <span>{{ item.meta.title }}</span>
      </template>
      <JMenu :data="item.children" :parentPath="getParentPath(props.parentPath,item.path)"/>
    </el-sub-menu>
    <el-menu-item v-else :index="item.name" :route="getParentPath(props.parentPath,item.path)">
      <JIcon :icon="item.meta.icon"/>
      <span v-text="item.meta.title"/>
    </el-menu-item>
  </div>
</template>

<script setup>
import JIcon from '@/components/jIcon.vue'

defineOptions({
  name: 'JMenu'
})

const props = defineProps({
  data: {
    type: Array,
    default: () => []
  },
  parentPath: {
    type: String,
    default: ''
  }
})
const getParentPath = (parentPath, path) => {
  return [parentPath, path].join('/').replace('//', '/')
}
</script>
<style lang="scss" scoped>

</style>
