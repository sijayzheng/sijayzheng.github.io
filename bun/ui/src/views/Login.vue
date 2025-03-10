<script lang="ts" setup>
import type {FormInstance} from 'element-plus'
import {hola} from '@/utils/request'
import {useRequest} from 'alova/client'

const loginFormRef = ref<FormInstance>()
const formData = ref({
  module: 1,
  username: 'admin',
  password: 'admin',
  rememberMe: false,
})
const {loading, data, send: login} = useRequest(hola('get', '/login', {
  ...formData.value,
}), {immediate: false})
const {loading: loading1, data: data1, send: send1} = useRequest(hola('get', '/list', {
  ...formData.value,
}))
const {loading: loading2, data: data2, send: send2} = useRequest(hola('get', '/tree', {
  ...formData.value,
}), {immediate: false})
const {loading: loading3, data: data3, send: send3} = useRequest(hola('post', '/oa/oaPurchaseContract/downloadTemplate', {
  ...formData.value,
}, {
  isDownload: true,
}), {immediate: false})

const rules = reactive({
  name: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 2, max: 10, message: '用户名长度需要再2-10之间', trigger: 'blur'},
  ],

  count: [
    {
      required: true,
      message: '请选择 Activity count',
      trigger: 'change',
    },
  ],

  type: [
    {
      type: 'array',
      required: true,
      message: '请选择 at least one activity type',
      trigger: 'change',
    },
  ],
  resource: [
    {
      required: true,
      message: '请选择 activity resource',
      trigger: 'change',
    },
  ],
  desc: [
    {required: true, message: '请输入 activity form', trigger: 'blur'},
  ],
})

function submitForm1() {
  send1()
}

function submitForm2() {
  send2()
}

function submitForm3() {
  send3()
}

function submitForm() {
  login()

  // loginFormRef.value?.validate((valid, fields) => {
  //   if (valid) {
  //     console.log('submit!')
  //   }
  //   else {
  //     console.log('error submit!', fields)
  //   }
  // })
}
</script>

<template>
  <el-form
    ref="loginFormRef"
    :model="formData"
    :rules="rules"
    status-icon
    style="max-width: 400px"
  >
    <el-form-item prop="module">
      <el-select v-model="formData.module" placeholder="系统模块">
        <el-option :value="1" label="one"/>
        <el-option :value="2" label="two"/>
      </el-select>
    </el-form-item>
    <el-form-item prop="username">
      <el-input v-model="formData.username"/>
    </el-form-item>
    <el-form-item prop="password">
      <el-input v-model="formData.password" show-password type="password"/>
    </el-form-item>
    <el-form-item prop="rememberMe">
      <el-checkbox v-model="formData.rememberMe">
        记住我
      </el-checkbox>
    </el-form-item>
  </el-form>
  <el-form-item>
    <el-button :loading="loading" type="primary" @click="submitForm()">
      登录
    </el-button>
  </el-form-item>
</template>
