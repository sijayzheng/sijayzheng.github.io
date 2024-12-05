<template>
  <div />
</template>

<script setup>
const { params, query } = useRoute()
const { path } = params

useRouter().replace({
  path: '/' + path,
  query
})
</script>
