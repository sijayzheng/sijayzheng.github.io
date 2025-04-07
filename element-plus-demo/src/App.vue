<template>
  <div>
    <el-table :data="tableData" :span-method="objectSpanMethod1" border style="width: 100%; margin-top: 20px">
      <el-table-column prop="name" label="Name" />
      <el-table-column prop="gender" label="gender" />
      <el-table-column prop="amount" label="Amount" />
    </el-table>
  </div>

</template>

<script setup>
import { onMounted, ref } from 'vue'
import { getSpanArr } from './utils'

const tableData = ref([
  { name: 'Tom', gender: '男', amount: '234' },
  { name: 'Tom', gender: '男', amount: '165' },
  { name: 'Tom', gender: '男', amount: '324' },
  { name: 'jane', gender: '女', amount: '621' },
  { name: 'jane', gender: '女', amount: '539' },
])
const spanArr = ['name', 'gender']
const spanMapper = {}
onMounted(() => {
  spanArr.forEach((key) => {
    spanMapper[key] = getSpanArr(tableData.value, key)
  })
  console.log(spanMapper)
})

const objectSpanMethod1 = ({ column, rowIndex }) => {
  for (const key in spanMapper) {
    if (column.property === key) {
      const _row = spanMapper[key][rowIndex]
      const _col = _row > 0 ? 1 : 0
      return {
        rowspan: _row,
        colspan: _col,
      }
    }
  }
}
</script>
