<template>
  <div>
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="上级：" prop="parentId">
            <el-input-number v-model="queryParams.parentId" clearable placeholder="请输入上级" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="部门名称：" prop="name">
            <el-input v-model="queryParams.name" clearable placeholder="请输入部门名称" @keyup.enter="query"/>
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
            <el-button v-hasPermission="'sys:sysDept:add'" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button :disabled="!ids.length" v-hasPermission="'sys:sysDept:remove'" icon="Delete" plain type="danger" @click="remove">删除</el-button>
          </el-col>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" stripe :tree-props="{ children: 'children'}" @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id"/>
        <el-table-column :show-overflow-tooltip="true" label="部门名称" prop="name"/>
        <el-table-column :show-overflow-tooltip="true" label="部门负责人" prop="leader"/>
        <el-table-column :show-overflow-tooltip="true" label="部门电话" prop="phone"/>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column :show-overflow-tooltip="true" label="是否启用" prop="enabled">
          <template #default="scope">
            {{scope.row.enabled?'是':'否'}}
          </template>
        </el-table-column>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button v-hasPermission="'sys:sysDept:edit'" icon="Edit" link type="primary" @click="edit(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button v-hasPermission="'sys:sysDept:remove'" icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-scrollbar>
        <el-form ref="formRef" :model="formData" :rules="rules" label-position="left" label-width="120px" status-icon>
          <el-form-item label="上级：" prop="parentId">
            <el-input-number  v-model="formData.parentId" placeholder="请输入上级" clearable/>
          </el-form-item>
          <el-form-item label="部门名称：" prop="name">
            <el-input  v-model="formData.name" placeholder="请输入部门名称" clearable/>
          </el-form-item>
          <el-form-item label="部门负责人：" prop="leader">
            <el-input-number  v-model="formData.leader" placeholder="请输入部门负责人" clearable/>
          </el-form-item>
          <el-form-item label="部门电话：" prop="phone">
            <el-input  v-model="formData.phone" placeholder="请输入部门电话" clearable/>
          </el-form-item>
          <el-form-item label="排序：" prop="sort">
            <el-input-number  v-model="formData.sort" placeholder="请输入排序" clearable/>
          </el-form-item>
          <el-form-item label="是否启用：" prop="enabled">
            <el-switch  v-model="formData.enabled" :active-value="true" :inactive-value="false" active-text="是" inactive-text="否" inline-prompt/>
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

<script name="SysDept" setup>

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
  name: undefined,
  leader: 0,
  phone: undefined,
  sort: 0,
  enabled: true,
}
const queryParams = ref({
  parentId: undefined,
  name: undefined,
  enabled: undefined,
})
const formData = ref({...initFormData})
const rules = ref({
  parentId: [relus.required('上级'), ],
  name: [relus.required('部门名称'), relus.maxLength('部门名称', 50),],
  phone: [ relus.maxLength('部门电话', 11),],
})

/**
 * 查询部门列表
 */
const pageList = async () => {
  loading.value = true;
  sysDeptApi.listTree(queryParams.value).then(res => {
    dataList.value = res.data;
    loading.value = false;
  })
}

/**
 * 取消按钮
 */
const cancel = () => {
  reset()
  dialog.visible = false;
}

/**
 * 表单重置
 */
const reset = () => {
  formRef.value?.resetFields()
  formData.value = {...initFormData};
}

/**
 * 搜索
 */
const query = () => {
  queryParams.value.current = 1;
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
  dialog.visible = true;
  dialog.title = "添加部门";
}

/**
 * 修改
 */
const edit = async (row) => {
  reset()
  const id = row?.id;
  sysDeptApi.getById(id).then(res => {
    Object.assign(formData.value, res.data)
    dialog.visible = true;
    dialog.title = "修改部门";
  })
}

/**
 * 提交按钮
 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      sysDeptApi.save(formData.value).then(res => {
        ElMessage.success(res.message)
        dialog.visible = false;
        pageList()
      })
    }
  })
}

/**
 * 删除
 */
const remove = (row) => {
  const _ids = row?.id ? [row?.id] : ids.value;
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的部门数据项？').then(() => {
    sysDeptApi.remove(_ids).then(res => {
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
