<template>
  <div class="p-2">
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="菜单名称：" prop="name">
            <el-input v-model="queryParams.name" clearable placeholder="请输入菜单名称" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="路径：" prop="path">
            <el-input v-model="queryParams.path" clearable placeholder="请输入路径" @keyup.enter="query"/>
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
      <el-table v-loading="loading" :data="dataList" default-expand-all row-key="id">
        <el-table-column :show-overflow-tooltip="true" label="菜单名称" prop="name" width="160"/>
        <el-table-column :show-overflow-tooltip="true" label="菜单类型" prop="type" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="路径" prop="path"/>
        <el-table-column :show-overflow-tooltip="true" label="组件路径" prop="component"/>
        <!--        <el-table-column :show-overflow-tooltip="true" label="路由参数" prop="queryParam"/>-->
        <el-table-column :show-overflow-tooltip="true" label="权限标识" prop="perms"/>
        <el-table-column :show-overflow-tooltip="true" align="center" label="图标" prop="icon" width="60">
          <template #default="scope">
            <!--            <el-icon><Connection /></el-icon>-->
            <svg-icon :icon-class="scope.row.icon"/>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="排序" prop="sort" width="60"/>
        <el-table-column :show-overflow-tooltip="true" label="是否为外链" prop="link" width="100"/>
        <el-table-column :show-overflow-tooltip="true" label="是否缓存" prop="cache" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="显示状态" prop="visible" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="是否启用" prop="enabled" width="80"/>
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
            <el-form-item label="上级菜单：" prop="parentId">
              <el-tree-select v-model="form.parentId" :data="parentIdOptions" accordion check-strictly clearable default-expand-all highlight-current
                              placeholder="请选择上级菜单"/>
            </el-form-item>
            <el-form-item label="菜单名称：" prop="name">
              <el-input v-model="form.name" clearable placeholder="请输入菜单名称"/>
            </el-form-item>
            <el-form-item label="菜单类型：" prop="type">
              <el-select v-model="form.type" clearable placeholder="请选择菜单类型">
                <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
            <el-form-item label="路径：" prop="path">
              <el-input v-model="form.path" clearable placeholder="请输入路径"/>
            </el-form-item>
            <el-form-item label="组件路径：" prop="component">
              <el-input v-model="form.component" clearable placeholder="请输入组件路径"/>
            </el-form-item>
            <el-form-item label="路由参数：" prop="queryParam">
              <el-input v-model="form.queryParam" clearable placeholder="请输入路由参数"/>
            </el-form-item>
            <el-form-item label="权限标识：" prop="perms">
              <el-input v-model="form.perms" clearable placeholder="请输入权限标识"/>
            </el-form-item>
            <el-form-item label="图标：" prop="icon">
              <el-input v-model="form.icon" clearable placeholder="请输入图标"/>
            </el-form-item>
            <el-form-item label="排序：" prop="sort">
              <el-input-number v-model="form.sort" clearable placeholder="请输入排序"/>
            </el-form-item>
            <el-form-item label="是否为外链：" prop="link">
              <el-switch v-model="form.link" :active-text="linkOptions[0].label" :active-value="linkOptions[0].value"
                         :inactive-text="linkOptions[1].label"
                         :inactive-value="linkOptions[1].value" inline-prompt/>
            </el-form-item>
            <el-form-item label="是否缓存：" prop="cache">
              <el-switch v-model="form.cache" :active-text="cacheOptions[0].label" :active-value="cacheOptions[0].value"
                         :inactive-text="cacheOptions[1].label"
                         :inactive-value="cacheOptions[1].value" inline-prompt/>
            </el-form-item>
            <el-form-item label="显示状态：" prop="visible">
              <el-switch v-model="form.visible" :active-text="visibleOptions[0].label" :active-value="visibleOptions[0].value"
                         :inactive-text="visibleOptions[1].label"
                         :inactive-value="visibleOptions[1].value" inline-prompt/>
            </el-form-item>
            <el-form-item label="是否启用：" prop="enabled">
              <el-switch v-model="form.enabled" :active-text="enabledOptions[0].label" :active-value="enabledOptions[0].value"
                         :inactive-text="enabledOptions[1].label"
                         :inactive-value="enabledOptions[1].value" inline-prompt/>
            </el-form-item>
          </el-form>
        </el-scrollbar>
      </template>
      <template #footer>
        <div style="flex: auto">
          <el-button @click="cancel">取消</el-button>
          <el-button @click="reset">重置</el-button>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </div>
      </template>
    </el-drawer>
    <el-dialog v-model="dialog.visible" :title="dialog.title" align-center>
      <el-button icon="Download" plain type="primary" @click="downloadTemplate()">下载模板</el-button>
      <el-upload :action="`${constant.baseApi}/sysMenu/import`" :on-success="uploadSuccess" accept=".xls,.xlsx" drag>
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

<script name="SysMenu" setup>
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
  title: '上传菜单信息'
})
const initFormData = {
  id: undefined,
  parentId: undefined,
  name: undefined,
  type: undefined,
  path: undefined,
  component: undefined,
  queryParam: undefined,
  perms: undefined,
  icon: undefined,
  sort: undefined,
  link: '是',
  cache: '是',
  visible: '是',
  enabled: '是',
}
const queryParams = ref({
  current: 1,
  size: 10,
  name: undefined,
  path: undefined,
})
const form = ref({...initFormData})
const rules = ref({
  parentId: [relus.required('上级'),],
  name: [relus.required('菜单名称'), relus.maxLength('菜单名称', 50),],
  type: [],
  path: [relus.maxLength('路径', 200),],
  component: [relus.maxLength('组件路径', 255),],
  queryParam: [relus.maxLength('路由参数', 255),],
  perms: [relus.maxLength('权限标识', 100),],
  icon: [relus.maxLength('图标', 100),],
  link: [],
  cache: [],
  visible: [],
  enabled: [],
})
const parentIdOptions = ref([])
const typeOptions = ref([])
const linkOptions = ref([])
const cacheOptions = ref([])
const visibleOptions = ref([])
const enabledOptions = ref([])


/** 查询菜单信息列表 */
const pageList = async () => {
  loading.value = true;
  sysMenuApi.listTree().then(res => {
    dataList.value = res.data;
    // total.value = res.total;
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

/** 新增 */
const add = () => {
  reset()
  drawer.visible = true;
  drawer.title = "添加菜单信息";
}
/** 修改 */
const update = async (row) => {
  reset()
  const id = row?.id;
  sysMenuApi.getById(id).then(res => {
    Object.assign(form.value, res.data)
    drawer.visible = true;
    drawer.title = "修改菜单信息";
  })
}
/** 提交按钮 */
const submitForm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      if (form.value.id) {
        sysMenuApi.update(form.value).then(res => {
          showMsg(res)
        })
      } else {
        sysMenuApi.add(form.value).then(res => {
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
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的菜单信息数据项？').then(() => {
    sysMenuApi.remove({
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
  sysMenuApi.downloadTemplate()
}
/** 导出 */
const exportData = () => {
  sysMenuApi.exportData()
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
  nextTick(() => {
    pageList()
    commonApi.listTableDataOptions('sys_menu').then(res => parentIdOptions.value = res.data)
    commonApi.listEnumDataOptions('MenuType').then(res => typeOptions.value = res.data)
    commonApi.listEnumDataOptions('YesOrNo').then(res => {
      linkOptions.value = res.data
      cacheOptions.value = res.data
      visibleOptions.value = res.data
      enabledOptions.value = res.data
    })
  })
})
</script>
