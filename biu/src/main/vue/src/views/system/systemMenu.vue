<template>
  <el-card class="main-search" shadow="hover">
    <el-form ref="queryFormRef" :model="queryForm" inline>
      <el-form-item label="父菜单id" prop="parentId">
        <el-tree-select
                v-model="queryForm.parentId" :data="parentIdOptions" :filter-node-method=" (v, data) => data.label.includes(v)"
                check-strictly
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryForm.name" clearable placeholder="请输入名称" />
      </el-form-item>
      <el-form-item label="启用" prop="enable">
        <el-radio-group v-model="queryForm.enable">
          <el-radio v-for="item in enableOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
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
      <el-table-column label="父菜单id" min-width="180" prop="parentId" sortable />
      <el-table-column label="名称" min-width="180" prop="name" sortable />
      <el-table-column label="权限编码" min-width="180" prop="permsCode" sortable />
      <el-table-column label="排序" min-width="180" prop="sort" sortable />
      <el-table-column label="路由地址" min-width="180" prop="path" sortable />
      <el-table-column label="组件路径" min-width="180" prop="component" sortable />
      <el-table-column label="菜单类型" min-width="180" prop="type" sortable />
      <el-table-column label="是否外链" min-width="180" prop="externalLink" sortable />
      <el-table-column label="可缓存" min-width="180" prop="cacheable" sortable />
      <el-table-column label="可显示" min-width="180" prop="visible" sortable />
      <el-table-column label="启用" min-width="180" prop="enable" sortable />
      <el-table-column label="菜单图标" min-width="180" prop="icon" sortable />
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
      <el-form-item label="父菜单id" prop="parentId">
        <el-tree-select
            v-model="dataForm.parentId" :data="parentIdOptions" :filter-node-method=" (v, data) => data.label.includes(v)"
            check-strictly
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" clearable placeholder="请输入名称" />
      </el-form-item>
      <el-form-item label="权限编码" prop="permsCode">
        <el-input v-model="dataForm.permsCode" clearable placeholder="请输入权限编码" />
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="dataForm.sort" :precision="0" :step="1" />
      </el-form-item>
      <el-form-item label="路由地址" prop="path">
        <el-input v-model="dataForm.path" clearable placeholder="请输入路由地址" />
      </el-form-item>
      <el-form-item label="组件路径" prop="component">
        <el-input v-model="dataForm.component" clearable placeholder="请输入组件路径" />
      </el-form-item>
      <el-form-item label="菜单类型" prop="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否外链" prop="externalLink">
        <el-radio-group v-model="dataForm.externalLink">
          <el-radio v-for="item in externalLinkOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="可缓存" prop="cacheable">
        <el-radio-group v-model="dataForm.cacheable">
          <el-radio v-for="item in cacheableOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="可显示" prop="visible">
        <el-radio-group v-model="dataForm.visible">
          <el-radio v-for="item in visibleOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="启用" prop="enable">
        <el-radio-group v-model="dataForm.enable">
          <el-radio v-for="item in enableOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="菜单图标" prop="icon">
        <el-input v-model="dataForm.icon" clearable placeholder="请输入菜单图标" />
      </el-form-item>
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
const queryForm = ref<SystemMenu & PageQuery & { any?: any, arr?: any[] }>({
  parentId: undefined,
  name: undefined,
  enable: undefined,
  pageNum: 1,
  pageSize: 10,
})
const formRef = ref<FormInstance>()
const dataForm = ref<SystemMenu>({
  parentId: undefined,
  name: undefined,
  permsCode: undefined,
  sort: undefined,
  path: undefined,
  component: undefined,
  type: undefined,
  externalLink: undefined,
  cacheable: undefined,
  visible: undefined,
  enable: undefined,
  icon: undefined,
})
const formRules = ref<FormRules<SystemMenu>>({
  name: [
    { required: true, message: '名称不可为空', trigger: 'blur' },
    { max: 50, message: '名称最大长度为50', trigger: 'blur' },
  ],
  permsCode: [
    { required: true, message: '权限编码不可为空', trigger: 'blur' },
    { max: 100, message: '权限编码最大长度为100', trigger: 'blur' },
  ],
  path: [
    { max: 200, message: '路由地址最大长度为200', trigger: 'blur' },
  ],
  component: [
    { max: 255, message: '组件路径最大长度为255', trigger: 'blur' },
  ],
  type: [
    { required: true, message: '菜单类型不可为空', trigger: 'blur' },
    { max: 9, message: '菜单类型最大长度为9', trigger: 'blur' },
  ],
  icon: [
    { max: 100, message: '菜单图标最大长度为100', trigger: 'blur' },
  ],
})
const buttonLoading = ref(false)
const tableData = ref<SystemMenu[]>([])
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
const parentIdOptions = ref<Option[]>([])
const typeOptions = ref<Option[]>([])
const externalLinkOptions = ref<Option[]>([])
const cacheableOptions = ref<Option[]>([])
const visibleOptions = ref<Option[]>([])
const enableOptions = ref<Option[]>([])

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
  systemMenuApi.page(queryForm.value).then((res) => {
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

function handleUpdate(row: SystemMenu) {
  resetForm()
  systemMenuApi.getById(row.id).then((res) => {
    dataForm.value = res.data
    dialog.value = { title: '修改部门', visible: true }
  })
}

function submitForm() {
  formRef.value?.validate((valid) => {
    if (valid) {
      systemMenuApi.save(dataForm.value).then((res) => {
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
      systemMenuApi.remove(ids).then((res) => {
        ElMessage.success(res.message)
        listData()
      })
    })
}

function handleImport() {
  upload.value.visible = true
}

function handleExport() {
  systemMenuApi.exportData(queryForm.value)
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
  systemMenuApi.downloadTemplate()
}

function importData() {
  uploadRef.value?.submit()
}

onMounted(() => {
  listData()
  parentIdOptions.value = []
  typeOptions.value = []
  externalLinkOptions.value = []
  cacheableOptions.value = []
  visibleOptions.value = []
  enableOptions.value = []
})
</script>

<style lang="scss" scoped>

</style>
