<template>
  <div>
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="用户名：" prop="username">
            <el-input v-model="queryParams.username" clearable placeholder="请输入用户名" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="姓名：" prop="name">
            <el-input v-model="queryParams.name" clearable placeholder="请输入姓名" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="手机号：" prop="phone">
            <el-input v-model="queryParams.phone" clearable placeholder="请输入手机号" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="部门：" prop="deptId">
            <el-input-number v-model="queryParams.deptId" clearable placeholder="请输入部门" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="是否启用：" prop="enable">
            <el-switch v-model="queryParams.enable" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
          </el-form-item>
          <el-form-item>
            <el-button icon="Search" type="primary" @click="query">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <el-card shadow="hover">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysUser:add'" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysUser:remove'" :disabled="!ids.length" icon="Delete" plain type="danger" @click="remove">删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysUser:import'" icon="Upload" plain type="primary" @click="uploadDialog.visible=true">导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysUser:export'" icon="Download" plain type="primary" @click="exportData">导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysUser:export'" icon="Download" plain type="primary" @click="downloadTemplate">下载模板</el-button>
          </el-col>
          <table-tool-bar v-model:showSearch="showSearch" @queryTable="pageList"/>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" stripe @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id"/>
        <el-table-column :show-overflow-tooltip="true" label="用户名" prop="username"/>
        <el-table-column :show-overflow-tooltip="true" label="姓名" prop="name"/>
        <el-table-column :show-overflow-tooltip="true" label="手机号" prop="phone"/>
        <el-table-column :show-overflow-tooltip="true" label="部门" prop="deptId"/>
        <el-table-column :show-overflow-tooltip="true" label="是否启用" prop="enable">
          <template #default="scope">
            {{ scope.row.enable ? '是' : '否' }}
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column :show-overflow-tooltip="true" label="岗位" prop="posts"/>
        <el-table-column :show-overflow-tooltip="true" label="角色" prop="roles"/>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button v-hasPermission="'sys:sysUser:edit'" icon="Edit" link type="primary" @click="edit(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button v-hasPermission="'sys:sysUser:remove'" icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <paginationBar v-model:limit="queryParams.size" v-model:page="queryParams.current" :total="total" @pagination="pageList"/>
    </el-card>
    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-scrollbar>
        <el-form ref="formRef" :model="formData" :rules="rules" label-position="left" label-width="120px" status-icon>
          <el-form-item label="用户名：" prop="username">
            <el-input v-model="formData.username" clearable placeholder="请输入用户名"/>
          </el-form-item>
          <el-form-item label="姓名：" prop="name">
            <el-input v-model="formData.name" clearable placeholder="请输入姓名"/>
          </el-form-item>
          <el-form-item label="手机号：" prop="phone">
            <el-input v-model="formData.phone" clearable placeholder="请输入手机号"/>
          </el-form-item>
          <el-form-item label="部门：" prop="deptId">
            <el-input-number v-model="formData.deptId" clearable placeholder="请输入部门"/>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input v-model="formData.password" clearable placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item label="是否启用：" prop="enable">
            <el-switch v-model="formData.enable" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
          </el-form-item>
          <el-form-item label="排序：" prop="sort">
            <el-input-number v-model="formData.sort" clearable placeholder="请输入排序"/>
          </el-form-item>
          <el-form-item label="岗位：" prop="posts">
            <el-select v-model="formData.posts" clearable placeholder="请选择岗位">
              <el-option v-for="item in postsOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="角色：" prop="roles">
            <el-select v-model="formData.roles" clearable placeholder="请选择角色">
              <el-option v-for="item in rolesOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-form>
      </el-scrollbar>
      <template #footer>
        <div style="flex: auto">
          <el-button @click="cancel">取消</el-button>
          <el-button @click="reset">重置</el-button>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </div>
      </template>
    </el-dialog>
    <el-dialog v-model="uploadDialog.visible" :title="uploadDialog.title" align-center>
      <el-button icon="Download" plain type="primary" @click="downloadTemplate()">下载模板</el-button>
      <el-upload :action="`${constant.baseApi}/sysUser/import`" :on-success="uploadSuccess" accept=".xlsx" drag>
        <el-icon class="el-icon--upload">
          <upload-filled/>
        </el-icon>
        <div class="el-upload__text">
          <em>拖入或点击以进行上传</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">xlsx文件</div>
        </template>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script setup>
defineOptions({
  name: 'SysUser'
})
import {UploadFilled} from '@element-plus/icons-vue'
import TableToolBar from '@/components/tableToolBar.vue'
import PaginationBar from '@/components/paginationBar.vue'

const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const total = ref(0)
const queryFormRef = ref()
const formRef = ref()
const dialog = reactive({
  visible: false,
  title: ''
})
const uploadDialog = reactive({
  visible: false,
  title: '上传用户'
})
const initFormData = {
  id: undefined,
  username: undefined,
  name: undefined,
  phone: undefined,
  deptId: 0,
  password: undefined,
  enable: true,
  sort: 0,
  posts: undefined,
  roles: undefined,
}
const queryParams = ref({
  current: 1,
  size: 10,
  username: undefined,
  name: undefined,
  phone: undefined,
  deptId: undefined,
  enable: undefined,
})
const formData = ref({...initFormData})
const rules = ref({
  username: [relus.required('用户名'), relus.maxLength('用户名', 50),],
  name: [relus.required('姓名'), relus.maxLength('姓名', 50),],
  phone: [relus.maxLength('手机号', 11),],
  password: [relus.required('密码'), relus.maxLength('密码', 128),],
})
const postsOptions = ref([])
const rolesOptions = ref([])

/**
 * 查询用户列表
 */
const pageList = async () => {
  loading.value = true
  sysUserApi.page(queryParams.value).then(res => {
    dataList.value = res.rows
    total.value = res.total
    loading.value = false
  })
}

/**
 * 取消按钮
 */
const cancel = () => {
  reset()
  dialog.visible = false
}

/**
 * 表单重置
 */
const reset = () => {
  formRef.value?.resetFields()
  formData.value = {...initFormData}
}

/**
 * 搜索
 */
const query = () => {
  queryParams.value.current = 1
  pageList()
}

/**
 * 重置
 */
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  query()
}

/**
 * 多选框选中数据
 */
const selectionChange = (selection) => {
  ids.value = selection.map(item => item.id)
}

/**
 * 新增
 */
const add = () => {
  reset()
  dialog.visible = true
  dialog.title = '添加用户'
}

/**
 * 修改
 */
const edit = async (row) => {
  reset()
  const id = row?.id
  sysUserApi.getById(id).then(res => {
    Object.assign(formData.value, res.data)
    dialog.visible = true
    dialog.title = '修改用户'
  })
}

/**
 * 提交按钮
 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      sysUserApi.save(formData.value).then(res => {
        ElMessage.success(res.message)
        dialog.visible = false
        pageList()
      })
    }
  })
}

/**
 * 删除
 */
const remove = (row) => {
  const _ids = row?.id ? [row?.id] : ids.value
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的用户数据项？').then(() => {
    sysUserApi.remove(_ids).then(res => {
      ElMessage.success(res.message)
      pageList()
    })
  }).catch((err) => {
    console.log(err)
  })
}

/**
 * 下载模板
 */
const downloadTemplate = () => {
  sysUserApi.downloadTemplate()
}

/**
 * 导出
 */
const exportData = () => {
  sysUserApi.exportData()
}

/**
 * 导入结果提示
 */
const uploadSuccess = (res) => {
  uploadDialog.visible = false
  ElMessage.success(res.message)
  pageList()
}

onMounted(() => {
  nextTick(() => {
    pageList()
    // commonApi.getDictOptions('List').then(res => postsOptions.value = res.data)
    // commonApi.getDictOptions('List').then(res => rolesOptions.value = res.data)
  })
})
</script>
