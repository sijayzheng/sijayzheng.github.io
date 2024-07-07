<template>
  <el-tree-select ref="menuTreeRef"
                  v-model="menus"
                  :data="menusOptions"
                  check-strictly
                  clearable
                  default-expand-all
                  multiple
                  node-key="id"
                  placeholder="请选择菜单"
                  show-checkbox
                  @check="treeCheckChange"/>
</template>

<script name="TreeSelect" setup>
const menusOptions = [
  {
    "id": 1,
    "value": 1,
    "parentId": 0,
    "label": "系统管理",
    "sort": 1,
    "children": [
      {
        "id": 101,
        "value": 101,
        "parentId": 1,
        "label": "系统配置",
        "sort": 1,
        "children": []
      },
      {
        "id": 102,
        "value": 102,
        "parentId": 1,
        "label": "菜单管理",
        "sort": 2,
        "children": []
      },
      {
        "id": 103,
        "value": 103,
        "parentId": 1,
        "label": "模块管理",
        "sort": 3,
        "children": []
      },
      {
        "id": 104,
        "value": 104,
        "parentId": 1,
        "label": "部门管理",
        "sort": 4,
        "children": []
      },
      {
        "id": 105,
        "value": 105,
        "parentId": 1,
        "label": "角色管理",
        "sort": 5,
        "children": []
      },
      {
        "id": 106,
        "value": 106,
        "parentId": 1,
        "label": "岗位管理",
        "sort": 6,
        "children": []
      },
      {
        "id": 107,
        "value": 107,
        "parentId": 1,
        "label": "用户管理",
        "sort": 7,
        "children": []
      },
      {
        "id": 108,
        "value": 108,
        "parentId": 1,
        "label": "通知公告",
        "sort": 8,
        "children": []
      }
    ]
  },
  {
    "id": 2,
    "value": 2,
    "parentId": 0,
    "label": "数据管理",
    "sort": 2,
    "children": [
      {
        "id": 201,
        "value": 201,
        "parentId": 2,
        "label": "数据字典类型",
        "sort": 9,
        "children": []
      },
      {
        "id": 202,
        "value": 202,
        "parentId": 2,
        "label": "数据字典项",
        "sort": 10,
        "children": []
      }
    ]
  },
  {
    "id": 3,
    "value": 3,
    "parentId": 0,
    "label": "系统工具",
    "sort": 3,
    "children": [
      {
        "id": 301,
        "value": 301,
        "parentId": 3,
        "label": "代码生成",
        "sort": 11,
        "children": []
      }
    ]
  }
]
const menus = ref([])
const menuTreeRef = ref()

const treeCheckChange = (data) => {
  let set = new Set(menus.value)
  data.children.forEach(child => {
    let check = menuTreeRef.value.getCheckedNodes().map(item => item.id).includes(data.id);
    menuTreeRef.value.setChecked(child.id, check)
    if (check) {
      set.add(child.id)
    } else
      set.delete(child.id);
  })
  let check = menuTreeRef.value.getCheckedNodes().map(item => item.parentId).includes(data.parentId) && data.parentId !== 0
  menuTreeRef.value.setChecked(data.parentId, check)
  if (check) {
    set.add(data.parentId)
  } else
    set.delete(data.parentId);
  menus.value = [...set]
}
</script>

<style scoped>

</style>
