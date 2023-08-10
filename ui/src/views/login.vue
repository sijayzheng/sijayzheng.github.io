<template>
  <div class="login">
    <el-form ref="loginRef" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">Z</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" auto-complete="off" placeholder="账号" size="large" type="text">
          <template #prefix>
            <el-icon>
              <i-userfilled></i-userfilled>
            </el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" auto-complete="off" placeholder="密码" size="large" type="password" @keyup.enter="handleLogin">
          <template #prefix>
            <el-icon>
              <i-lock></i-lock>
            </el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button :loading="loading" size="large" style="width:100%;" type="primary" @click.prevent="handleLogin">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script name="login" setup>
import Cookies from 'js-cookie'
import useZStore from '@/store/zStore'

const router = useRouter()
const store = useZStore()

const loginForm = ref({
  username: 'root',
  password: 'admin',
  rememberMe: false,
})
const redirect = ref(undefined)

const loginRules = {
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
  ],
}

const loading = ref(false)

const loginRef = ref()
const handleLogin = () => {
  loginRef.value?.validate((valid, fields) => {
    if (valid) {
      loading.value = true
      // 勾选了需要记住密码设置在 cookie 中设置记住用户名和密码
      if (loginForm.value.rememberMe) {
        Cookies.set('username', String(loginForm.value.username), {expires: 30})
        Cookies.set('password', String(loginForm.value.password), {expires: 30})
        Cookies.set('rememberMe', String(loginForm.value.rememberMe), {expires: 30})
      } else {
        // 否则移除
        Cookies.remove('username')
        Cookies.remove('password')
        Cookies.remove('rememberMe')
      }
      store.login({
        ...loginForm.value,
        path: redirect.value
      })
    }
  })
}

onMounted(() => {
  const username = Cookies.get('username')
  const password = Cookies.get('password')
  const rememberMe = Cookies.get('rememberMe')
  loginForm.value = {
    username: username === undefined ? String(loginForm.value.username) : username,
    password: password === undefined ? String(loginForm.value.password) : String(password),
    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
  }
})
</script>

<style lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-size: cover;
}

.title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;

  .el-input {
    height: 40px;

    input {
      height: 40px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 0px;
  }
}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  height: 40px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial, serif;
  font-size: 12px;
  letter-spacing: 1px;
}

.login-code-img {
  height: 40px;
  padding-left: 12px;
}
</style>
