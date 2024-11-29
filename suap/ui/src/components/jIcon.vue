<template>
  <component :is="props.icon" :style="{width: `${size}`,height: `${size}`}"></component>
</template>

<script setup>
defineOptions({
  name: 'JIcon'
})
const props = defineProps({
  icon: {
    type: String,
    default: 'Loading'
  },
  size: {
    type: [Number, String],
    default: 1
  }
})
const size = computed(() => {
  return Number.isFinite(props.size) ? props.size + 'rem' :
    /^\d+$/.test(String(props.size)) ? props.size + 'rem' : props.size
})
</script>

<style scoped>

</style>
