<template>
  <inner-link
      v-for="(item, index) in tagsViewStore.iframeViews"
      v-show="route.path === item.path"
      :key="item.path"
      :iframe-id="'iframe' + index"
      :src="iframeUrl(item.meta ? item.meta.link : '', item.query)"
  />
</template>

<script setup>
import InnerLink from '@/layout/InnerLink.vue'

const route = useRoute()
const tagsViewStore = useTagsViewStore()

function iframeUrl(url, query) {
  if (Object.keys(query).length > 0) {
    let params = Object.keys(query)
        .map((key) => key + '=' + query[key])
        .join('&')
    return url + '?' + params
  }
  return url
}
</script>
