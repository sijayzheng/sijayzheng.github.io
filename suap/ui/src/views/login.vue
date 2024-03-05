<template>
  <div class="card-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>登录</span>
        </div>
      </template>
      <el-form :model="data">
        <el-form-item label="username">
          <el-input v-model="data.username"/>
        </el-form-item>
        <el-form-item label="password">
          <el-input v-model="data.password"/>
        </el-form-item>
        <el-form-item label="rememberMe">
          <el-checkbox v-model="data.rememberMe"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script name="Login" setup>

import useUserStore from "@/store/userStore.js";
import router from "@/router/index.js";

const userStore = useUserStore()

const data = reactive({
  username: 'su',
  password: '123456',
  rememberMe: true
})
const loading = ref(false)
const redirect = ref('')
watch(() => router.currentRoute.value, (newRoute) => {
  redirect.value = newRoute.query && newRoute.query.redirect
}, {immediate: true})


const login = () => {
  userStore.login(data).then(() => {
    loading.value = false
    router.push({path: redirect.value || '/'})
  })
}

</script>

<style lang="scss" scoped>
.card-container {
  position: absolute;
  top: 100px;
  right: 200px;

  .el-card {
    width: 400px;
    height: 300px;
  }
}

</style>
