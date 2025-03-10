<template>
  <div class="p-2">
    <transition
        :enter-active-class="proxy?.animate.searchAnimate.enter"
        :leave-active-class="proxy?.animate.searchAnimate.leave"
    >
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :inline="true" :model="queryParams">
            <el-form-item label="数据源" prop="dataName">
              <el-select
                  v-model="queryParams.dataName"
                  clearable
                  filterable
                  placeholder="请选择/输入数据源名称"
              >
                <el-option key="" label="全部" value=""/>
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
            <el-form-item label="创建时间" style="width: 308px">
              <el-date-picker
                  v-model="dateRange"
                  end-placeholder="结束日期"
                  range-separator="-"
                  start-placeholder="开始日期"
                  type="daterange"
                  value-format="YYYY-MM-DD"
              ></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button icon="Search" type="primary" @click="handleQuery"
              >搜索
              </el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </transition>

    <el-card shadow="hover">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
                v-hasPermi="['tool:gen:code']"
                icon="Download"
                plain
                type="primary"
                @click="handleGenTable()"
            >生成
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                v-hasPermi="['tool:gen:import']"
                icon="Upload"
                plain
                type="info"
                @click="openImportTable"
            >导入
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                v-hasPermi="['tool:gen:edit']"
                :disabled="single"
                icon="Edit"
                plain
                type="success"
                @click="handleEditTable()"
            >修改
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                v-hasPermi="['tool:gen:remove']"
                :disabled="multiple"
                icon="Delete"
                plain
                type="danger"
                @click="handleDelete()"
            >
              删除
            </el-button>
          </el-col>
          <right-toolbar
              v-model:showSearch="showSearch"
              @query-table="getList"
          ></right-toolbar>
        </el-row>
      </template>

      <el-table
          v-loading="loading"
          :data="tableList"
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            align="center"
            type="selection"
            width="55"
        ></el-table-column>
        <el-table-column align="center" label="序号" type="index" width="50">
          <template #default="scope">
            <span>{{
                (queryParams.pageNum - 1) * queryParams.pageSize +
                scope.$index +
                1
              }}</span>
          </template>
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            align="center"
            label="数据源"
            prop="dataName"
        />
        <el-table-column
            :show-overflow-tooltip="true"
            align="center"
            label="表名称"
            prop="tableName"
        />
        <el-table-column
            :show-overflow-tooltip="true"
            align="center"
            label="表描述"
            prop="tableComment"
        />
        <el-table-column
            :show-overflow-tooltip="true"
            align="center"
            label="实体"
            prop="className"
        />
        <el-table-column
            align="center"
            label="创建时间"
            prop="createTime"
            width="160"
        />
        <el-table-column
            align="center"
            label="更新时间"
            prop="updateTime"
            width="160"
        />
        <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="330"
        >
          <template #default="scope">
            <el-tooltip content="预览" placement="top">
              <el-button
                  v-hasPermi="['tool:gen:preview']"
                  icon="View"
                  link
                  type="primary"
                  @click="handlePreview(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip content="编辑" placement="top">
              <el-button
                  v-hasPermi="['tool:gen:edit']"
                  icon="Edit"
                  link
                  type="primary"
                  @click="handleEditTable(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button
                  v-hasPermi="['tool:gen:remove']"
                  icon="Delete"
                  link
                  type="primary"
                  @click="handleDelete(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip content="同步" placement="top">
              <el-button
                  v-hasPermi="['tool:gen:edit']"
                  icon="Refresh"
                  link
                  type="primary"
                  @click="handleSynchDb(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip content="生成代码" placement="top">
              <el-button
                  v-hasPermi="['tool:gen:code']"
                  icon="Download"
                  link
                  type="primary"
                  @click="handleGenTable(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <pagination
          v-show="total > 0"
          v-model:limit="queryParams.pageSize"
          v-model:page="queryParams.pageNum"
          :total="total"
          @pagination="getList"
      />
    </el-card>

    <!-- 预览界面 -->
    <el-dialog
        v-model="dialog.visible"
        :title="dialog.title"
        append-to-body
        class="scrollbar"
        top="5vh"
        width="80%"
    >
      <el-tabs v-model="preview.activeName">
        <el-tab-pane
            v-for="(value, key) in preview.data"
            :key="value"
            :label="key.substring(key.lastIndexOf('/') + 1, key.indexOf('.vm'))"
            :name="key.substring(key.lastIndexOf('/') + 1, key.indexOf('.vm'))"
        >
          <el-link
              v-copyText="value"
              v-copyText:callback="copyTextSuccess"
              :underline="false"
              icon="DocumentCopy"
              style="float: right"
          >
            &nbsp;复制
          </el-link>
          <pre>{{ value }}</pre>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <import-table ref="importRef" @ok="handleQuery"/>
  </div>
</template>

<script setup>
import {delTable, genCode, getDataNames, listTable, previewTable, synchDb} from '@/api/tool/gen'
import router from '@/router'
import ImportTable from './importTable.vue'
import {downloadFile} from '@/util/request.js'

const route = useRoute()
const {proxy} = getCurrentInstance()

const tableList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const dateRange = ref(['', ''])
const uniqueId = ref('')
const dataNameList = ref([])

const queryFormRef = ref()
const importRef = ref()

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  tableName: '',
  tableComment: '',
  dataName: ''
})

const preview = ref({
  data: {},
  activeName: 'domain.java'
})
const dialog = reactive({
  visible: false,
  title: '代码预览'
})

onActivated(() => {
  const time = route.query.t
  if (time != null && time != uniqueId.value) {
    uniqueId.value = time
    queryParams.value.pageNum = Number(route.query.pageNum)
    dateRange.value = ['', '']
    queryFormRef.value?.resetFields()
    getList()
  }
})

/** 查询多数据源名称 */
const getDataNameList = async () => {
  const res = await getDataNames()
  dataNameList.value = res.data
}

/** 查询表集合 */
const getList = async () => {
  loading.value = true
  const res = await listTable(
      proxy?.addDateRange(queryParams.value, dateRange.value)
  )
  tableList.value = res.rows
  total.value = res.total
  loading.value = false
}
/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1
  getList()
}
/** 生成代码操作 */
const handleGenTable = async (row) => {
  const tbIds = row?.tableId || ids.value
  if (tbIds === '') {
    modalUtil.msgError('请选择要生成的数据')
    return
  }
  if (row?.genType === '1') {
    await genCode(row.tableId)
    modalUtil.msgSuccess('成功生成到自定义路径：' + row.genPath)
  } else {
    downloadFile('/tool/gen/batchGenCode?tableIdStr=' + tbIds, 'code.zip')
  }
}
/** 同步数据库操作 */
const handleSynchDb = async (row) => {
  const tableId = row.tableId
  await modalUtil.confirm('确认要强制同步"' + row.tableName + '"表结构吗？')
  await synchDb(tableId)
  modalUtil.msgSuccess('同步成功')
}
/** 打开导入表弹窗 */
const openImportTable = () => {
  importRef.value?.show(queryParams.value.dataName)
}
/** 重置按钮操作 */
const resetQuery = () => {
  dateRange.value = ['', '']
  queryFormRef.value?.resetFields()
  handleQuery()
}
/** 预览按钮 */
const handlePreview = async (row) => {
  const res = await previewTable(row.tableId)
  preview.value.data = res.data
  dialog.visible = true
  preview.value.activeName = 'domain.java'
}
/** 复制代码成功 */
const copyTextSuccess = () => {
  modalUtil.msgSuccess('复制成功')
}
// 多选框选中数据
const handleSelectionChange = (selection) => {
  ids.value = selection.map((item) => item.tableId)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}
/** 修改按钮操作 */
const handleEditTable = (row) => {
  const tableId = row?.tableId || ids.value[0]
  router.push({
    path: '/tool/gen-edit/index/' + tableId,
    query: {pageNum: queryParams.value.pageNum}
  })
}
/** 删除按钮操作 */
const handleDelete = async (row) => {
  const tableIds = row?.tableId || ids.value
  await modalUtil.confirm('是否确认删除表编号为"' + tableIds + '"的数据项？')
  await delTable(tableIds)
  await getList()
  modalUtil.msgSuccess('删除成功')
}

onMounted(() => {
  getList()
  getDataNameList()
})
</script>
