<template>
  <div class="p-2">
    <div v-show="showSearch">
      <el-card shadow="hover">
        <el-form ref="queryFormRef" :inline="true" :model="queryParams" class="inline-form">
          <el-form-item label="表名：" prop="tableName">
            <el-input v-model="queryParams.tableName" clearable placeholder="请输入表名" @keyup.enter="query"/>
          </el-form-item>
          <el-form-item label="表备注：" prop="comment">
            <el-input v-model="queryParams.comment" clearable placeholder="请输入表备注" @keyup.enter="query"/>
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
            <el-button :loading="addLoading" icon="Plus" plain type="primary" @click="add">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <!--            v-hasPermi="['system:config:remove']"-->
            <el-button :disabled="multiple" icon="Delete" plain type="danger" @click="remove()">
              删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button icon="Download" type="success" @click="generateCode">生成代码</el-button>
          </el-col>
          <table-tool-bar v-model:showSearch="showSearch" @queryTable="pageList"></table-tool-bar>
        </el-row>
      </template>
      <el-table v-loading="loading" :data="dataList" max-height="522px" @selection-change="selectionChange">
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="编号" prop="id" width="60"/>
        <el-table-column :show-overflow-tooltip="true" label="表名" prop="tableName" width="150"/>
        <el-table-column :show-overflow-tooltip="true" label="表备注" prop="comment" width="150"/>
        <el-table-column :show-overflow-tooltip="true" label="实体类名称" prop="className" width="150"/>
        <el-table-column :show-overflow-tooltip="true" label="模板类型" prop="template" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="包路径" prop="packageName" width="150"/>
        <el-table-column :show-overflow-tooltip="true" label="模块名" prop="module" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="作者" prop="author" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="生成方式" prop="genType" width="80"/>
        <el-table-column :show-overflow-tooltip="true" label="父类" prop="superClass" width="160"/>
        <el-table-column :show-overflow-tooltip="true" label="所属菜单" prop="menuId"/>
        <el-table-column :show-overflow-tooltip="true" label="是否已生成" prop="created" width="100"/>
        <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="200">
          <template #default="scope">
            <el-tooltip content="列信息" placement="top">
              <el-button icon="More" link type="primary" @click="columnDetailRef.open(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="修改" placement="top">
              <!--              v-hasPermi="['system:config:edit']"-->
              <el-button icon="Edit" link type="primary" @click="generateRef.open(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <!--              v-hasPermi="['system:config:remove']"-->
              <el-button icon="Delete" link type="primary" @click="remove(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip content="生成代码" placement="top">
              <!--              v-hasPermi="['system:config:remove']"-->
              <el-button icon="Download" link type="primary" @click="generateCode(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <paginationBar v-show="total > 0" v-model:limit="queryParams.size" v-model:page="queryParams.current" :total="total" @pagination="pageList"/>
    </el-card>
    <Generate ref="generateRef" @successful="pageList"></Generate>
    <column-detail ref="columnDetailRef"/>
  </div>
</template>

<script name="GenIndex" setup>
const dataList = ref([]);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const queryFormRef = ref();
const dialog = reactive({
  visible: false,
  title: '上传表信息'
});
const queryParams = reactive({
  current: 1,
  size: 10,
  tableName: undefined,
  comment: undefined,
})
const generateRef = ref()
const addLoading = ref(false)
const columnDetailRef = ref()

const pageList = async () => {
  addLoading.value = false
  loading.value = true;
  genApi.page(queryParams).then(res => {
    dataList.value = res.rows;
    total.value = res.total;
    loading.value = false;
  })
}
const query = () => {
  queryParams.current = 1;
  pageList();
}
const resetQuery = () => {
  queryFormRef.value.resetFields();
  query();
}
const selectionChange = (selection) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const remove = (row) => {
  const _ids = row?.id || ids.value;
  ElMessageBox.confirm('是否确认删除编号为"' + _ids + '"的表信息数据项？').then(() => {
    genApi.remove({
      id: row?.id,
      ids: ids.value
    }).then(res => {
      if (res.data) {
        ElMessage.success(res.msg);
      } else {
        ElMessage.error(res.msg);
      }
      pageList();
    })
  }).catch(() => {
  })
}
const add = () => {
  addLoading.value = true
  generateRef.value.open()
}
const generateCode = (row) => {
  if (row?.id) {
    if (row.genType === '压缩包') {
      genApi.download(row.id)
    } else {
      genApi.generate(row.id).then(res => ElMessage.success('生成' + res.msg))
    }
  } else {
    genApi.generate().then(res => ElMessage.success('生成' + res.msg))
  }
}

onMounted(() => {
  pageList();
})
</script>
