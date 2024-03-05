<template>
  <div :style="'height:' + height">
    <iframe :id="props.iframeId" :src="props.src" style="width: 100%; height: 100%;border: 0"></iframe>
  </div>
</template>

<script name="InnerLink" setup>
const props = defineProps({
  src: {
    type: String,
    default: '/'
  },
  iframeId: {
    type: String
  }
})
const height = ref(document.documentElement.clientHeight - 95 + 'px')
</script>

<style scoped>

</style>
