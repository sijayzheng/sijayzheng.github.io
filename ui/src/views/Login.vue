<template>
  <div class="login">
    <el-form
      ref="loginRef"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
    >
      <h3>BUN开发平台</h3>
      <el-form-item prop="module">
        <el-select
          v-model="loginForm.module"
          auto-complete="off"
          placeholder="模块"
          size="large"
          type="text"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          auto-complete="off"
          placeholder="账号"
          size="large"
          type="text"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密码"
          size="large"
          type="password"
          @keyup.enter="handleLogin"
        >
        </el-input>
      </el-form-item>
      <!--      <el-form-item v-if="captchaEnabled" prop="code">-->
      <!--        <el-input-->
      <!--          v-model="loginForm.code"-->
      <!--          auto-complete="off"-->
      <!--          placeholder="验证码"-->
      <!--          size="large"-->
      <!--          style="width: 63%"-->
      <!--          @keyup.enter="handleLogin"-->
      <!--        >-->
      <!--        </el-input>-->
      <!--        <div class="login-code">-->
      <!--          <img :src="codeUrl" alt="" class="login-code-img" @click="getCode" />-->
      <!--        </div>-->
      <!--      </el-form-item>-->
      <el-checkbox v-model="loginForm.rememberMe" style="margin: 0 0 25px 0">记住密码
      </el-checkbox>
      <el-form-item style="width: 100%">
        <el-button
          :loading="loading"
          size="large"
          style="width: 100%"
          type="primary"
          @click.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <!--        <div v-if="register" style="float: right">-->
        <!--          <router-link :to="'/register'" class="link-type">立即注册</router-link>-->
        <!--        </div>-->
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>

const userStore = useUserStore()
const router = useRouter()

const loginForm = ref({
  module: 1,
  username: 'admin',
  password: 'admin123',
  rememberMe: false,
  code: '',
  uuid: ''
})

const loginRules = {
  module: [
    {
      required: true,
      trigger: 'blur',
      message: '请选择模块'
    }
  ],
  username: [
    {
      required: true,
      trigger: 'blur',
      message: '请输入您的账号'
    }
  ],
  password: [
    {
      required: true,
      trigger: 'blur',
      message: '请输入您的密码'
    }
  ]
  // code: [
  //   {
  //     required: true,
  //     trigger: 'change',
  //     message: '请输入验证码'
  //   }
  // ]
}

const codeUrl = ref('')
const loading = ref(false)
// 验证码开关
const captchaEnabled = ref(false)

// 注册开关
const register = ref(true)
const redirect = ref('/')
const loginRef = ref()
const options = [
  {
    value: 1,
    label: '系统后台'
  }
]

watch(
  () => router.currentRoute.value,
  (newRoute) => {
    redirect.value =
      newRoute.query && decodeURIComponent(newRoute.query.redirect)
  },
  { immediate: true }
)

const handleLogin = () => {
  loginRef.value?.validate(async (valid, fields) => {
    if (valid) {
      loading.value = true
      // 勾选了需要记住密码设置在 localStorage 中设置记住用户名和密码
      if (loginForm.value.rememberMe) {
        localStorage.setItem('module', String(loginForm.value.module))
        localStorage.setItem('username', String(loginForm.value.username))
        localStorage.setItem('password', String(loginForm.value.password))
        localStorage.setItem('rememberMe', String(loginForm.value.rememberMe))
      } else {
        // 否则移除
        localStorage.removeItem('module')
        localStorage.removeItem('username')
        localStorage.removeItem('password')
        localStorage.removeItem('rememberMe')
      }
      // 调用action的登录方法
      const [err] = await ato(userStore.login(loginForm.value))
      if (!err) {
        const redirectUrl = redirect.value || '/'
        await router.push(redirectUrl)
        loading.value = false
      } else {
        loading.value = false
        // 重新获取验证码
        if (captchaEnabled.value) {
          // await getCode()
        }
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}

/**
 * 获取验证码
 */
// const getCode = async () => {
//   const res = await getCodeImg()
//   const { data } = res
//   captchaEnabled.value =
//     data.captchaEnabled === undefined ? true : data.captchaEnabled
//   if (captchaEnabled.value) {
//     codeUrl.value = 'data:image/gif;base64,' + data.img
//     loginForm.value.uuid = data.uuid
//   }
// }

const getLoginData = () => {
  const username = localStorage.getItem('username')
  const password = localStorage.getItem('password')
  const rememberMe = localStorage.getItem('rememberMe')
  loginForm.value = {
    username: username === null ? String(loginForm.value.username) : username,
    password:
      password === null ? String(loginForm.value.password) : String(password),
    rememberMe: rememberMe === null ? false : Boolean(rememberMe)
  }
}

onMounted(() => {
  // getCode()
  getLoginData()
})
</script>

<style lang="scss" scoped>
.login {
  width: 30%;
  margin-left: 30%;
  margin-top: 2%;

  .login-code {
    width: 33%;
    height: 40px;
    float: right;

    img {
      cursor: pointer;
      vertical-align: middle;
    }

    .login-code-img {
      height: 40px;
      padding-left: 12px;
    }
  }
}
</style>
