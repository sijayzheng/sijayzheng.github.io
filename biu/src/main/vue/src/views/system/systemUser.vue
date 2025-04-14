<template>
  <el-card class="main-search" shadow="hover">
    <el-form ref="queryFormRef" :model="queryForm" inline>
      <el-form-item label="账号" prop="username">
        <el-input v-model="queryForm.username" clearable placeholder="请输入账号" />
      </el-form-item>
      <el-form-item label="姓名" prop="realName">
        <el-input v-model="queryForm.realName" clearable placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="queryForm.phone" clearable placeholder="请输入手机号码" />
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
      <el-table-column label="账号" min-width="180" prop="username" sortable />
      <el-table-column label="部门" min-width="180" prop="deptId" sortable />
      <el-table-column label="姓名" min-width="180" prop="realName" sortable />
      <el-table-column label="邮箱" min-width="180" prop="email" sortable />
      <el-table-column label="手机号码" min-width="180" prop="phone" sortable />
      <el-table-column label="性别" min-width="180" prop="gender" sortable />
      <el-table-column label="头像" min-width="180" prop="avatar" sortable />
      <el-table-column label="密码" min-width="180" prop="password" sortable />
      <el-table-column label="角色" min-width="180" prop="roles" sortable />
      <el-table-column label="岗位" min-width="180" prop="posts" sortable />
      <el-table-column label="模块" min-width="180" prop="modules" sortable />
      <el-table-column label="启用" min-width="180" prop="enable" sortable />
      <el-table-column label="密码是否更改" min-width="180" prop="pwdChanged" sortable />
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
      <el-form-item label="账号" prop="username">
        <el-input v-model="dataForm.username" clearable placeholder="请输入账号" />
      </el-form-item>
      <el-form-item label="部门" prop="deptId">
        <el-tree-select
            v-model="dataForm.deptId" :data="deptIdOptions" :filter-node-method=" (v, data) => data.label.includes(v)"
            check-strictly
        />
      </el-form-item>
      <el-form-item label="姓名" prop="realName">
        <el-input v-model="dataForm.realName" clearable placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" clearable placeholder="请输入邮箱" />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="dataForm.phone" clearable placeholder="请输入手机号码" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="dataForm.gender">
          <el-radio v-for="item in genderOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-radio-group>
      </el-form-item>
    <el-form-item label="头像" prop="avatar">
        <!--      IMAGE_UPLOAD("图片上传"), -->
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="dataForm.password" clearable placeholder="请输入密码" />
      </el-form-item>
      <el-form-item label="角色" prop="roles">
        <el-select v-model="dataForm.roles" clearable placeholder="请选择角色">
          <el-option v-for="item in rolesOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="岗位" prop="posts">
        <el-select v-model="dataForm.posts" clearable placeholder="请选择岗位">
          <el-option v-for="item in postsOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="模块" prop="modules">
        <el-select v-model="dataForm.modules" clearable placeholder="请选择模块">
          <el-option v-for="item in modulesOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="启用" prop="enable">
        <el-switch v-model="dataForm.enable" />
      </el-form-item>
      <el-form-item label="密码是否更改" prop="pwdChanged">
        <el-switch v-model="dataForm.pwdChanged" />
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
const queryForm = ref<SystemUser & PageQuery & { any?: any, arr?: any[] }>({
  username: undefined,
  deptId: undefined,
  realName: undefined,
  phone: undefined,
  pageNum: 1,
  pageSize: 10,
})
const formRef = ref<FormInstance>()
const dataForm = ref<SystemUser>({
  username: undefined,
  deptId: undefined,
  realName: undefined,
  email: undefined,
  phone: undefined,
  gender: undefined,
  avatar: undefined,
  password: undefined,
  roles: undefined,
  posts: undefined,
  modules: undefined,
  enable: undefined,
  pwdChanged: undefined,
})
const formRules = ref<FormRules<SystemUser>>({
  username: [
    { required: true, message: '账号不可为空', trigger: 'blur' },
    { max: 20, message: '账号最大长度为20', trigger: 'blur' },
  ],
  realName: [
    { max: 50, message: '姓名最大长度为50', trigger: 'blur' },
  ],
  email: [
    { max: 100, message: '邮箱最大长度为100', trigger: 'blur' },
  ],
  phone: [
    { max: 11, message: '手机号码最大长度为11', trigger: 'blur' },
  ],
  gender: [
    { max: 7, message: '性别最大长度为7', trigger: 'blur' },
  ],
  password: [
    { max: 255, message: '密码最大长度为255', trigger: 'blur' },
  ],
})
const buttonLoading = ref(false)
const tableData = ref<SystemUser[]>([])
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
const deptIdOptions = ref<Option[]>([])
const genderOptions = ref<Option[]>([])
const rolesOptions = ref<Option[]>([])
const postsOptions = ref<Option[]>([])
const modulesOptions = ref<Option[]>([])

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
  systemUserApi.page(queryForm.value).then((res) => {
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

function handleUpdate(row: SystemUser) {
  resetForm()
  systemUserApi.getById(row.id).then((res) => {
    dataForm.value = res.data
    dialog.value = { title: '修改部门', visible: true }
  })
}

function submitForm() {
  formRef.value?.validate((valid) => {
    if (valid) {
      systemUserApi.save(dataForm.value).then((res) => {
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
      systemUserApi.remove(ids).then((res) => {
        ElMessage.success(res.message)
        listData()
      })
    })
}

function handleImport() {
  upload.value.visible = true
}

function handleExport() {
  systemUserApi.exportData(queryForm.value)
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
  systemUserApi.downloadTemplate()
}

function importData() {
  uploadRef.value?.submit()
}

onMounted(() => {
  listData()
  deptIdOptions.value = []
  genderOptions.value = []
  rolesOptions.value = []
  postsOptions.value = []
  modulesOptions.value = []
})
</script>

<style lang="scss" scoped>

</style>
