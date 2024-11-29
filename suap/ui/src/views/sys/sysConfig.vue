<template>
  <div>
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="配置名称：" prop="name">
            <el-input v-model="queryParams.name" clearable placeholder="请输入配置名称" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="配置编码：" prop="code">
            <el-input v-model="queryParams.code" clearable placeholder="请输入配置编码" @keyup.enter="query"/>
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
            <el-button v-hasPermission="'sys:sysConfig:add'" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button :disabled="!ids.length" v-hasPermission="'sys:sysConfig:remove'" icon="Delete" plain type="danger" @click="remove">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysConfig:import'" icon="Upload" plain type="primary" @click="uploadDialog.visible=true">导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysConfig:export'" icon="Download" plain type="primary" @click="exportData">导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysConfig:export'" icon="Download" plain type="primary" @click="downloadTemplate">下载模板</el-button>
          </el-col>
          <table-tool-bar v-model:showSearch="showSearch" @queryTable="pageList"/>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" stripe @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id"/>
        <el-table-column :show-overflow-tooltip="true" label="配置名称" prop="name"/>
        <el-table-column :show-overflow-tooltip="true" label="配置编码" prop="code"/>
        <el-table-column :show-overflow-tooltip="true" label="配置值" prop="value"/>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button v-hasPermission="'sys:sysConfig:edit'" icon="Edit" link type="primary" @click="edit(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button v-hasPermission="'sys:sysConfig:remove'" icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <paginationBar v-model:limit="queryParams.size" v-model:page="queryParams.current" :total="total" @pagination="pageList"/>
    </el-card>
    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-scrollbar>
        <el-form ref="formRef" :model="formData" :rules="rules" label-position="left" label-width="120px" status-icon>
          <el-form-item label="配置名称：" prop="name">
            <el-input  v-model="formData.name" placeholder="请输入配置名称" clearable/>
          </el-form-item>
          <el-form-item label="配置编码：" prop="code">
            <el-input  v-model="formData.code" placeholder="请输入配置编码" clearable/>
          </el-form-item>
          <el-form-item label="配置值：" prop="value">
            <el-input  v-model="formData.value" placeholder="请输入配置值" clearable/>
          </el-form-item>
          <el-form-item label="排序：" prop="sort">
            <el-input-number  v-model="formData.sort" placeholder="请输入排序" clearable/>
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
      <el-upload :action="`${constant.baseApi}/sysConfig/import`" accept=".xlsx" :on-success="uploadSuccess" drag>
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
  name: 'SysConfig'
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
  title: '上传系统配置'
})
const initFormData = {
  id: undefined,
  name: undefined,
  code: undefined,
  value: undefined,
  sort: 0,
}
const queryParams = ref({
  current: 1,
  size: 10,
  name: undefined,
  code: undefined,
})
const formData = ref({...initFormData})
const rules = ref({
  name: [relus.required('配置名称'), relus.maxLength('配置名称', 50),],
  code: [relus.required('配置编码'), relus.maxLength('配置编码', 50),],
  value: [relus.required('配置值'), relus.maxLength('配置值', 50),],
})

/**
 * 查询系统配置列表
 */
const pageList = async () => {
  loading.value = true
  sysConfigApi.page(queryParams.value).then(res => {
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
  dialog.title = "添加系统配置"
}

/**
 * 修改
 */
const edit = async (row) => {
  reset()
  const id = row?.id
  sysConfigApi.getById(id).then(res => {
    Object.assign(formData.value, res.data)
    dialog.visible = true
    dialog.title = "修改系统配置"
  })
}

/**
 * 提交按钮
 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      sysConfigApi.save(formData.value).then(res => {
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
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的系统配置数据项？').then(() => {
    sysConfigApi.remove(_ids).then(res => {
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
  sysConfigApi.downloadTemplate()
}

/**
 * 导出
 */
const exportData = () => {
  sysConfigApi.exportData()
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
