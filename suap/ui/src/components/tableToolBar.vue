<template>
  <div :style="style" style="margin-left: auto;">
    <el-row>
      <el-tooltip v-if="search" :content="showSearch ? '隐藏搜索' : '显示搜索'" class="item" effect="dark" placement="top">
        <el-button circle icon="Search" @click="toggleSearch()"/>
      </el-tooltip>
      <el-tooltip class="item" content="刷新" effect="dark" placement="top">
        <el-button circle icon="Refresh" @click="refresh()"/>
      </el-tooltip>
      <el-tooltip v-if="columns" class="item" content="显示/隐藏列" effect="dark" placement="top">
        <el-popover placement="bottom" trigger="click">
          <div class="tree-header">显示/隐藏列</div>
          <el-tree
              ref="columnRef"
              :data="columns"
              :props="{ label: 'label', children: 'children' }"
              node-key="key"
              show-checkbox
              @check="columnChange"
          ></el-tree>
          <template #reference>
            <el-button circle icon="Menu"/>
          </template>
        </el-popover>
      </el-tooltip>
    </el-row>
  </div>
</template>

<script name="TableToolBar" setup>


;

const props = defineProps({
  showSearch: {
    type: Boolean,
    default: true
  },
  columns: {
    type: Array
  },
  search: {
    type: Boolean,
    default: true
  },
  gutter: {
    type: Number,
    default: 10
  },
})

const columnRef = ref();
const emits = defineEmits(['update:showSearch', 'queryTable']);

const style = computed(() => {
  const ret = {};
  if (props.gutter) {
    ret.marginRight = `${props.gutter / 2}px`;
  }
  return ret;
});

// 搜索
function toggleSearch() {
  emits("update:showSearch", !props.showSearch);
}

// 刷新
function refresh() {
  emits("queryTable");
}

// 更改数据列的显示和隐藏
function columnChange(...args) {
  props.columns?.forEach((item) => {
    item.visible = args[1].checkedKeys.includes(item.key);
  })
}

// 显隐列初始默认隐藏列
onMounted(() => {
  props.columns?.forEach((item) => {
    if (item.visible) {
      columnRef.value?.setChecked(item.key, true, false);
      // value.value.push(item.key);
    }
  })
})
</script>

<style lang="scss" scoped>
:deep(.el-transfer__button) {
  border-radius: 50%;
  display: block;
  margin-left: 0;
}

:deep(.el-transfer__button:first-child) {
  margin-bottom: 10px;
}

.my-el-transfer {
  text-align: center;
}

.tree-header {
  width: 100%;
  line-height: 24px;
  text-align: center;
}
</style>

