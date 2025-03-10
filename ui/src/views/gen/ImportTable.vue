<template>
  <!-- 导入表 -->
  <el-dialog
      v-model="visible"
      append-to-body
      title="导入表"
      top="5vh"
      width="1100px"
  >
    <el-form ref="queryFormRef" :inline="true" :model="queryParams">
      <el-form-item label="数据源" prop="dataName">
        <el-select
            v-model="queryParams.dataName"
            filterable
            placeholder="请选择/输入数据源名称"
        >
          <el-option
              v-for="item in dataNameList"
              :key="item"
              :label="item"
              :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="表名称" prop="tableName">
        <el-input
            v-model="queryParams.tableName"
            clearable
            placeholder="请输入表名称"
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表描述" prop="tableComment">
        <el-input
            v-model="queryParams.tableComment"
            clearable
            placeholder="请输入表描述"
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="Search" type="primary" @click="handleQuery"
        >搜索
        </el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-table
          ref="tableRef"
          :data="dbTableList"
          height="260px"
          @row-click="clickRow"
          @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            label="表名称"
            prop="tableName"
        ></el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            label="表描述"
            prop="tableComment"
        ></el-table-column>
        <el-table-column label="创建时间" prop="createTime"></el-table-column>
        <el-table-column label="更新时间" prop="updateTime"></el-table-column>
      </el-table>
      <pagination
          v-show="total > 0"
          v-model:limit="queryParams.pageSize"
          v-model:page="queryParams.pageNum"
          :total="total"
          @pagination="getList"
      />
    </el-row>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="handleImportTable">确 定</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {getDataNames, importTable, listDbTable} from '@/api/tool/gen'

const total = ref(0)
const visible = ref(false)
const tables = ref([])
const dbTableList = ref([])
const {proxy} = getCurrentInstance()

const tableRef = ref()
const queryFormRef = ref()

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  dataName: '',
  tableName: '',
  tableComment: '',
})
const dataNameList = ref([])

const emit = defineEmits(['ok'])

/** 查询参数列表 */
const show = (dataName) => {
  getDataNameList()
  if (dataName) {
    queryParams.dataName = dataName
  } else {
    queryParams.dataName = 'master'
  }
  getList()
  visible.value = true
}
/** 单击选择行 */
const clickRow = (row) => {
  // ele bug
  tableRef.value?.toggleRowSelection(row, false)
}
/** 多选框选中数据 */
const handleSelectionChange = (selection) => {
  tables.value = selection.map((item) => item.tableName)
}
/** 查询表数据 */
const getList = async () => {
  const res = await listDbTable(queryParams)
  dbTableList.value = res.rows
  total.value = res.total
}
/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.pageNum = 1
  getList()
}
/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  handleQuery()
}
/** 导入按钮操作 */
const handleImportTable = async () => {
  const tableNames = tables.value.join(',')
  if (tableNames === '') {
    modalUtil.msgError('请选择要导入的表')
    return
  }
  const res = await importTable({
    tables: tableNames,
    dataName: queryParams.dataName,
  })
  modalUtil.msgSuccess(res.msg)
  if (res.code === 200) {
    visible.value = false
    emit('ok')
  }
}
/** 查询多数据源名称 */
const getDataNameList = async () => {
  const res = await getDataNames()
  dataNameList.value = res.data
}

defineExpose({
  show,
})
</script>
