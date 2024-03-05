<template>
  <el-container class="outer-layer">
    <el-aside width="200px">
      <div class="logo">
        <router-link to="/">
          <img :src="logo" alt="logo"/>
        </router-link>
        <router-link to="/">
          <h1>{{ title }}</h1>
        </router-link>
      </div>
      <div class="menu-container">
        <el-scrollbar wrap-class="scrollbar-wrapper">
          <el-menu
              :default-active="activeMenu"
              :unique-opened="true"
              mode="vertical"
          >
            <menuItem v-for="(route, index) in routes" :key="route.path + index" :base-path="route.path" :item="route"/>
          </el-menu>
        </el-scrollbar>
      </div>
    </el-aside>
    <el-container class="inner-layer">
      <el-header height="50px">
        <div class="breadcrumb">
          <el-breadcrumb class="app-breadcrumb" separator="/">
            <el-breadcrumb-item v-for="(item, index) in levelList" :key="item.path">
        <span v-if="item.redirect === 'noRedirect' || index == levelList.length - 1" class="no-redirect">{{
            item.meta?.title
          }}</span>
              <a v-else @click.prevent="handleLink(item)">{{ item.meta?.title }}</a>
            </el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="user-bar">
          <div class="username">{{ userInfo.realName }}</div>
          <el-dropdown class="right-menu-item hover-effect" trigger="click" @command="handleCommand">
            <el-avatar :size="40" :src="userAvatarImg"/>
            <template #dropdown>
              <el-dropdown-menu>
                <router-link to="/user/profile">
                  <el-dropdown-item>个人中心</el-dropdown-item>
                </router-link>
                <el-dropdown-item command="logout" divided>
                  <span>退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
        <section class="app-main">
          <router-view v-slot="{ Component, route }">
            <keep-alive>
              <component :is="Component" v-if="!route.meta.link" :key="route.path"/>
            </keep-alive>
          </router-view>
          <!--    <iframe-toggle/>-->
        </section>
      </el-main>
      <el-footer height="30px">
        <span>Copyright © {{ new Date().getFullYear() }} Sijay</span>
      </el-footer>
    </el-container>
  </el-container>
</template>
<script name="layout" setup>
import logo from '@/assets/logo.png'
import userAvatar from '@/assets/icons/svg/user.svg'

import MenuItem from "@/layout/menuItem.vue";


const route = useRoute()
const routeStore = useRouteStore()
const userStore = useUserStore()

const routes = computed(() => routeStore.routes)
const title = ref(import.meta.env.VITE_APP_TITLE)

const activeMenu = computed(() => {
  const {
    meta,
    path
  } = route
  // if set path, the sidebar will highlight the path you set
  if (meta.activeMenu) {
    return meta.activeMenu
  }
  return path
})
const userInfo = computed(() => userStore.userInfo)
const userAvatarImg = computed(() => userInfo.value.avatar ? userInfo.value.avatar : userAvatar)

const logout = () => {
  ElMessageBox.confirm('确定注销并退出系统吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    userStore.logout().then(() => {
      location.href = import.meta.env.VITE_APP_CONTEXT_PATH + 'index';
    })
  })
}
const commandMap = {
  logout
};
const handleCommand = (command) => {
  // 判断是否存在该方法
  if (commandMap[command]) {
    commandMap[command]();
  }
}


const levelList = ref([])

const getBreadcrumb = () => {
  // only show routes with meta.title
  let matched = route.matched.filter(item => item.meta && item.meta.title);
  const first = matched[0]
  // 判断是否为首页
  if (!isDashboard(first)) {
    matched = ([{
      path: '/index',
      meta: {title: '首页'}
    }]).concat(matched)
  }
  levelList.value = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
}
const isDashboard = (route) => {
  const name = route && route.name
  if (!name) {
    return false
  }
  return name.trim() === 'Index'
}
const handleLink = (item) => {
  const {
    redirect,
    path
  } = item
  redirect ? router.push(redirect) : router.push(path)
}

watchEffect(() => {
  // if you go to the redirect page, do not update the breadcrumbs
  if (route.path.startsWith('/redirect/')) {
    return
  }
  getBreadcrumb()
})
onMounted(() => {
  getBreadcrumb();
})
</script>
<style lang="scss" scoped>
.el-container {
  height: 100%;
}

.outer-layer {
  .el-aside {
    border-right: #dfdfdf 1px solid;

    .logo {
      width: 100%;
      height: 50px;
      display: flex;
      justify-content: center;
      align-items: center;
      align-content: center;

      img {
        height: 30px;
        width: 60px;
      }

      h1 {
        color: #42b883;
        margin-left: 5px;
        font-size: 1.5rem;
      }
    }

    .menu-container {
      height: calc(100% - 50px);
      width: 100%;

      .scrollbar-wrapper {
        .el-menu {
          width: 100%;
          border: none;
        }
      }
    }
  }

  .inner-layer {
    .el-header {
      display: flex;
      justify-content: space-between;
      border-bottom: #dfdfdf 1px solid;

      .breadcrumb {
        display: flex;
        flex-direction: column;
        justify-content: center;
      }

      .user-bar {
        display: flex;

        .username {
          line-height: 50px;
          margin-right: 5px;
        }

        .el-avatar {
          margin: 5px;
        }
      }
    }

    .el-main {
      .app-main {
        /* 50= navbar  50  */
        min-height: calc(100vh - 120px);
        width: 100%;
        position: relative;
        overflow: hidden;
      }
    }

    .el-footer {
      text-align: center;
      display: flex;
      flex-wrap: wrap;
      flex-direction: row;
      align-content: center;
      justify-content: center;

      img {
        display: inline-block;
        width: 24px;
        height: 24px;
        margin: 0 0 0 10px;
      }
    }
  }
}


.fixed-header + .app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header + .app-main {
    padding-top: 84px;
  }
}


// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 6px;
  }
}

::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background-color: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background-color: #c0c0c0;
  border-radius: 3px;
}


</style>
