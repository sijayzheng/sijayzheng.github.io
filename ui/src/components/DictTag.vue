<template>
  <div>
    <template v-for="(item, index) in options">
      <template v-if="values.includes(item.value)">
        <span
            v-if="(item.elTagType === 'default' || item.elTagType === '') && (item.elTagClass === '' || item.elTagClass === null)"
            :key="item.value"
            :class="item.elTagClass"
            :index="index"
        >
          {{ item.label + ' ' }}
        </span>
        <el-tag
            v-else
            :key="item.value + ''"
            :class="item.elTagClass"
            :disable-transitions="true"
            :index="index"
            :type="
            item.elTagType === 'primary' ||
              item.elTagType === 'success' ||
              item.elTagType === 'info' ||
              item.elTagType === 'warning' ||
              item.elTagType === 'danger'
              ? item.elTagType
              : 'primary'
          "
        >
          {{ item.label + ' ' }}
        </el-tag>
      </template>
    </template>
    <template v-if="unmatch && showValue">
      {{ unmatchArray }}
    </template>
  </div>
</template>

<script setup>
const props = withDefaults(defineProps(), {
  showValue: true,
  separator: ','
})

const values = computed(() => {
  if (props.value === '' || props.value === null || typeof props.value === 'undefined') {
    return []
  }
  return Array.isArray(props.value) ? props.value.map((item) => '' + item) : String(props.value).split(props.separator)
})

const unmatch = computed(() => {
  if (props.options?.length === 0 || props.value === '' || props.value === null || typeof props.value === 'undefined') {
    return false
  }
  // 传入值为非数组
  let unmatch = false // 添加一个标志来判断是否有未匹配项
  values.value.forEach((item) => {
    if (!props.options.some((v) => v.value === item)) {
      unmatch = true // 如果有未匹配项，将标志设置为true
    }
  })
  return unmatch // 返回标志的值
})

const unmatchArray = computed(() => {
  // 记录未匹配的项
  const itemUnmatchArray = []
  if (props.value !== '' && props.value !== null && typeof props.value !== 'undefined') {
    values.value.forEach((item) => {
      if (!props.options.some((v) => v.value === item)) {
        itemUnmatchArray.push(item)
      }
    })
  }
  // 没有value不显示
  return handleArray(itemUnmatchArray)
})

const handleArray = (array) => {
  if (array.length === 0) {
    return ''
  }
  return array.reduce((pre, cur) => {
    return pre + ' ' + cur
  })
}
</script>

<style scoped>
.el-tag + .el-tag {
  margin-left: 10px;
}
</style>
