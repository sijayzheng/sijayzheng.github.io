<template>
  this is a 404 page
  will return after {{ second }} second
</template>

<script setup>
defineOptions({
  name: 'ER404'
})
const router = useRouter()
const second = ref(3)
onMounted(() => {
  let iv = setInterval(() => {
    if (second.value > 0) {
      second.value--
    } else {
      clearInterval(iv)
      router.back()
    }
  }, 1000)
})
</script>

<style scoped>

</style>
