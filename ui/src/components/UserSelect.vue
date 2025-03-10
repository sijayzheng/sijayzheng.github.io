<template>
  <div>
    <el-dialog
        v-model="userDialog.visible.value"
        :title="userDialog.title.value"
        append-to-body
        width="80%"
    >
      <el-row :gutter="20">
        <!-- 部门树 -->
        <el-col
            :lg="4"
            :xs="24"
            style=""
        >
          <el-card shadow="hover">
            <el-input
                v-model="deptName"
                clearable
                placeholder="请输入部门名称"
                prefix-icon="Search"
            />
            <el-tree
                ref="deptTreeRef"
                :data="deptOptions"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                :props="{ label: 'label', children: 'children' }"
                class="mt-2"
                default-expand-all
                highlight-current
                node-key="id"
                @node-click="handleNodeClick"
            />
          </el-card>
        </el-col>
        <el-col
            :lg="20"
            :xs="24"
        >
          <transition
              :enter-active-class="animateUtil.searchAnimate.enter"
              :leave-active-class="animateUtil.searchAnimate.leave"
          >
            <div
                v-show="showSearch"
                class="mb-[10px]"
            >
              <el-card shadow="hover">
                <el-form
                    ref="queryFormRef"
                    :inline="true"
                    :model="queryParams"
                >
                  <el-form-item
                      label="用户名称"
                      prop="userName"
                  >
                    <el-input
                        v-model="queryParams.userName"
                        clearable
                        placeholder="请输入用户名称"
                        @keyup.enter="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item
                      label="手机号码"
                      prop="phonenumber"
                  >
                    <el-input
                        v-model="queryParams.phonenumber"
                        clearable
                        placeholder="请输入手机号码"
                        @keyup.enter="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-button
                        icon="Search"
                        type="primary"
                        @click="handleQuery"
                    >
                      搜索
                    </el-button>
                    <el-button
                        icon="Refresh"
                        @click="() => resetQuery()"
                    >
                      重置
                    </el-button>
                  </el-form-item>
                </el-form>
              </el-card>
            </div>
          </transition>

          <el-card shadow="hover">
            <template
                v-if="prop.multiple"
                #header
            >
              <el-tag
                  v-for="user in selectUserList"
                  :key="user.userId"
                  closable
                  style="margin: 2px"
                  @close="handleCloseTag(user)"
              >
                {{ user.userName }}
              </el-tag>
            </template>

            <vxe-table
                ref="tableRef"
                :checkbox-config="{ reserve: true, trigger: 'row', highlight: true, showHeader: prop.multiple }"
                :data="userList"
                :loading="loading"
                :row-config="{ keyField: 'userId', isHover: true }"
                border
                height="400px"
                show-overflow
                @checkbox-all="handleCheckboxAll"
                @checkbox-change="handleCheckboxChange"
            >
              <vxe-column
                  align="center"
                  type="checkbox"
                  width="50"
              />
              <vxe-column
                  key="userId"
                  align="center"
                  field="userId"
                  title="用户编号"
              />
              <vxe-column
                  key="userName"
                  align="center"
                  field="userName"
                  title="用户名称"
              />
              <vxe-column
                  key="nickName"
                  align="center"
                  field="nickName"
                  title="用户昵称"
              />
              <vxe-column
                  key="deptName"
                  align="center"
                  field="deptName"
                  title="部门"
              />
              <vxe-column
                  key="phonenumber"
                  align="center"
                  field="phonenumber"
                  title="手机号码"
                  width="120"
              />
              <vxe-column
                  key="status"
                  align="center"
                  title="状态"
              >
                <template #default="scope">
                  <dict-tag
                      :options="sys_normal_disable"
                      :value="scope.row.status"
                  />
                </template>
              </vxe-column>

              <vxe-column
                  align="center"
                  title="创建时间"
                  width="160"
              >
                <template #default="scope">
                  <span>{{ scope.row.createTime }}</span>
                </template>
              </vxe-column>
            </vxe-table>

            <pagination
                v-show="total > 0"
                v-model:limit="queryParams.pageSize"
                v-model:page="queryParams.pageNum"
                :total="total"
                @pagination="pageList"
            />
          </el-card>
        </el-col>
      </el-row>

      <template #footer>
        <el-button @click="close">
          取消
        </el-button>
        <el-button
            type="primary"
            @click="confirm"
        >
          确定
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import api from '@/api/system/user'
import useDialog from '@/hooks/useDialog.ts'

const prop = withDefaults(defineProps(), {
  multiple: true,
  modelValue: undefined,
  data: undefined
})
const emit = defineEmits(['update:modelValue', 'confirmCallBack'])

const {proxy} = getCurrentInstance()
const {sys_normal_disable} = toRefs(proxy?.useDict('sys_normal_disable'))

const userList = ref()
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const dateRange = ref(['', ''])
const deptName = ref('')
const deptOptions = ref([])
const selectUserList = ref([])

const deptTreeRef = ref()
const queryFormRef = ref()
const tableRef = ref()

const userDialog = useDialog({
  title: '用户选择'
})

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  userName: '',
  phonenumber: '',
  status: '',
  deptId: '',
  roleId: ''
})

const defaultSelectUserIds = computed(() => computedIds(prop.data))

/** 根据名称筛选部门树 */
watchEffect(
    () => {
      deptTreeRef.value?.filter(deptName.value)
    },
    {
      flush: 'post' // watchEffect会在DOM挂载或者更新之前就会触发，此属性控制在DOM元素更新后运行
    }
)

const confirm = () => {
  emit('update:modelValue', selectUserList.value)
  emit('confirmCallBack', selectUserList.value)
  userDialog.closeDialog()
}

const computedIds = (data) => {
  if (data instanceof Array) {
    return [...data]
  } else if (typeof data === 'string') {
    return data.split(',')
  } else if (typeof data === 'number') {
    return [data]
  } else {
    console.warn('<UserSelect> The data type of data should be array or string or number, but I received other')
    return []
  }
}

/** 通过条件过滤节点  */
const filterNode = (value, data) => {
  if (!value) {
    return true
  }
  return data.label.indexOf(value) !== -1
}

/** 查询部门下拉树结构 */
const getTreeSelect = async () => {
  const res = await api.deptTreeSelect()
  deptOptions.value = res.data
}

/** 查询用户列表 */
const getList = async () => {
  loading.value = true
  const res = await api.listUser(proxy?.addDateRange(queryParams.value, dateRange.value))
  loading.value = false
  userList.value = res.rows
  total.value = res.total
}

const pageList = async () => {
  await getList()
  const users = userList.value.filter((item) => {
    return selectUserList.value.some((user) => user.userId === item.userId)
  })
  await tableRef.value.setCheckboxRow(users, true)
}

/** 节点单击事件 */
const handleNodeClick = (data) => {
  queryParams.value.deptId = data.id
  handleQuery()
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1
  getList()
}
/** 重置按钮操作 */
const resetQuery = (refresh = true) => {
  dateRange.value = ['', '']
  queryFormRef.value?.resetFields()
  queryParams.value.pageNum = 1
  queryParams.value.deptId = undefined
  deptTreeRef.value?.setCurrentKey(undefined)
  refresh && handleQuery()
}

const handleCheckboxChange = (checked) => {
  if (!prop.multiple && checked.checked) {
    tableRef.value.setCheckboxRow(selectUserList.value, false)
    selectUserList.value = []
  }
  const row = checked.row
  if (checked.checked) {
    selectUserList.value.push(row)
  } else {
    selectUserList.value = selectUserList.value.filter((item) => {
      return item.userId !== row.userId
    })
  }
}
const handleCheckboxAll = (checked) => {
  const rows = userList.value
  if (checked.checked) {
    rows.forEach((row) => {
      if (!selectUserList.value.some((item) => item.userId === row.userId)) {
        selectUserList.value.push(row)
      }
    })
  } else {
    selectUserList.value = selectUserList.value.filter((item) => {
      return !rows.some((row) => row.userId === item.userId)
    })
  }
}

const handleCloseTag = (user) => {
  const userId = user.userId
  // 使用split删除用户
  const index = selectUserList.value.findIndex((item) => item.userId === userId)
  const rows = selectUserList.value[index]
  tableRef.value?.setCheckboxRow(rows, false)
  selectUserList.value.splice(index, 1)
}

const initSelectUser = async () => {
  if (defaultSelectUserIds.value.length > 0) {
    const {data} = await api.optionSelect(defaultSelectUserIds.value)
    selectUserList.value = data
    const users = userList.value.filter((item) => {
      return defaultSelectUserIds.value.includes(String(item.userId))
    })
    await nextTick(() => {
      tableRef.value.setCheckboxRow(users, true)
    })
  }
}
const close = () => {
  userDialog.closeDialog()
}

watch(
    () => userDialog.visible.value,
    async (newValue) => {
      if (newValue) {
        await getTreeSelect() // 初始化部门数据
        await getList() // 初始化列表数据
        await initSelectUser()
      } else {
        tableRef.value.clearCheckboxReserve()
        tableRef.value.clearCheckboxRow()
        resetQuery(false)
        selectUserList.value = []
      }
    }
)

defineExpose({
  open: userDialog.openDialog,
  close: userDialog.closeDialog
})
</script>

<style lang="scss" scoped></style>
