<template>
  <div>
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="标题：" prop="title">
            <el-input v-model="queryParams.title" clearable placeholder="请输入标题" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="类型：" prop="type">
            <el-input v-model="queryParams.type" clearable placeholder="请输入类型" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="状态：" prop="status">
            <el-input v-model="queryParams.status" clearable placeholder="请输入状态" @keyup.enter="query"/>
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
            <el-button v-hasPermission="'sys:sysNotice:add'" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button :disabled="!ids.length" v-hasPermission="'sys:sysNotice:remove'" icon="Delete" plain type="danger" @click="remove">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysNotice:import'" icon="Upload" plain type="primary" @click="uploadDialog.visible=true">导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysNotice:export'" icon="Download" plain type="primary" @click="exportData">导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysNotice:export'" icon="Download" plain type="primary" @click="downloadTemplate">下载模板</el-button>
          </el-col>
          <table-tool-bar v-model:showSearch="showSearch" @queryTable="pageList"/>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" stripe @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id"/>
        <el-table-column :show-overflow-tooltip="true" label="标题" prop="title"/>
        <el-table-column :show-overflow-tooltip="true" label="类型" prop="type"/>
        <el-table-column :show-overflow-tooltip="true" label="状态" prop="status"/>
        <el-table-column :show-overflow-tooltip="true" label="内容" prop="content"/>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button v-hasPermission="'sys:sysNotice:edit'" icon="Edit" link type="primary" @click="edit(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button v-hasPermission="'sys:sysNotice:remove'" icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <paginationBar v-model:limit="queryParams.size" v-model:page="queryParams.current" :total="total" @pagination="pageList"/>
    </el-card>
    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-scrollbar>
        <el-form ref="formRef" :model="formData" :rules="rules" label-position="left" label-width="120px" status-icon>
          <el-form-item label="标题：" prop="title">
            <el-input  v-model="formData.title" placeholder="请输入标题" clearable/>
          </el-form-item>
          <el-form-item label="类型：" prop="type">
            <el-input  v-model="formData.type" placeholder="请输入类型" clearable/>
          </el-form-item>
          <el-form-item label="状态：" prop="status">
            <el-input  v-model="formData.status" placeholder="请输入状态" clearable/>
          </el-form-item>
          <el-form-item label="内容：" prop="content">
            <el-input  v-model="formData.content" placeholder="请输入内容" clearable/>
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
      <el-upload :action="`${constant.baseApi}/sysNotice/import`" accept=".xlsx" :on-success="uploadSuccess" drag>
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
  name: 'SysNotice'
})
import {UploadFilled} from "@element-plus/icons-vue"
import TableToolBar from "@/components/tableToolBar.vue"
import PaginationBar from "@/components/paginationBar.vue"

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
  title: '上传通知公告'
})
const initFormData = {
  id: undefined,
  title: undefined,
  type: undefined,
  status: undefined,
  content: undefined,
}
const queryParams = ref({
  current: 1,
  size: 10,
  title: undefined,
  type: undefined,
  status: undefined,
})
const formData = ref({...initFormData})
const rules = ref({
  title: [relus.required('标题'), relus.maxLength('标题', 50),],
  type: [ relus.maxLength('类型', 10),],
  status: [ relus.maxLength('状态', 10),],
  content: [ relus.maxLength('内容', 65535),],
})

/**
 * 查询通知公告列表
 */
const pageList = async () => {
  loading.value = true
  sysNoticeApi.page(queryParams.value).then(res => {
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
  dialog.title = "添加通知公告"
}

/**
 * 修改
 */
const edit = async (row) => {
  reset()
  const id = row?.id
  sysNoticeApi.getById(id).then(res => {
    Object.assign(formData.value, res.data)
    dialog.visible = true
    dialog.title = "修改通知公告"
  })
}

/**
 * 提交按钮
 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      sysNoticeApi.save(formData.value).then(res => {
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
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的通知公告数据项？').then(() => {
    sysNoticeApi.remove(_ids).then(res => {
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
  sysNoticeApi.downloadTemplate()
}

/**
 * 导出
 */
const exportData = () => {
  sysNoticeApi.exportData()
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
  })
})
</script>
