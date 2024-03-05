<template>
  <el-drawer v-model="drawer.visible" size="100%" @close="close">
    <template #header>
      <h4>{{ drawer.title }}</h4>
    </template>
    <template #default>
      <el-row style="margin-bottom: 32px">
        <el-col :md="18" :xl="19">
          <el-steps :active="septIndex" align-center finish-status="success">
            <el-step title="表信息"/>
            <el-step :title="title"/>
            <el-step title="代码预览"/>
          </el-steps>
        </el-col>
        <el-col :md="6" :xl="5">
          <el-button type="primary" @click="septIndex = septIndex > 0?septIndex-1:0">上一步</el-button>
          <el-button type="primary" @click="next">下一步</el-button>
          <el-button type="danger" @click="close">关闭</el-button>
          <el-button :disabled="canDownload" type="success" @click="download">下载代码</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-scrollbar max-height="600px">
            <div v-if="septIndex === 0">
              <table-info ref="tableInfoRef"/>
            </div>
            <div v-else-if="septIndex === 1">
              <column-info ref="columnInfoRef" :super-class="tableForm.superClass" :table-id="tableId" :table-name="tableForm.tableName"/>
            </div>
            <div v-else-if="septIndex === 2">
              <preview-code ref="previewRef"/>
            </div>
          </el-scrollbar>
        </el-col>
      </el-row>
    </template>
  </el-drawer>
</template>

<script name="Generate" setup>
const drawer = reactive({
  visible: false,
  title: '添加表信息'
});
const emit = defineEmits({successful: null})
const tableForm = ref({})
const dataList = ref([])
const tableInfoRef = ref()
const columnInfoRef = ref()
const septIndex = ref(-1)
const title = ref('表的列信息')
const previewRef = ref()
const canDownload = ref(true)
const tableId = ref(undefined)

const open = (record) => {
  drawer.visible = true;
  tableForm.value = {
    id: undefined,
    tableName: undefined,
    comment: undefined,
    className: undefined,
    template: undefined,
    packageName: undefined,
    module: undefined,
    author: undefined,
    genType: undefined,
    superClass: undefined,
    menuId: undefined,
  }
  if (record?.id) {
    tableId.value = record.id
    drawer.title = '修改表信息'
    genApi.getTableById(record?.id).then(res => {
      Object.assign(tableForm.value, res.data)
    })
  }
  septIndex.value = 0
}
const next = () => {
  if (septIndex.value === 0) {
    tableInfoRef.value.validate((valid) => {
      if (valid) {
        septIndex.value = 1
        tableForm.value = tableInfoRef.value.getData()
        title.value = `表[${tableForm.value.tableName}]的列信息`
      }
    })
  } else if (septIndex.value === 1) {
    dataList.value = columnInfoRef.value.getData()
    tableForm.value.fields = dataList.value
    if (tableId.value) {
      genApi.update(tableForm.value).then(res => {
        if (res.data.x.includes('成功')) {
          ElMessage.success(res.data.x)
          septIndex.value = 2
          tableId.value = res.data.y
          nextTick(() => {
            previewRef.value.preview(res.data.y)
          })
          canDownload.value = false
        } else {
          ElMessage.error(res.data.x)
        }
      })
    } else {
      genApi.save(tableForm.value).then(res => {
        if (res.data.x.includes('成功')) {
          ElMessage.success(res.data.x)
          septIndex.value = 2
          tableId.value = res.data.y
          nextTick(() => {
            previewRef.value.preview(res.data.y)
          })
          canDownload.value = false
        } else {
          ElMessage.error(res.data.x)
        }
      })
    }
  } else if (septIndex.value === 2) {
    septIndex.value = 2
  }
}
const close = () => {
  drawer.visible = false
  septIndex.value = undefined
  tableId.value = undefined
  emit('successful')
}
const download = () => {
  genApi.download(tableId.value)
}
watch(tableInfoRef, (val) => val?.initData(tableForm.value))
watch(columnInfoRef, (val) => val?.initData())

defineExpose({
  open
})
</script>

<style scoped>

</style>