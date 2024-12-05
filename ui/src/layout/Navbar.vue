<template>
  <div class="navbar">
    <hamburger
      id="hamburger-container"
      :is-active="appStore.sidebar.opened"
      class="hamburger-container"
      @toggle-click="toggleSideBar"
    />
    <breadcrumb
      v-if="!settingsStore.topNav"
      id="breadcrumb-container"
      class="breadcrumb-container"
    />
    <top-nav
      v-if="settingsStore.topNav"
      id="topmenu-container"
      class="topmenu-container"
    />

    <div class="right-menu flex align-center">
      <template>
        <!-- 消息 -->
        <el-tooltip
          content="消息"
          effect="dark"
          placement="bottom"
        >
          <div>
            <el-popover
              :persistent="false"
              :width="300"
              placement="bottom"
              transition="el-zoom-in-top"
              trigger="click"
            >
              <template #reference>
                <el-badge
                  :max="99"
                  :value="newNotice > 0 ? newNotice : ''"
                >
                  <svg-icon icon-class="message" />
                </el-badge>
              </template>
              <template #default>
                <notice />
              </template>
            </el-popover>
          </div>
        </el-tooltip>

        <el-tooltip
          content="全屏"
          effect="dark"
          placement="bottom"
        >
          <screen-full
            id="screenfull"
            class="right-menu-item hover-effect"
          />
        </el-tooltip>

        <el-tooltip
          content="布局大小"
          effect="dark"
          placement="bottom"
        >
          <size-select
            id="size-select"
            class="right-menu-item hover-effect"
          />
        </el-tooltip>
      </template>
      <div class="avatar-container">
        <el-dropdown
          class="right-menu-item hover-effect"
          trigger="click"
          @command="handleCommand"
        >
          <div class="avatar-wrapper">
            <img
              :src="userStore.avatar"
              alt="头像"
              class="user-avatar"
            >
            <el-icon>
              <caret-bottom />
            </el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <router-link to="/user/profile">
                <el-dropdown-item>个人中心</el-dropdown-item>
              </router-link>
              <el-dropdown-item
                v-if="settingsStore.showSettings"
                command="setLayout"
              >
                <span>布局设置</span>
              </el-dropdown-item>
              <el-dropdown-item
                command="logout"
                divided
              >
                <span>退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import useAppStore from '@/store/app'
import useUserStore from '@/store/user'
import useSettingsStore from '@/store/settings'
import useNoticeStore from '@/store/notice'
import notice from './Notice.vue'
import SvgIcon from '@/components/SvgIcon.vue'
import { CaretBottom } from '@element-plus/icons-vue'
import ScreenFull from '@/components/ScreenFull.vue'
import SizeSelect from '@/components/SizeSelect.vue'
import Hamburger from '@/components/Hamburger.vue'
import Breadcrumb from '@/components/Breadcrumb.vue'
import TopNav from '@/components/TopNav.vue'
import { ElMessageBox } from 'element-plus'

const appStore = useAppStore()
const userStore = useUserStore()
const settingsStore = useSettingsStore()
const noticeStore = storeToRefs(useNoticeStore())
const newNotice = ref(0)

defineExpose({})

const toggleSideBar = () => {
  appStore.toggleSideBar(false)
}

const logout = async () => {
  await ElMessageBox.confirm('确定注销并退出系统吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  await userStore.logout()
  location.href = '/'
}

const emits = defineEmits(['setLayout'])
const setLayout = () => {
  emits('setLayout')
}
// 定义Command方法对象 通过key直接调用方法
const commandMap = {
  setLayout,
  logout
}
const handleCommand = (command) => {
  // 判断是否存在该方法
  if (commandMap[command]) {
    commandMap[command]()
  }
}
//用深度监听 消息
watch(
  () => noticeStore.state.value.notices,
  (newVal) => {
    newNotice.value = newVal.filter((item) => !item.read).length
  },
  { deep: true }
)
</script>

<style lang="scss" scoped>
:deep(.el-select .el-input__wrapper) {
  height: 30px;
}

:deep(.el-badge__content.is-fixed) {
  top: 12px;
}

.flex {
  display: flex;
}

.align-center {
  align-items: center;
}

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  //background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .topmenu-container {
    position: absolute;
    left: 50px;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;
    display: flex;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 40px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
          margin-top: 10px;
        }

        i {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
