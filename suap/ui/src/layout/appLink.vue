<template>
  <component :is="type" v-bind="linkProps()">
    <slot/>
  </component>
</template>


<script name="appLink" setup>

import {isUrl} from "@/utils/common.js";

const props = defineProps({
  to: {
    type: [String, Object],
    required: true
  }
})

const isExt = computed(() => {
  return isUrl(props.to)
})

const type = computed(() => {
  if (isExt.value) {
    return 'a'
  }
  return 'router-link'
})

function linkProps() {
  if (isExt.value) {
    return {
      href: props.to,
      target: '_blank',
      rel: 'noopener'
    }
  }
  return {
    to: props.to
  }
}

</script>

<style scoped>

</style>
