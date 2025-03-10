<template>
  <div class="el-tree-select">
    <el-select
        ref="treeSelect"
        v-model="valueId"
        :clearable="true"
        :filter-method="selectFilterData"
        :filterable="true"
        :placeholder="placeholder"
        style="width: 100%"
        @clear="clearHandle"
    >
      <el-option
          :label="valueTitle"
          :value="valueId"
      >
        <el-tree
            id="tree-option"
            ref="selectTree"
            :accordion="accordion"
            :data="options"
            :default-expanded-keys="defaultExpandedKey"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            :node-key="objMap.value"
            :props="objMap"
            @node-click="handleNodeClick"
        />
      </el-option>
    </el-select>
  </div>
</template>

<script setup>
const props = withDefaults(defineProps(), {
  objMap: () => {
    return {
      value: 'id',
      label: 'label',
      children: 'children'
    }
  },
  accordion: false,
  value: '',
  options: () => [],
  placeholder: ''
})

const selectTree = ref()

const emit = defineEmits(['update:value'])

const valueId = computed({
  get: () => props.value,
  set: (val) => {
    emit('update:value', val)
  }
})
const valueTitle = ref('')
const defaultExpandedKey = ref([])

const initHandle = () => {
  nextTick(() => {
    const selectedValue = valueId.value
    if (selectedValue !== null && typeof selectedValue !== 'undefined') {
      const node = selectTree.value?.getNode(selectedValue)
      if (node) {
        valueTitle.value = node.data[props.objMap.label]
        selectTree.value?.setCurrentKey(selectedValue) // 设置默认选中
        defaultExpandedKey.value = [selectedValue] // 设置默认展开
      }
    } else {
      clearHandle()
    }
  })
}
const handleNodeClick = (node) => {
  valueTitle.value = node[props.objMap.label]
  valueId.value = node[props.objMap.value]
  defaultExpandedKey.value = []
  selectTree.value?.blur()
  selectFilterData('')
}
const selectFilterData = (val) => {
  selectTree.value?.filter(val)
}
const filterNode = (value, data) => {
  if (!value) {
    return true
  }
  return data[props.objMap['label']].indexOf(value) !== -1
}
const clearHandle = () => {
  valueTitle.value = ''
  valueId.value = ''
  defaultExpandedKey.value = []
  clearSelected()
}
const clearSelected = () => {
  const allNode = document.querySelectorAll('#tree-option .el-tree-node')
  allNode.forEach((element) => element.classList.remove('is-current'))
}

onMounted(() => {
  initHandle()
})

watch(valueId, () => {
  initHandle()
})
</script>

<style lang="scss" scoped>
@use '@/style/variables.module.scss' as variables;

.el-scrollbar .el-scrollbar__view .el-select-dropdown__item {
  padding: 0;
  background-color: #fff;
  height: auto;
}

.el-select-dropdown__item.selected {
  font-weight: normal;
}

ul li .el-tree .el-tree-node__content {
  height: auto;
  padding: 0 20px;
  box-sizing: border-box;
}

:deep(.el-tree-node__content:hover),
:deep(.el-tree-node__content:active),
:deep(.is-current > div:first-child),
:deep(.el-tree-node__content:focus) {
  background-color: mix(#fff, variables.$color-primary, 90%);
  color: variables.$color-primary;
}
</style>
