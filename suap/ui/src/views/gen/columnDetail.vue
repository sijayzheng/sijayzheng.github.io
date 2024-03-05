<template>
  <el-drawer v-model="visible" size="100%" @close="close">
    <template #header>
      <h4>表{{ tableName }}列信息</h4>
    </template>
    <template #default>
      <el-table v-loading="loading" :data="dataList" max-height="600px" scrollbar-always-on stripe>
        <el-table-column :min-width="130" :show-overflow-tooltip="true" fixed label="列名" prop="name"/>
        <el-table-column :min-width="130" :show-overflow-tooltip="true" label="列注释" prop="comment"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="列类型" prop="type"/>
        <el-table-column :min-width="70" :show-overflow-tooltip="true" label="长度" prop="length"/>
        <el-table-column :min-width="70" :show-overflow-tooltip="true" label="小数位" prop="scale"/>
        <el-table-column :min-width="130" :show-overflow-tooltip="true" label="字段名称" prop="fieldName"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="实体类类型" prop="javaType"/>
        <el-table-column :min-width="60" :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否父类字段" prop="superColumn"/>
        <el-table-column :min-width="80" :show-overflow-tooltip="true" label="是否唯一" prop="uniqueKey"/>
        <el-table-column :min-width="80" :show-overflow-tooltip="true" label="是否显示" prop="visible"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否可编辑" prop="editable"/>
        <el-table-column :min-width="80" :show-overflow-tooltip="true" label="是否必填" prop="required"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="输入类型" prop="inputType"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否可查询" prop="queryable"/>
        <el-table-column :min-width="80" :show-overflow-tooltip="true" label="查询方式" prop="queryType"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="数据来源" prop="dataSource"/>
        <el-table-column :min-width="110" :show-overflow-tooltip="true" label="数据" prop="data"/>
        <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否是导入导出字段" prop="excelColumn"/>
      </el-table>
    </template>
  </el-drawer>
</template>

<script name="ColumnDetail" setup>
const dataList = ref([])
const loading = ref(false)
const visible = ref(false)
const tableName = ref('')


const open = (row) => {
  visible.value = true
  loading.value = true
  tableName.value = row?.tableName
  if (row?.id) {
    genApi.listColumnByTableId(row?.id).then(res => {
      dataList.value = res.data
      loading.value = false
    })
  } else {
    loading.value = false
  }
}
const close = () => {
  visible.value = false
}

defineExpose({
  open,
})
</script>

<style scoped>

</style>
