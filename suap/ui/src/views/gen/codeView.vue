<template>
  <div class="hljs-container">
    <el-button class="copy-button" @click="copy">一键复制</el-button>
    <highlightjs :autodetect="!props.language" :code="props.code" :language="props.language" class="code-container"></highlightjs>
  </div>
</template>

<script name="CodeView" setup>
import useClipboard from 'vue-clipboard3'

const {toClipboard} = useClipboard()
const props = defineProps({
  language: {
    type: String,
    default: () => undefined
  },
  code: {
    type: String,
    default: () => '无'
  }
})
const copy = () => {
  toClipboard(props.code)
  ElMessage.success('复制成功')
}
</script>

<style scoped>
.copy-button {
  position: absolute;
  right: 5px;
}

.code-container > code, .code-container > code > span {
  font-family: 'Cascadia Mono Light', monospace !important;
}
</style>
