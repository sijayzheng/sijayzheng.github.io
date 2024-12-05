<template>
  <div
    :style="{ 'width': width }"
    class="relative"
  >
    <el-input
      v-model="modelValue"
      placeholder="点击选择图标"
      readonly
      @click="visible = !visible"
    >
      <template #prepend>
        <svg-icon :icon-class="modelValue" />
      </template>
    </el-input>

    <el-popover
      :visible="visible"
      :width="450"
      placement="bottom-end"
      shadow="none"
      trigger="click"
    >
      <template #reference>
        <div
          class="cursor-pointer text-[#999] absolute right-[10px] top-0 height-[32px] leading-[32px]"
          @click="visible = !visible"
        >
          <i-ep-caret-top v-show="visible" />
          <i-ep-caret-bottom v-show="!visible" />
        </div>
      </template>

      <el-input
        v-model="filterValue"
        class="p-2"
        clearable
        placeholder="搜索图标"
        @input="filterIcons"
      />

      <el-scrollbar height="w-[200px]">
        <ul class="icon-list">
          <el-tooltip
            v-for="(iconName, index) in iconNames"
            :key="index"
            :content="iconName"
            effect="light"
            placement="bottom"
          >
            <li
              :class="['icon-item', { active: modelValue === iconName }]"
              @click="selectedIcon(iconName)"
            >
              <svg-icon
                :icon-class="iconName"
                color="var(--el-text-color-regular)"
              />
            </li>
          </el-tooltip>
        </ul>
      </el-scrollbar>
    </el-popover>
  </div>
</template>

<script setup>
import icons from '@/components/IconSelect/requireIcons'
import { propTypes } from '@/util/propTypes'

const props = defineProps({
  modelValue: propTypes.string.isRequired,
  width: propTypes.string.def('400px')
})

const emit = defineEmits(['update:modelValue'])
const visible = ref(false)
const { modelValue, width } = toRefs(props)
const iconNames = ref(icons)

const filterValue = ref('')

/**
 * 筛选图标
 */
const filterIcons = () => {
  if (filterValue.value) {
    iconNames.value = icons.filter((iconName) => iconName.includes(filterValue.value))
  } else {
    iconNames.value = icons
  }
}
/**
 * 选择图标
 * @param iconName 选择的图标名称
 */
const selectedIcon = (iconName) => {
  emit('update:modelValue', iconName)
  visible.value = false
}
</script>

<style lang="scss" scoped>
.el-scrollbar {
  max-height: calc(50vh - 100px) !important;
  overflow-y: auto;
}

.el-divider--horizontal {
  margin: 10px auto !important;
}

.icon-list {
  display: flex;
  flex-wrap: wrap;
  padding-left: 10px;
  margin-top: 10px;

  .icon-item {
    cursor: pointer;
    width: 10%;
    margin: 0 10px 10px 0;
    padding: 5px;
    display: flex;
    flex-direction: column;
    justify-items: center;
    align-items: center;
    border: 1px solid #ccc;

    &:hover {
      border-color: var(--el-color-primary);
      color: var(--el-color-primary);
      transition: all 0.2s;
      transform: scaleX(1.1);
    }
  }

  .active {
    border-color: var(--el-color-primary);
    color: var(--el-color-primary);
  }
}
</style>
