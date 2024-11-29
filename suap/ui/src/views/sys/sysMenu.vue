<template>
  <div>
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="上级：" prop="parentId">
            <el-input-number v-model="queryParams.parentId" clearable placeholder="请输入上级" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="菜单名称：" prop="name">
            <el-input v-model="queryParams.name" clearable placeholder="请输入菜单名称" @keyup.enter="query"/>
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
            <el-button v-hasPermission="'sys:sysMenu:add'" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-hasPermission="'sys:sysMenu:remove'" :disabled="!ids.length" icon="Delete" plain type="danger" @click="remove">删除
            </el-button>
          </el-col>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" row-key="id" stripe @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id"/>
        <el-table-column :show-overflow-tooltip="true" label="菜单类型" prop="type"/>
        <el-table-column :show-overflow-tooltip="true" label="菜单名称" prop="name"/>
        <el-table-column :show-overflow-tooltip="true" label="图标" prop="icon"/>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column :show-overflow-tooltip="true" label="路由地址" prop="path"/>
        <el-table-column :show-overflow-tooltip="true" label="组件路径" prop="component"/>
        <el-table-column :show-overflow-tooltip="true" label="权限标识" prop="perms"/>
        <el-table-column :show-overflow-tooltip="true" label="是否为外链" prop="link">
          <template #default="scope">
            {{ scope.row.link ? '是' : '否' }}
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="显示状态" prop="visible">
          <template #default="scope">
            {{ scope.row.visible ? '是' : '否' }}
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="是否启用" prop="enabled">
          <template #default="scope">
            {{ scope.row.enabled ? '是' : '否' }}
          </template>
        </el-table-column>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button v-hasPermission="'sys:sysMenu:edit'" icon="Edit" link type="primary" @click="edit(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button v-hasPermission="'sys:sysMenu:remove'" icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-scrollbar>
        <el-form ref="formRef" :model="formData" :rules="rules" label-position="left" label-width="120px" status-icon>
          <el-form-item label="上级：" prop="parentId">
            <el-input-number v-model="formData.parentId" clearable placeholder="请输入上级"/>
          </el-form-item>
          <el-form-item label="菜单类型：" prop="type">
            <el-input v-model="formData.type" clearable placeholder="请输入菜单类型"/>
          </el-form-item>
          <el-form-item label="菜单名称：" prop="name">
            <el-input v-model="formData.name" clearable placeholder="请输入菜单名称"/>
          </el-form-item>
          <el-form-item label="图标：" prop="icon">
            <el-input v-model="formData.icon" clearable placeholder="请输入图标"/>
          </el-form-item>
          <el-form-item label="排序：" prop="sort">
            <el-input-number v-model="formData.sort" clearable placeholder="请输入排序"/>
          </el-form-item>
          <el-form-item label="路由地址：" prop="path">
            <el-input v-model="formData.path" clearable placeholder="请输入路由地址"/>
          </el-form-item>
          <el-form-item label="组件路径：" prop="component">
            <el-input v-model="formData.component" clearable placeholder="请输入组件路径"/>
          </el-form-item>
          <el-form-item label="权限标识：" prop="perms">
            <el-input v-model="formData.perms" clearable placeholder="请输入权限标识"/>
          </el-form-item>
          <el-form-item label="是否为外链：" prop="link">
            <el-switch v-model="formData.link" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
          </el-form-item>
          <el-form-item label="显示状态：" prop="visible">
            <el-switch v-model="formData.visible" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
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
  </div>
</template>

<script name="SysMenu" setup>

const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const queryFormRef = ref()
const formRef = ref()
const dialog = reactive({
  visible: false,
  title: ''
})
const initFormData = {
  id: undefined,
  parentId: 0,
  type: undefined,
  name: undefined,
  icon: undefined,
  sort: 0,
  path: undefined,
  component: undefined,
  perms: undefined,
  link: true,
  visible: true,
  enabled: true,
}
const queryParams = ref({
  parentId: undefined,
  name: undefined,
  enabled: undefined,
})
const formData = ref({...initFormData})
const rules = ref({
  parentId: [relus.required('上级'),],
  type: [relus.required('菜单类型'), relus.maxLength('菜单类型', 10),],
  name: [relus.required('菜单名称'), relus.maxLength('菜单名称', 50),],
  icon: [relus.maxLength('图标', 100),],
  path: [relus.maxLength('路由地址', 200),],
  component: [relus.maxLength('组件路径', 255),],
  perms: [relus.maxLength('权限标识', 100),],
})

/**
 * 查询菜单列表
 */
const pageList = async () => {
  loading.value = true
  sysMenuApi.listTree(queryParams.value).then(res => {
    dataList.value = res.data
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
  dialog.title = '添加菜单'
}

/**
 * 修改
 */
const edit = async (row) => {
  reset()
  const id = row?.id
  sysMenuApi.getById(id).then(res => {
    Object.assign(formData.value, res.data)
    dialog.visible = true
    dialog.title = '修改菜单'
  })
}

/**
 * 提交按钮
 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      sysMenuApi.save(formData.value).then(res => {
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
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的菜单数据项？').then(() => {
    sysMenuApi.remove(_ids).then(res => {
      ElMessage.success(res.message)
      pageList()
    })
  }).catch((err) => {
    console.log(err)
  })
}

onMounted(() => {
  nextTick(() => {
    pageList()
  })
})
</script>
