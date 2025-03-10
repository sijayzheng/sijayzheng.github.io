<template>
  <!-- 代码构建 -->
  <div>
    <v-form-designer
        ref="buildRef"
        :designer-config="{ importJsonButton: true, exportJsonButton: true, exportCodeButton: true, generateSFCButton: true, formTemplates: true }"
        class="build"
    >
      <template v-if="showBtn" #customToolButtons>
        <el-button icon="Select" link type="primary" @click="getJson">保存</el-button>
      </template>
    </v-form-designer>
  </div>
</template>

<script setup>
const props = withDefaults(defineProps(), {
  showBtn: true,
  formJson: ''
})

const buildRef = ref()
const emits = defineEmits(['reJson', 'saveDesign'])

//获取表单json
const getJson = () => {
  const formJson = JSON.stringify(buildRef.value.getFormJson())
  const fieldJson = JSON.stringify(buildRef.value.getFieldWidgets())
  let data = {
    formJson,
    fieldJson
  }
  emits('saveDesign', data)
}

onMounted(() => {
  if (props.formJson) {
    buildRef.value.setFormJson(props.formJson)
  }
})
</script>

<style lang="scss">
.build {
  margin: 0 !important;
  overflow-y: auto !important;

  & header.main-header {
    display: none;
  }

  & .right-toolbar-con {
    text-align: right !important;
  }
}
</style>
