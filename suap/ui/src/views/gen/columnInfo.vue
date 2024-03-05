<template>
  <el-table v-loading="loading" :data="dataList" max-height="600px" scrollbar-always-on stripe>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" fixed label="列名" prop="name">
      <template #default="scope">
        <el-input v-model="scope.row.name" :disabled="scope.row.disable"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="列注释" prop="comment">
      <template #default="scope">
        <el-input v-model="scope.row.comment" :disabled="scope.row.disable"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="列类型" prop="type">
    </el-table-column>
    <el-table-column :min-width="110" :show-overflow-tooltip="true" label="长度" prop="length">
      <template #default="scope">
        <el-input-number v-model="scope.row.length" :disabled="scope.row.disable" controls-position="right" style="width: 80px"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="110" :show-overflow-tooltip="true" label="小数位" prop="scale">
      <template #default="scope">
        <el-input-number v-model="scope.row.scale" :disabled="scope.row.disable" controls-position="right" style="width: 80px"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="字段名称" prop="fieldName">
      <template #default="scope">
        <el-input v-model="scope.row.fieldName" :disabled="scope.row.disable"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="实体类类型" prop="javaType">
      <template #default="scope">
        <el-select v-model="scope.row.javaType" :disabled="scope.row.disable">
          <el-option v-for="item in javaTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="110" :show-overflow-tooltip="true" label="排序" prop="sort">
      <template #default="scope">
        <el-input-number v-model="scope.row.sort" :disabled="scope.row.disable" controls-position="right" style="width: 80px"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否父类字段" prop="superColumn">
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否唯一" prop="uniqueKey">
      <template #default="scope">
        <el-switch v-model="scope.row.uniqueKey" :disabled="scope.row.disable" active-text="是" active-value="是" inactive-text="否"
                   inactive-value="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否显示" prop="visible">
      <template #default="scope">
        <el-switch v-model="scope.row.visible" :disabled="scope.row.disable" active-text="是" active-value="是" inactive-text="否"
                   inactive-value="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否可编辑" prop="editable">
      <template #default="scope">
        <el-switch v-model="scope.row.editable" :disabled="scope.row.disable" active-text="是" active-value="是" inactive-text="否"
                   inactive-value="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否必填" prop="required">
      <template #default="scope">
        <el-switch v-model="scope.row.required" :disabled="scope.row.disable" active-text="是" active-value="是" inactive-text="否"
                   inactive-value="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="输入类型" prop="inputType">
      <template #default="scope">
        <el-select v-model="scope.row.inputType" :disabled="scope.row.disable">
          <el-option v-for="item in inputTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否可查询" prop="queryable">
      <template #default="scope">
        <el-switch v-model="scope.row.queryable" :disabled="scope.row.disable" active-text="是" active-value="是" inactive-text="否"
                   inactive-value="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="查询方式" prop="queryType">
      <template #default="scope">
        <el-select v-model="scope.row.queryType" :disabled="scope.row.disable">
          <el-option v-for="item in queryTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="数据来源" prop="dataSource">
      <template #default="scope">
        <el-select v-model="scope.row.dataSource" :disabled="scope.row.disable">
          <el-option v-for="item in dataSourceOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="数据" prop="data">
      <template #default="scope">
        <el-input v-model="scope.row.data" :disabled="scope.row.disable"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="100" :show-overflow-tooltip="true" label="是否是导入导出字段" prop="excelColumn">
      <template #default="scope">
        <el-switch v-model="scope.row.excelColumn" :disabled="scope.row.disable" active-text="是" active-value="是" inactive-text="否"
                   inactive-value="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
  </el-table>
</template>

<script name="ColumnInfo" setup>
const props = defineProps({
  tableName: {
    type: String,
    default: ''
  },
  superClass: {
    type: String,
    default: ''
  },
  tableId: {
    type: Number,
    default: undefined
  }
})
const dataList = ref([])

const loading = ref(false)
const javaTypeOptions = ref([])
const inputTypeOptions = ref([])
const queryTypeOptions = ref([])
const dataSourceOptions = ref([])

const initData = () => {
  loading.value = true
  commonApi.listEnumDataOptions('JavaType').then(res => javaTypeOptions.value = res.data)
  commonApi.listEnumDataOptions('InputType').then(res => inputTypeOptions.value = res.data)
  commonApi.listEnumDataOptions('QueryType').then(res => queryTypeOptions.value = res.data)
  commonApi.listEnumDataOptions('DataSource').then(res => dataSourceOptions.value = res.data)
  if (props.tableId) {
    genApi.listColumnByTableId(props.tableId).then(res => {
      dataList.value = res.data
      loading.value = false
    })
  } else {
    genApi.listTableColumns(props.tableName, props.superClass).then(res => {
      dataList.value = res.data
      loading.value = false
    })
  }
}

const getData = () => {
  return dataList.value
}
defineExpose({
  initData,
  getData
})
</script>

<style scoped>

</style>