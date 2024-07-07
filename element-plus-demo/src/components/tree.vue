<template>
  <el-tree ref="menuTreeRef"
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

<script name="Tree" setup>
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
        "children": [
          {
            "id": 10101,
            "value": 10101,
            "parentId": 101,
            "label": "系统配置查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10102,
            "value": 10102,
            "parentId": 101,
            "label": "系统配置新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10103,
            "value": 10103,
            "parentId": 101,
            "label": "系统配置修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10104,
            "value": 10104,
            "parentId": 101,
            "label": "系统配置删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10105,
            "value": 10105,
            "parentId": 101,
            "label": "系统配置导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10106,
            "value": 10106,
            "parentId": 101,
            "label": "系统配置导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 102,
        "value": 102,
        "parentId": 1,
        "label": "菜单管理",
        "sort": 2,
        "children": [
          {
            "id": 10201,
            "value": 10201,
            "parentId": 102,
            "label": "菜单查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10202,
            "value": 10202,
            "parentId": 102,
            "label": "菜单新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10203,
            "value": 10203,
            "parentId": 102,
            "label": "菜单修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10204,
            "value": 10204,
            "parentId": 102,
            "label": "菜单删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10205,
            "value": 10205,
            "parentId": 102,
            "label": "菜单导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10206,
            "value": 10206,
            "parentId": 102,
            "label": "菜单导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 103,
        "value": 103,
        "parentId": 1,
        "label": "模块管理",
        "sort": 3,
        "children": [
          {
            "id": 10301,
            "value": 10301,
            "parentId": 103,
            "label": "模块查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10302,
            "value": 10302,
            "parentId": 103,
            "label": "模块新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10303,
            "value": 10303,
            "parentId": 103,
            "label": "模块修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10304,
            "value": 10304,
            "parentId": 103,
            "label": "模块删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10305,
            "value": 10305,
            "parentId": 103,
            "label": "模块导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10306,
            "value": 10306,
            "parentId": 103,
            "label": "模块导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 104,
        "value": 104,
        "parentId": 1,
        "label": "部门管理",
        "sort": 4,
        "children": [
          {
            "id": 10401,
            "value": 10401,
            "parentId": 104,
            "label": "部门查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10402,
            "value": 10402,
            "parentId": 104,
            "label": "部门新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10403,
            "value": 10403,
            "parentId": 104,
            "label": "部门修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10404,
            "value": 10404,
            "parentId": 104,
            "label": "部门删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10405,
            "value": 10405,
            "parentId": 104,
            "label": "部门导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10406,
            "value": 10406,
            "parentId": 104,
            "label": "部门导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 105,
        "value": 105,
        "parentId": 1,
        "label": "角色管理",
        "sort": 5,
        "children": [
          {
            "id": 10501,
            "value": 10501,
            "parentId": 105,
            "label": "角色查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10502,
            "value": 10502,
            "parentId": 105,
            "label": "角色新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10503,
            "value": 10503,
            "parentId": 105,
            "label": "角色修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10504,
            "value": 10504,
            "parentId": 105,
            "label": "角色删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10505,
            "value": 10505,
            "parentId": 105,
            "label": "角色导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10506,
            "value": 10506,
            "parentId": 105,
            "label": "角色导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 106,
        "value": 106,
        "parentId": 1,
        "label": "岗位管理",
        "sort": 6,
        "children": [
          {
            "id": 10601,
            "value": 10601,
            "parentId": 106,
            "label": "岗位查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10602,
            "value": 10602,
            "parentId": 106,
            "label": "岗位新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10603,
            "value": 10603,
            "parentId": 106,
            "label": "岗位修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10604,
            "value": 10604,
            "parentId": 106,
            "label": "岗位删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10605,
            "value": 10605,
            "parentId": 106,
            "label": "岗位导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10606,
            "value": 10606,
            "parentId": 106,
            "label": "岗位导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 107,
        "value": 107,
        "parentId": 1,
        "label": "用户管理",
        "sort": 7,
        "children": [
          {
            "id": 10701,
            "value": 10701,
            "parentId": 107,
            "label": "用户查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10702,
            "value": 10702,
            "parentId": 107,
            "label": "用户新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10703,
            "value": 10703,
            "parentId": 107,
            "label": "用户修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10704,
            "value": 10704,
            "parentId": 107,
            "label": "用户删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10705,
            "value": 10705,
            "parentId": 107,
            "label": "用户导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10706,
            "value": 10706,
            "parentId": 107,
            "label": "用户导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 108,
        "value": 108,
        "parentId": 1,
        "label": "通知公告",
        "sort": 8,
        "children": [
          {
            "id": 10801,
            "value": 10801,
            "parentId": 108,
            "label": "通知公告查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 10802,
            "value": 10802,
            "parentId": 108,
            "label": "通知公告新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 10803,
            "value": 10803,
            "parentId": 108,
            "label": "通知公告修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 10804,
            "value": 10804,
            "parentId": 108,
            "label": "通知公告删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 10805,
            "value": 10805,
            "parentId": 108,
            "label": "通知公告导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 10806,
            "value": 10806,
            "parentId": 108,
            "label": "通知公告导出",
            "sort": 5,
            "children": []
          }
        ]
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
        "children": [
          {
            "id": 20101,
            "value": 20101,
            "parentId": 201,
            "label": "数据字典类型查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 20102,
            "value": 20102,
            "parentId": 201,
            "label": "数据字典类型新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 20103,
            "value": 20103,
            "parentId": 201,
            "label": "数据字典类型修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 20104,
            "value": 20104,
            "parentId": 201,
            "label": "数据字典类型删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 20105,
            "value": 20105,
            "parentId": 201,
            "label": "数据字典类型导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 20106,
            "value": 20106,
            "parentId": 201,
            "label": "数据字典类型导出",
            "sort": 5,
            "children": []
          }
        ]
      },
      {
        "id": 202,
        "value": 202,
        "parentId": 2,
        "label": "数据字典项",
        "sort": 10,
        "children": [
          {
            "id": 20201,
            "value": 20201,
            "parentId": 202,
            "label": "数据字典项查询",
            "sort": 0,
            "children": []
          },
          {
            "id": 20202,
            "value": 20202,
            "parentId": 202,
            "label": "数据字典项新增",
            "sort": 1,
            "children": []
          },
          {
            "id": 20203,
            "value": 20203,
            "parentId": 202,
            "label": "数据字典项修改",
            "sort": 2,
            "children": []
          },
          {
            "id": 20204,
            "value": 20204,
            "parentId": 202,
            "label": "数据字典项删除",
            "sort": 3,
            "children": []
          },
          {
            "id": 20205,
            "value": 20205,
            "parentId": 202,
            "label": "数据字典项导入",
            "sort": 4,
            "children": []
          },
          {
            "id": 20206,
            "value": 20206,
            "parentId": 202,
            "label": "数据字典项导出",
            "sort": 5,
            "children": []
          }
        ]
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

function checkChild(data, set) {
  let check = menuTreeRef.value.getCheckedNodes().map(item => item.id).includes(data.id);
  if (data.children) {
    data.children.forEach(child => {
      menuTreeRef.value.setChecked(child.id, check)
      if (check) {
        set.add(child.id)
      } else {
        set.delete(child.id);
      }
      nextTick(() => {
        checkChild(child, set)
      })
    })
  }
}

function checkParent(data, set) {
  let check = menuTreeRef.value.getCheckedNodes().map(item => item.parentId).includes(data.parentId) && data.parentId !== 0
  menuTreeRef.value.setChecked(data.parentId, check)
  if (check) {
    set.add(data.parentId)
  } else {
    set.delete(data.parentId);
  }
}

const treeCheckChange = (data) => {
  let set = new Set(menus.value)
  checkChild(data, set)
  checkParent(data, set)
  menus.value = [...set]
}
</script>

<style scoped>

</style>
