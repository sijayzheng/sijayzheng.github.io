<template>
  <el-card class="main-search" shadow="hover">
    <el-form ref="queryFormRef" :model="queryForm" inline>
      <el-form-item label="用户id" prop="userId">
        <el-select v-model="queryForm.userId" clearable placeholder="请选择用户id">
          <el-option v-for="item in userIdOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="模块标题" prop="title">
        <el-input v-model="queryForm.title" clearable placeholder="请输入模块标题" />
      </el-form-item>
      <el-form-item label="方法名称" prop="method">
        <el-input v-model="queryForm.method" clearable placeholder="请输入方法名称" />
      </el-form-item>
      <el-form-item label="请求方式" prop="requestMethod">
        <el-input v-model="queryForm.requestMethod" clearable placeholder="请输入请求方式" />
      </el-form-item>
      <el-form-item label="请求URL" prop="url">
        <el-input v-model="queryForm.url" clearable placeholder="请输入请求URL" />
      </el-form-item>
      <el-form-item label="主机地址" prop="ip">
        <el-input v-model="queryForm.ip" clearable placeholder="请输入主机地址" />
      </el-form-item>
      <el-form-item label="操作地点" prop="location">
        <el-input v-model="queryForm.location" clearable placeholder="请输入操作地点" />
      </el-form-item>
        <el-form-item label="操作时间" prop="time">
          <el-date-picker v-model="queryForm.timeRange" placeholder="选择time时间" type="datetime" />
        </el-form-item>
      <el-form-item>
        <el-button icon="search" type="primary" @click="listData">
          搜索
        </el-button>
        <el-button icon="refresh" type="primary" @click="resetQueryForm">
          重置
        </el-button>
      </el-form-item>
    </el-form>
  </el-card>
  <el-card class="main-display" shadow="never">
    <template #header>
      <el-row :gutter="10" align="middle" justify="start">
        <el-button icon="plus" plain type="primary" @click="handleAdd">
          新增
        </el-button>
        <el-button :disabled="selection.length < 1" icon="delete" plain type="danger" @click="handleDelete()">
          删除
        </el-button>
        <el-button icon="upload" plain type="warning" @click="handleImport">
          导入
        </el-button>
        <el-button icon="download" plain type="warning" @click="handleExport">
          导出
        </el-button>
        <!--        <right-toolbar v-model:show-search="showSearch" @query-table="getList" /> -->
      </el-row>
    </template>
    <el-table
      v-loading="tableDataLoading" :data="tableData" max-height="700px" @sort-change="sorted"
      @selection-change="(rows) => selection = rows.map(r => r.id)"
    >
      <el-table-column align="center" fixed type="selection" width="32" />
      <el-table-column align="center" fixed="right" label="操作" width="90px">
        <template #default="scope">
          <el-tooltip content="修改" placement="top">
            <el-button icon="edit" link type="primary" @click="handleUpdate(scope.row)" />
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button icon="delete" link type="primary" @click="handleDelete(scope.row.id)" />
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <el-row align="middle" class="main-pagination" justify="end">
      <el-pagination
        v-model:current-page="queryForm.pageNum"
        v-model:page-size="queryForm.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        background
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="listData"
        @current-change="listData"
      />
    </el-row>
  </el-card>
  <el-dialog v-model="dialog.visible" :title="dialog.title" append-to-body width="1000px">
    <el-form ref="formRef" :model="dataForm" :rules="formRules" label-width="120px" status-icon>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">
          确定
        </el-button>
        <el-button @click="cancel">
          取消
        </el-button>
      </div>
    </template>
  </el-dialog>
  <el-dialog v-model="upload.visible" append-to-body title="上传标题" width="1000px">
    <el-upload
      ref="uploadRef"
      :action="`${upload.url}?updateSupport=${upload.updateSupport}`"
      :auto-upload="false"
      :disabled="upload.isUploading"
      :limit="1"
      :on-progress="() => upload.isUploading = true"
      :on-success="handleFileSuccess"
      accept=".xlsx"
      drag
    >
      <el-icon class="el-icon--upload">
        <UploadFilled />
      </el-icon>
      <div class="el-upload__text">
        将文件拖到此处，或<em>点击上传</em>
      </div>
      <template #tip>
        <div class="el-upload__tip text-center">
          <b>仅允许导入xlsx格式文件。</b>
          <el-link :underline="false" style="font-size: 12px; vertical-align: baseline" type="primary" @click="downloadTemplate">
            下载模板
          </el-link>
        </div>
      </template>
    </el-upload>
    <template #footer>
      <div class="dialog-footer">
        <el-button :loading="upload.isUploading" type="primary" @click="importData">
          确定
        </el-button>
        <el-button @click="upload.visible = false">
          取消
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { UploadFilled } from '@element-plus/icons-vue'

const queryFormRef = ref<FormInstance>()
const queryForm = ref<LogOperate & PageQuery & { any?: any, arr?: any[] }>({
  userId: undefined,
  title: undefined,
  method: undefined,
  requestMethod: undefined,
  url: undefined,
  ip: undefined,
  location: undefined,
  time: undefined,
  pageNum: 1,
  pageSize: 10,
})
const formRef = ref<FormInstance>()
const dataForm = ref<LogOperate>({
})
const formRules = ref<FormRules<LogOperate>>({
})
const buttonLoading = ref(false)
const tableData = ref<LogOperate[]>([])
const tableDataLoading = ref(false)
const total = ref<number>(0)
const dialog = ref({ title: '', visible: false })
const selection = ref<number[]>([])
const uploadRef = ref()
const upload = ref({
  visible: false,
  url: systemDeptApi.importUrl,
  updateSupport: false,
  isUploading: false,
  headers: '',
})

function resetQueryForm() {
  queryFormRef.value?.resetFields()
  listData()
}

function sorted(sort: { order: string, prop: string }) {
  if (sort.order) {
    queryForm.value.field = sort.prop
    queryForm.value.asc = sort.order === 'ascending'
  } else {
    queryForm.value.field = ''
  }
}

function listData() {
  tableDataLoading.value = true
  logOperateApi.page(queryForm.value).then((res) => {
    tableData.value = res.rows
    total.value = res.total
    tableDataLoading.value = false
  })
}

function cancel() {
  dialog.value.visible = false
}

function resetForm() {
  formRef.value?.resetFields()
}

function handleAdd() {
  resetForm()
  dialog.value = { title: '新增部门', visible: true }
}

function handleUpdate(row: LogOperate) {
  resetForm()
  logOperateApi.getById(row.id).then((res) => {
    dataForm.value = res.data
    dialog.value = { title: '修改部门', visible: true }
  })
}

function submitForm() {
  formRef.value?.validate((valid) => {
    if (valid) {
      logOperateApi.save(dataForm.value).then((res) => {
        ElMessage.success(res.message)
        dialog.value.visible = false
        listData()
      })
    }
  })
}

function handleDelete(id?: number) {
  const ids = id ? [id] : selection.value
  modal.confirm(`是否确认删除部门编号为"${ids}"的数据项？`)
    .then(() => {
      logOperateApi.remove(ids).then((res) => {
        ElMessage.success(res.message)
        listData()
      })
    })
}

function handleImport() {
  upload.value.visible = true
}

function handleExport() {
  logOperateApi.exportData(queryForm.value)
}

function handleFileSuccess(response: any, file: UploadFile) {
  upload.value.visible = false
  upload.value.isUploading = false
  uploadRef.value?.handleRemove(file)
  ElMessageBox.alert(`<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>${response.message}</div>`, '导入结果', {
    dangerouslyUseHTMLString: true,
  })
  listData()
}

function downloadTemplate() {
  logOperateApi.downloadTemplate()
}

function importData() {
  uploadRef.value?.submit()
}

onMounted(() => {
  listData()
})
</script>

<style lang="scss" scoped>

</style>
