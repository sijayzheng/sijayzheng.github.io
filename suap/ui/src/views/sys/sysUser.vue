<template>
  <div class="p-2">
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="用户名：" prop="username">
            <el-input v-model="queryParams.username" clearable placeholder="请输入用户名" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="手机号：" prop="phone">
            <el-input v-model="queryParams.phone" clearable placeholder="请输入手机号" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="邮箱：" prop="email">
            <el-input v-model="queryParams.email" clearable placeholder="请输入邮箱" @keyup.enter="query"/>
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
            <!--            v-hasPermi="['system:config:add']"-->
            <el-button icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <!--            v-hasPermi="['system:config:remove']"-->
            <el-button :disabled="multiple" icon="Delete" plain type="danger" @click="remove()">
              删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <!--            v-hasPermi="['system:config:remove']"-->
            <el-button icon="Upload" plain type="primary" @click="dialog.visible=true">导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <!--            v-hasPermi="['system:config:remove']"-->
            <el-button icon="Download" plain type="primary" @click="exportData()">导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button icon="Download" plain type="primary" @click="downloadTemplate()">下载模板</el-button>
          </el-col>
          <table-tool-bar v-model:showSearch="showSearch" @queryTable="pageList"></table-tool-bar>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id" width="100"/>
        <el-table-column :show-overflow-tooltip="true" label="用户名" prop="username"/>
        <el-table-column :show-overflow-tooltip="true" label="手机号" prop="phone"/>
        <el-table-column :show-overflow-tooltip="true" label="邮箱" prop="email"/>
        <el-table-column :show-overflow-tooltip="true" label="最后登录时间" prop="lastLoginTime"/>
        <el-table-column :show-overflow-tooltip="true" label="是否启用" prop="enable"/>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort"/>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="150">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <!--              v-hasPermi="['system:config:edit']"-->
              <el-button icon="Edit" link type="primary" @click="update(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <!--              v-hasPermi="['system:config:remove']"-->
              <el-button icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <paginationBar v-show="total > 0" v-model:limit="queryParams.size" v-model:page="queryParams.current" :total="total" @pagination="pageList"/>
    </el-card>
    <el-drawer v-model="drawer.visible" size="60%">
      <template #header>
        <h4>{{ drawer.title }}</h4>
      </template>
      <template #default>
        <el-scrollbar>
          <el-form
              ref="formRef"
              :model="form"
              :rules="rules"
              label-position="left"
              label-width="120px"
              status-icon
          >
            <el-form-item label="用户名：" prop="username">
              <el-input v-model="form.username" placeholder="请输入用户名" clearable/>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号" clearable/>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" clearable/>
            </el-form-item>
            <el-form-item label="是否启用：" prop="enable">
              <el-switch v-model="queryParams.enable" :active-text="enableOptions[0].label" :active-value="enableOptions[0].value"
                         :inactive-text="enableOptions[1].label"
                         :inactive-value="enableOptions[1].value" inline-prompt/>
            </el-form-item>
            <el-form-item label="排序：" prop="sort">
              <el-input-number v-model="form.sort" placeholder="请输入排序" clearable/>
            </el-form-item>
          </el-form>
        </el-scrollbar>
      </template>
      <template #footer>
        <div style="flex: auto">
          <el-button @click="cancel">取消</el-button>
          <el-button @click="reset">重置</el-button>
          <el-button type="primary" @click="submitForm(formRef)">提交</el-button>
        </div>
      </template>
    </el-drawer>
    <el-dialog v-model="dialog.visible" :title="dialog.title" align-center>
      <el-button icon="Download" plain type="primary" @click="downloadTemplate()">下载模板</el-button>
      <el-upload :action="`${constant.baseApi}/sysUser/import`" accept=".xls,.xlsx" :on-success="uploadSuccess" drag>
        <el-icon class="el-icon--upload">
          <upload-filled/>
        </el-icon>
        <div class="el-upload__text">
          <em>拖入或点击以进行上传</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            xls/xlsx文件
          </div>
        </template>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script name="SysUser" setup>
const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const queryFormRef = ref()
const formRef = ref()
const drawer = reactive({
  visible: false,
  title: ''
})
const dialog = reactive({
  visible: false,
  title: '上传登录用户'
})
const initFormData = {
  id: undefined,
  username: undefined,
  phone: undefined,
  email: undefined,
  enable: undefined,
  sort: undefined,
}
const queryParams = ref({
  current: 1,
  size: 10,
  username: undefined,
  phone: undefined,
  email: undefined,
})
const form = ref({...initFormData})
const rules = ref({
  username: [relus.required('用户名'), relus.maxLength('用户名', 50),],
  phone: [relus.maxLength('手机号', 11),],
  email: [relus.maxLength('邮箱', 100),],
  enable: [],
})
const enableOptions = ref([])


/** 查询登录用户列表 */
const pageList = async () => {
  loading.value = true;
  sysUserApi.page(queryParams.value).then(res => {
    dataList.value = res.rows;
    total.value = res.total;
    loading.value = false;
  })
}
/** 取消按钮 */
const cancel = () => {
  reset()
  drawer.visible = false;
}
/** 表单重置 */
const reset = () => {
  formRef.value?.resetFields()
  form.value = {...initFormData};
}
/** 搜索 */
const query = () => {
  queryParams.value.current = 1;
  pageList()
}
/** 重置 */
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  query()
}
/** 多选框选中数据 */
const selectionChange = (selection) => {
  ids.value = selection.map(item => item.id)
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
/** 新增 */
const add = () => {
  reset()
  drawer.visible = true;
  drawer.title = "添加登录用户";
}
/** 修改 */
const update = async (row) => {
  reset()
  const id = row?.id;
  sysUserApi.getById(id).then(res => {
    Object.assign(form.value, res.data)
    drawer.visible = true;
    drawer.title = "修改登录用户";
  })
}
/** 提交按钮 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      if (form.value.id) {
        sysUserApi.update(form.value).then(res => {
          showMsg(res)
        })
      } else {
        sysUserApi.add(form.value).then(res => {
          showMsg(res)
        })
      }
    }
  })
}
const showMsg = (res) => {
  if (res.data) {
    ElMessage.success(res.msg)
  } else {
    ElMessage.error(res.msg)
  }
  drawer.visible = false;
  pageList()
}
/** 删除 */
const remove = (row) => {
  const _ids = row?.id || ids.value;
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的登录用户数据项？').then(() => {
    sysUserApi.remove({
      id: row?.id,
      ids: ids.value
    }).then(res => {
      if (res.data) {
        ElMessage.success(res.msg)
      } else {
        ElMessage.error(res.msg)
      }
      pageList()
    })
  }).catch(() => {
  })
}
/** 下载模板 */
const downloadTemplate = () => {
  sysUserApi.downloadTemplate()
}
/** 导出 */
const exportData = () => {
  sysUserApi.exportData()
}
/** 导入结果提示 */
const uploadSuccess = (res) => {
  dialog.visible = false
  if (res.code !== '0000') {
    ElMessage.error(res.msg)
  } else {
    ElMessage.success(res.msg)
  }
  pageList()
}
onMounted(() => {
  pageList()
  commonApi.listEnumDataOptions('YesOrNo').then(res => enableOptions.value = res.data)
})
</script>
