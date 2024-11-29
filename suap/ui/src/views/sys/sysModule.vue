<template>
  <div>
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="模块名称：" prop="name">
            <el-input v-model="queryParams.name" clearable placeholder="请输入模块名称" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="是否启用：" prop="enabled">
            <el-switch v-model="queryParams.enabled" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
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
            <el-button v-hasPermission="'sys:sysModule:add'" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysModule:remove'" :disabled="!ids.length" icon="Delete" plain type="danger" @click="remove">删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysModule:import'" icon="Upload" plain type="primary" @click="uploadDialog.visible=true">导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysModule:export'" icon="Download" plain type="primary" @click="exportData">导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysModule:export'" icon="Download" plain type="primary" @click="downloadTemplate">下载模板</el-button>
          </el-col>
          <table-tool-bar v-model:showSearch="showSearch" @queryTable="pageList"/>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" stripe @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id"/>
        <el-table-column :show-overflow-tooltip="true" label="模块名称" prop="name"/>
        <el-table-column :show-overflow-tooltip="true" label="菜单" prop="menus"/>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column :show-overflow-tooltip="true" label="是否启用" prop="enabled">
          <template #default="scope">
            {{ scope.row.enabled ? '是' : '否' }}
          </template>
        </el-table-column>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button v-hasPermission="'sys:sysModule:edit'" icon="Edit" link type="primary" @click="edit(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button v-hasPermission="'sys:sysModule:remove'" icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <paginationBar v-model:limit="queryParams.size" v-model:page="queryParams.current" :total="total" @pagination="pageList"/>
    </el-card>
    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-scrollbar>
        <el-form ref="formRef" :model="formData" :rules="rules" label-position="left" label-width="120px" status-icon>
          <el-form-item label="模块名称：" prop="name">
            <el-input v-model="formData.name" clearable placeholder="请输入模块名称"/>
          </el-form-item>
          <el-form-item label="菜单：" prop="menus">
            <el-select v-model="formData.menus" clearable placeholder="请选择菜单">
              <el-option v-for="item in menusOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="排序：" prop="sort">
            <el-input-number v-model="formData.sort" clearable placeholder="请输入排序"/>
          </el-form-item>
          <el-form-item label="是否启用：" prop="enabled">
            <el-switch v-model="formData.enabled" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
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
      <el-upload :action="`${constant.baseApi}/sysModule/import`" :on-success="uploadSuccess" accept=".xlsx" drag>
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
  name: 'SysModule'
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
  title: '上传模块'
})
const initFormData = {
  id: undefined,
  name: undefined,
  menus: undefined,
  sort: 0,
  enabled: true,
}
const queryParams = ref({
  current: 1,
  size: 10,
  name: undefined,
  enabled: undefined,
})
const formData = ref({...initFormData})
const rules = ref({
  name: [relus.required('模块名称'), relus.maxLength('模块名称', 50),],
})
const menusOptions = ref([])

/**
 * 查询模块列表
 */
const pageList = async () => {
  loading.value = true
  sysModuleApi.page(queryParams.value).then(res => {
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
  dialog.title = '添加模块'
}

/**
 * 修改
 */
const edit = async (row) => {
  reset()
  const id = row?.id
  sysModuleApi.getById(id).then(res => {
    Object.assign(formData.value, res.data)
    dialog.visible = true
    dialog.title = '修改模块'
  })
}

/**
 * 提交按钮
 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      sysModuleApi.save(formData.value).then(res => {
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
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的模块数据项？').then(() => {
    sysModuleApi.remove(_ids).then(res => {
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
  sysModuleApi.downloadTemplate()
}

/**
 * 导出
 */
const exportData = () => {
  sysModuleApi.exportData()
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
    // commonApi.getDictOptions('List').then(res => menusOptions.value = res.data)
  })
})
</script>
