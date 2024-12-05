<template>
  <div :style="'height:' + height">
    <iframe
      :id="iframeId"
      :src="src"
      style="width: 100%; height: 100%; border: 0"
    />
  </div>
</template>

<script setup>
const props = defineProps({
  src: propTypes.string.def('/'),
  iframeId: propTypes.string.isRequired
})
const height = ref(document.documentElement.clientHeight - 94.5 + 'px')
</script>
