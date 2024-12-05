<template>
  <div>
    <el-dialog
      v-model="roleDialog.visible.value"
      :title="roleDialog.title.value"
      append-to-body
      width="80%"
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
                label="角色名称"
                prop="roleName"
              >
                <el-input
                  v-model="queryParams.roleName"
                  clearable
                  placeholder="请输入角色名称"
                  @keyup.enter="handleQuery"
                />
              </el-form-item>
              <el-form-item
                label="权限字符"
                prop="roleKey"
              >
                <el-input
                  v-model="queryParams.roleKey"
                  clearable
                  placeholder="请输入权限字符"
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
                  @click="resetQuery"
                >
                  重置
                </el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div>
      </transition>

      <el-card shadow="hover">
        <template #header>
          <el-tag
            v-for="role in selectRoleList"
            :key="role.roleId"
            closable
            style="margin: 2px"
            @close="handleCloseTag(role)"
          >
            {{ role.roleName }}
          </el-tag>
        </template>

        <vxe-table
          ref="tableRef"
          :checkbox-config="{ reserve: true, checkRowKeys: defaultSelectRoleIds }"
          :data="roleList"
          :loading="loading"
          :row-config="{ keyField: 'roleId' }"
          border
          height="400px"
          highlight-current-row
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
            v-if="false"
            key="roleId"
            label="角色编号"
          />
          <vxe-column
            field="roleName"
            title="角色名称"
          />
          <vxe-column
            field="roleKey"
            title="权限字符"
          />
          <vxe-column
            field="roleSort"
            title="显示顺序"
            width="100"
          />
          <vxe-column
            align="center"
            title="状态"
            width="100"
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
            field="createTime"
            title="创建时间"
          >
            <template #default="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </vxe-column>
        </vxe-table>

        <pagination
          v-if="total > 0"
          v-model:limit="queryParams.pageSize"
          v-model:page="queryParams.pageNum"
          v-model:total="total"
          @pagination="pageList"
        />
      </el-card>
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
import useDialog from '@/hooks/useDialog.ts'
import api from '@/api/system/role'

const prop = withDefaults(defineProps(), {
  multiple: true,
  modelValue: undefined,
  data: undefined
})
const emit = defineEmits(['update:modelValue', 'confirmCallBack'])

const router = useRouter()
const { proxy } = getCurrentInstance()
const { sys_normal_disable } = toRefs(proxy?.useDict('sys_normal_disable'))

const roleList = ref()
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const dateRange = ref(['', ''])
const selectRoleList = ref([])

const roleDialog = useDialog({
  title: '角色选择'
})

const queryFormRef = ref()
const tableRef = ref()

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  roleName: '',
  roleKey: '',
  status: ''
})

const defaultSelectRoleIds = computed(() => computedIds(prop.data))

const confirm = () => {
  emit('update:modelValue', selectRoleList.value)
  emit('confirmCallBack', selectRoleList.value)
  roleDialog.closeDialog()
}

const computedIds = (data) => {
  if (data instanceof Array) {
    return [...data]
  } else if (typeof data === 'string') {
    return data.split(',')
  } else if (typeof data === 'number') {
    return [data]
  } else {
    console.warn('<RoleSelect> The data type of data should be array or string or number, but I received other')
    return []
  }
}

/**
 * 查询角色列表
 */
const getList = () => {
  loading.value = true
  api.listRole(proxy?.addDateRange(queryParams.value, dateRange.value)).then((res) => {
    roleList.value = res.rows
    total.value = res.total
    loading.value = false
  })
}
const pageList = async () => {
  await getList()
  const roles = roleList.value.filter((item) => {
    return selectRoleList.value.some((role) => role.roleId === item.roleId)
  })
  await tableRef.value.setCheckboxRow(roles, true)
}
/**
 * 搜索按钮操作
 */
const handleQuery = () => {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置 */
const resetQuery = () => {
  dateRange.value = ['', '']
  queryFormRef.value?.resetFields()
  handleQuery()
}

const handleCheckboxChange = (checked) => {
  if (!prop.multiple && checked.checked) {
    tableRef.value.setCheckboxRow(selectRoleList.value, false)
    selectRoleList.value = []
  }
  const row = checked.row
  if (checked.checked) {
    selectRoleList.value.push(row)
  } else {
    selectRoleList.value = selectRoleList.value.filter((item) => {
      return item.roleId !== row.roleId
    })
  }
}
const handleCheckboxAll = (checked) => {
  const rows = roleList.value
  if (checked.checked) {
    rows.forEach((row) => {
      if (!selectRoleList.value.some((item) => item.roleId === row.roleId)) {
        selectRoleList.value.push(row)
      }
    })
  } else {
    selectRoleList.value = selectRoleList.value.filter((item) => {
      return !rows.some((row) => row.roleId === item.roleId)
    })
  }
}

const handleCloseTag = (user) => {
  const roleId = user.roleId
  // 使用split删除用户
  const index = selectRoleList.value.findIndex((item) => item.roleId === roleId)
  const rows = selectRoleList.value[index]
  tableRef.value?.setCheckboxRow(rows, false)
  selectRoleList.value.splice(index, 1)
}
/**
 * 初始化选中数据
 */
const initSelectRole = async () => {
  if (defaultSelectRoleIds.value.length > 0) {
    const { data } = await api.optionSelect(defaultSelectRoleIds.value)
    selectRoleList.value = data
    const users = roleList.value.filter((item) => {
      return defaultSelectRoleIds.value.includes(String(item.roleId))
    })
    await nextTick(() => {
      tableRef.value.setCheckboxRow(users, true)
    })
  }
}
const close = () => {
  roleDialog.closeDialog()
}
watch(
  () => roleDialog.visible.value,
  (newValue) => {
    if (newValue) {
      initSelectRole()
    } else {
      tableRef.value.clearCheckboxReserve()
      tableRef.value.clearCheckboxRow()
      resetQuery()
      selectRoleList.value = []
    }
  }
)
onMounted(() => {
  getList() // 初始化列表数据
})

defineExpose({
  open: roleDialog.openDialog,
  close: roleDialog.closeDialog
})
</script>
