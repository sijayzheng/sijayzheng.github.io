<template>
  <el-container>
    <el-aside width="200px">
      <div class="logo">
        <router-link class="router-link" to="/">
          <img :src="logoImg" alt="logo" class="logo-img"/>
          <h1>z</h1>
        </router-link>
      </div>
      <el-scrollbar class="menu-container">
        <el-scrollbar wrap-class="scrollbar-wrapper">
          <el-menu
              :default-active="activeMenu"
              mode="vertical"
              unique-opened
          >
            <menuItem v-for="route in routers" :base-path="route.path" :item="route"></menuItem>
          </el-menu>
        </el-scrollbar>
      </el-scrollbar>
    </el-aside>
    <el-container>
      <el-header height="48">
        <breadcrumb></breadcrumb>
        <div class="right">
          <el-image :src="logoImg"></el-image>
          <el-button @click="store.logout()">
            <el-icon>
              <i-switchbutton></i-switchbutton>
            </el-icon>
          </el-button>
        </div>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script name="Layout" setup>
import logoImg from '@/assets/logo/logo.ico'
import {externalLinks, routes} from '@/router'
import menuItem from '@/layout/menuItem.vue'
import Breadcrumb from '@/layout/breadcrumb.vue'
import useZStore from '@/store/zStore'


const route = useRoute()
const store = useZStore()

const routers = computed(() => [...routes, ...externalLinks].filter(item => !item.hidden))
const activeMenu = computed(() => {
  const {path} = route
  return path
})
</script>

<style lang="scss" scoped>
.el-container {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}

.el-aside {
  box-shadow: #dddddd 0 0 2px 2px;
}

.el-header {
  box-shadow: #dddddd 2px 0 2px 2px;
}

.logo {
  width: 200px;
  height: 50px;
  text-align: center;
  overflow: hidden;
  line-height: 50px;

  .router-link {
    height: 100%;
    width: 100%;
    display: flex;
    align-content: center;
    justify-content: center;
    align-items: center;

    .logo-img {
      height: 40px;
      width: 40px;
    }

  }
}

.menu-container {
  height: calc(100% - 50px);
}

.right {
  float: right;
  display: flex;
  height: 48px;
  align-content: center;
  align-items: center;

  .el-image {
    height: 48px;
  }
}
</style>
