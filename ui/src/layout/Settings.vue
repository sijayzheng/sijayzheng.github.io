<template>
  <el-drawer
      v-model="showSettings"
      :with-header="false"
      close-on-click-modal
      direction="rtl"
      size="300px"
  >
    <div class="drawer-item">
      <span>主题颜色</span>
      <span class="comp-style">
        <el-color-picker
            v-model="theme"
            :predefine="predefineColors"
            @change="themeChange"
        />
      </span>
    </div>
    <div class="drawer-item">
      <span>深色模式</span>
      <span class="comp-style">
        <el-switch
            v-model="isDark"
            class="drawer-switch"
            @change="toggleDark"
        />
      </span>
    </div>

    <el-divider/>

    <el-button
        icon="DocumentAdd"
        plain
        type="primary"
        @click="saveSetting"
    >
      保存配置
    </el-button>
    <el-button
        icon="Refresh"
        plain
        @click="resetSetting"
    >
      重置配置
    </el-button>
  </el-drawer>
</template>

<script setup>
import {useDynamicTitle} from '@/util/dynamicTitle'
import useAppStore from '@/store/app'
import useSettingsStore from '@/store/settings'
import usePermissionStore from '@/store/permission'
import {handleThemeStyle} from '@/util/theme'
import {defaultSettings} from '@/store/settings.ts'

const appStore = useAppStore()
const settingsStore = useSettingsStore()
const permissionStore = usePermissionStore()

const showSettings = ref(false)
const theme = ref(settingsStore.theme)
const sideTheme = ref(settingsStore.sideTheme)
const storeSettings = computed(() => settingsStore)
const predefineColors = ref(['#409eff', '#ff4500', '#ff8c00', '#ffd700', '#90ee90', '#00ced1', '#1e90ff', '#c71585'])

// 是否暗黑模式
const isDark = useDark({
  storageKey: 'useDarkKey',
  valueDark: 'dark',
  valueLight: 'light'
})
// 匹配菜单颜色
watch(isDark, () => {
  if (isDark.value) {
    settingsStore.sideTheme = 'theme-dark'
  } else {
    settingsStore.sideTheme = sideTheme.value
  }
})
const toggleDark = () => useToggle(isDark)

const topNavChange = (val) => {
  if (!val) {
    appStore.toggleSideBarHide(false)
    permissionStore.setSidebarRouters(permissionStore.defaultRoutes)
  }
}

const dynamicTitleChange = () => {
  // 动态设置网页标题
  useDynamicTitle()
}

const themeChange = (val) => {
  settingsStore.theme = val
  handleThemeStyle(val)
}
const handleTheme = (val) => {
  sideTheme.value = val
  if (isDark.value && val === 'theme-light') {
    // 暗黑模式颜色不变
    settingsStore.sideTheme = 'theme-dark'
    return
  }
  settingsStore.sideTheme = val
}
const saveSetting = () => {
  modalUtil.loading('正在保存到本地，请稍候...')
  const settings = useStorage('layout-setting', defaultSettings)
  settings.value.topNav = storeSettings.value.topNav
  settings.value.tagsView = storeSettings.value.tagsView
  settings.value.fixedHeader = storeSettings.value.fixedHeader
  settings.value.sidebarLogo = storeSettings.value.sidebarLogo
  settings.value.dynamicTitle = storeSettings.value.dynamicTitle
  settings.value.sideTheme = storeSettings.value.sideTheme
  settings.value.theme = storeSettings.value.theme
  modalUtil.closeLoading(1000)
}
const resetSetting = () => {
  modalUtil.loading('正在清除设置缓存并刷新，请稍候...')
  useStorage('layout-setting', null).value = null
  setTimeout('window.location.reload()', 1000)
}
const openSetting = () => {
  showSettings.value = true
}

defineExpose({
  openSetting
})
</script>

<style lang="scss" scoped>
.setting-drawer-title {
  margin-bottom: 12px;
  color: rgba(0, 0, 0, 0.85);
  line-height: 22px;
  font-weight: bold;

  .drawer-title {
    font-size: 14px;
  }
}

.setting-drawer-block-checkbox {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-top: 10px;
  margin-bottom: 20px;

  .setting-drawer-block-checkbox-item {
    position: relative;
    margin-right: 16px;
    border-radius: 2px;
    cursor: pointer;

    img {
      width: 48px;
      height: 48px;
    }

    .custom-img {
      width: 48px;
      height: 38px;
      border-radius: 5px;
      box-shadow: 1px 1px 2px #898484;
    }

    .setting-drawer-block-checkbox-selectIcon {
      position: absolute;
      top: 0;
      right: 0;
      width: 100%;
      height: 100%;
      padding-top: 15px;
      padding-left: 24px;
      color: #1890ff;
      font-weight: 700;
      font-size: 14px;
    }
  }
}

.drawer-item {
  padding: 12px 0;
  font-size: 14px;

  .comp-style {
    float: right;
    margin: -3px 8px 0px 0px;
  }
}
</style>
