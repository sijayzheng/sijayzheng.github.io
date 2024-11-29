<template>
  <div class="layout">
    <div class="side">
      <div class="side-header">suap</div>
      <div class="menu-container">
        <el-scrollbar>
          <el-menu :default-active="router.currentRoute.value.name||'Index'" router unique-opened>
            <JMenu :data="routes"/>
          </el-menu>
        </el-scrollbar>
      </div>
    </div>
    <div class="main">
      <el-row align="middle" class="header">
        <el-col :span="20">
          <el-tag to="/">首页</el-tag>
          <el-tag v-for="item in tags" :key="item.key" closable @close="closeTag(item)">
            <router-link :to="item.path" class="no-style-link el-tag__content">{{ item.name }}</router-link>
          </el-tag>
        </el-col>
        <el-col :span="4" class="header-right">
          <el-dropdown @command="handleCommand">
            <el-avatar>{{ name }}</el-avatar>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="toggle">{{ dark ? '打开' : '关闭' }}黑暗模式</el-dropdown-item>
                <el-dropdown-item command="logout">退出</el-dropdown-item>
                <el-dropdown-item command="profile">个人资料</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-col>
      </el-row>
      <div class="content">
        <el-scrollbar wrap-class="content-wrap">
          <el-backtop :visibility-height="10" target=".content-wrap"></el-backtop>
          <router-view v-slot="{ Component ,route}" class="content-container">
            <component :is="Component" v-if="!route.meta.link" :key="route.path"/>
          </router-view>
        </el-scrollbar>
      </div>
    </div>
  </div>
</template>

<script setup>
import JMenu from '@/layout/jMenu.vue'

defineOptions({
  name: 'Layout'
})
const dark = ref(false)
const route = useRoute()
const router = useRouter()
const routes = ref([])
const tags = computed(() => useVisitedStore().visited)
watch(route, (r) => {
  useVisitedStore().add(r)
})
const closeTag = (tag) => {
  useVisitedStore().remove(tag)
}
const name = computed(() => {
  const name = useUserStore().userInfo.name
  return name?.length > 2 ? name.substring(name.length - 2, name.length) : name
})
const logout = () => {
  useUserStore().logout()
  router.push('/login')
}
const filterVisible = (routes) => {
  return routes.filter(item => item.meta?.visible)
    .map(item => {
      if (item.children) {
        item.children = filterVisible(item.children)
      }
      return item
    })
}
const handleCommand = (command) => {
  switch (command) {
    case 'logout':
      logout()
      break
    case 'profile':
      break
    case 'toggle':
      dark.value = !dark.value
      toggleDark()
      break
  }


}
onMounted(() => {
  routes.value = filterVisible(useRouteStore().routes)
})
</script>

<style lang="scss" scoped>
.layout {
  display: flex;
  height: 100vh;
  width: 100vw;

  .side {
    width: 256px;
    height: 100vh;
    box-shadow: #cdcdcd 1px 48px 5px 0;

    .side-header {
      height: 48px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .menu-container {
      height: calc(100vh - 48px);
    }
  }

  .main {
    width: calc(100vw - 256px);
    height: 100vh;

    .header {
      height: 48px;
      padding: 0 1rem;
      box-shadow: #cdcdcd 0 1px 2px 0;

      .header-right {
        display: flex;
        justify-content: flex-end;
      }
    }


    .content {
      height: calc(100vh - 80px);
      padding: 8px 0 0 8px;


    }
  }
}
</style>
