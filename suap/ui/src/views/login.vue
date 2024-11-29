<template>
  <div class="login-bg">
    <div class="login-container">
      <el-form :model="form">
        <el-form-item label="module">
          <el-input v-model="form.module"/>
        </el-form-item>
        <el-form-item label="username">
          <el-input v-model="form.username"/>
        </el-form-item>
        <el-form-item label="password">
          <el-input v-model="form.password"/>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">submit</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
defineOptions({
  name: 'Login'
})
// do not use same name with ref
const form = ref({
  module: 1,
  username: 'su',
  password: 'admin'
})
const router = useRouter()
const onSubmit = async () => {
  const [err] = await awaitTo(useUserStore().login(form.value))
  if (err) {
    console.log(err)
  } else {
    let fullPath = router.currentRoute.value.fullPath
    if (fullPath.startsWith('/login?redirect=')) {
      await router.push(fullPath.substring('/login?redirect='.length))
    } else {
      await router.push('/')
    }
  }
}
</script>

<style lang="scss" scoped>
.login-bg {
  height: 100vh;
  width: 100vw;
  display: flex;
  justify-content: center;
  background-image: url("@/assets/bg.png");
  background-repeat: space;

  .login-container {
    display: flex;
    justify-content: center;
    flex-direction: column;
  }
}

</style>
