<template>
  <el-form ref="tableFormRef" :model="tableForm" :rules="tableFormRules" label-position="left" label-width="120px" status-icon>
    <el-form-item label="表名：" prop="tableName">
      <el-select v-model="tableForm.tableName" clearable placeholder="请选择表名" @change="selectTable">
        <el-option v-for="item in tableNameOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="表备注：" prop="comment">
      <el-input v-model="tableForm.comment" clearable placeholder="请输入表备注"/>
    </el-form-item>
    <el-form-item label="实体类名称：" prop="className">
      <el-input v-model="tableForm.className" clearable placeholder="请输入实体类名称"/>
    </el-form-item>
    <el-form-item label="模板类型：" prop="template">
      <el-select v-model="tableForm.template" clearable placeholder="请选择模板类型">
        <el-option v-for="item in templateOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="包路径：" prop="packageName">
      <el-input v-model="tableForm.packageName" clearable placeholder="请输入包路径"/>
    </el-form-item>
    <el-form-item label="模块名：" prop="module">
      <el-input v-model="tableForm.module" clearable placeholder="请输入模块名"/>
    </el-form-item>
    <el-form-item label="作者：" prop="author">
      <el-input v-model="tableForm.author" clearable placeholder="请输入作者"/>
    </el-form-item>
    <el-form-item label="生成方式：" prop="genType">
      <el-select v-model="tableForm.genType" clearable placeholder="请选择生成方式">
        <el-option v-for="item in genTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="父类：" prop="superClass">
      <el-select v-model="tableForm.superClass" clearable placeholder="请选择父类">
        <el-option v-for="item in superClassOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="所属菜单：" prop="menuId">
      <el-tree-select v-model="tableForm.menuId" :data="menuIdOptions" accordion check-strictly clearable filterable highlight-current
                      placeholder="请选择所属菜单"/>
    </el-form-item>
  </el-form>
</template>

<script name="TableInfo" setup>
const tableForm = ref({})
const tableFormRef = ref();
const tableFormRules = ref({
  tableName: [relus.required('表名'), relus.maxLength('表名', 50)],
  comment: [relus.required('表备注'), relus.maxLength('表备注', 200)],
  className: [relus.required('实体类'), relus.maxLength('实体类名称', 100)],
  template: [relus.required('模板类型'),],
  packageName: [relus.required('包路径'), relus.maxLength('包路径', 100)],
  module: [relus.required('模块名'), relus.maxLength('模块名', 30)],
  author: [relus.required('作者'), relus.maxLength('作者', 50)],
  genType: [relus.required('生成方式'),],
  menuId: [relus.required('所属菜单'),],
})

const templateOptions = ref([])
const genTypeOptions = ref([])
const superClassOptions = ref([])
const menuIdOptions = ref([])
const tableNameOptions = ref([])

const tableMap = ref([])
const selectTable = (value) => {
  let tableInfo = tableMap.value[value]
  tableForm.value.tableName = tableInfo.tableName
  tableForm.value.comment = tableInfo.comment
  tableForm.value.className = tableInfo.className
  tableForm.value.module = tableInfo.module
}
const initData = (data) => {
  tableForm.value = data
  commonApi.listEnumDataOptions('TemplateType').then(res => templateOptions.value = res.data)
  commonApi.listEnumDataOptions('GenType').then(res => genTypeOptions.value = res.data)
  commonApi.listEnumDataOptions('SuperClassEnum').then(res => superClassOptions.value = res.data)
  commonApi.listTableDataOptions('sys_menu').then(res => {
    menuIdOptions.value = res.data
    tableForm.value.menuId = data?.id ? data.id : '1'
  })
  genApi.listTable().then(res => {
    let map = {}, options = []
    res.data.forEach(item => {
      options.push({
        "label": `${item.tableName}(${item.comment})`,
        "value": item.tableName
      })
      map[item.tableName] = item
    })
    tableNameOptions.value = options
    tableMap.value = map
  })
  genApi.getGenProperties().then(res => {
    const data = res.data
    tableForm.value.template = data.template
    tableForm.value.packageName = data.packageName
    tableForm.value.author = data.author
    tableForm.value.genType = data.genType
  })
}
const getData = () => {
  return tableForm.value
}
const validate = (fun) => {
  return tableFormRef.value?.validate(fun)
}
defineExpose({
  initData,
  getData,
  validate
})
</script>

<style scoped>

</style>