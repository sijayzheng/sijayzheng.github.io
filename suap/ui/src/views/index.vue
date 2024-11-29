<template>
  <div>
    {{ router.currentRoute.value.fullPath }}
    <p v-for="i in 100">
      <!--      <el-button type="primary">p{{ i }}</el-button>-->
    </p>
  </div>
</template>

<script setup>
defineOptions({
  name: 'Index'
})
const router = useRouter()
</script>

<style scoped>

</style>
