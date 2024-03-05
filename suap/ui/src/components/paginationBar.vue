<template>
  <div :class="{ 'hidden': hidden }" class="pagination-container">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :pager-count="7"
        :total="total"
        background
        layout="total, sizes, prev, pager, next, jumper"
        @change="change"
    />
  </div>
</template>

<script name="PaginationBar" setup>


const props = defineProps({
  total: {
    type: Number,
    default: 0
  },
  page: {
    type: Number,
    default: 1
  },
  limit: {
    type: Number,
    default: 20
  },
  hidden: {
    type: Boolean,
    default: false
  },
})

const emit = defineEmits(['update:page', 'update:limit', 'pagination']);
const currentPage = ref(1)
const pageSize = ref(10)

const change = (current, size) => {
  if (currentPage.value * current > props.total) {
    currentPage.value = 1
  }
  emit('update:page', current)
  emit('update:limit', size)
  emit('pagination', {
    page: currentPage.value,
    limit: size
  })
}
</script>

<style lang="scss" scoped>
.pagination-container {
  padding: 32px 16px;

  .el-pagination {
    float: right;
  }
}

.pagination-container.hidden {
  display: none;
}

</style>