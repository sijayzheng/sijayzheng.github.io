<template>
  <div class="">
    <v-form-render ref="vFormRef" :form-data="formData" :form-json="formJson"/>
  </div>
</template>

<!-- 动态表单渲染 -->
<script setup>
const props = withDefaults(defineProps(), {
  formJson: '',
  formData: '',
  isView: false
})

const vFormRef = ref()
// 获取表单数据-异步
const getFormData = () => {
  return vFormRef.value.getFormData()
}

/**
 * 设置表单内容
 * @param {表单配置} formConf
 * formConfig：{ formTemplate：表单模板，formData：表单数据，hiddenField：需要隐藏的字段字符串集合，disabledField：需要禁用的自读字符串集合}
 */
const initForm = (formConf) => {
  const {
    formTemplate,
    formData,
    hiddenField,
    disabledField
  } = toRaw(formConf)
  if (formTemplate) {
    vFormRef.value.setFormJson(formTemplate)
    if (formData) {
      vFormRef.value.setFormData(formData)
    }
    if (disabledField && disabledField.length > 0) {
      setTimeout(() => {
        vFormRef.value.disableWidgets(disabledField)
      }, 200)
    }
    if (hiddenField && hiddenField.length > 0) {
      setTimeout(() => {
        vFormRef.value.hideWidgets(hiddenField)
      }, 200)
    }
    if (props.isView) {
      setTimeout(() => {
        vFormRef.value.disableForm()
      }, 100)
    }
  }
}
defineExpose({
  getFormData,
  initForm
})
</script>
