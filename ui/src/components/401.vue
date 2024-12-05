<template>
  <h1>没有访问权限！{{ timeout }}秒后返回主页</h1>
</template>

<script setup>
const timeout = ref(3)
const iv = setInterval(() => {
  if (timeout.value > 1) {
    timeout.value--
  } else {
    clearInterval(iv)
    window.location.href = '/'
  }
}, 1000)
</script>

<style lang="scss" scoped></style>
