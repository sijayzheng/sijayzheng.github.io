import zhCN from 'element-plus/es/locale/lang/zh-cn';
import enUS from 'element-plus/es/locale/lang/en';

export const useAppStore = defineStore('app', () => {
  const sidebarStatus = useStorage('sidebarStatus', '1');
  const sidebar = reactive({
    opened: sidebarStatus.value ? !!+sidebarStatus.value : true,
    withoutAnimation: false,
    hide: false
  });
  const size = useStorage('size', 'default');

  // 语言
  const language = useStorage('language', 'zh_CN');
  const languageObj = {
    en_US: enUS,
    zh_CN: zhCN
  };
  const locale = computed(() => {
    return languageObj[language.value];
  });

  const toggleSideBar = (withoutAnimation) => {
    if (sidebar.hide) {
      return false;
    }

    sidebar.opened = !sidebar.opened;
    sidebar.withoutAnimation = withoutAnimation;
    if (sidebar.opened) {
      sidebarStatus.value = '1';
    } else {
      sidebarStatus.value = '0';
    }
  };

  const closeSideBar = ({ withoutAnimation }) => {
    sidebarStatus.value = '0';
    sidebar.opened = false;
    sidebar.withoutAnimation = withoutAnimation;
  };

  const setSize = (s) => {
    size.value = s;
  };
  const toggleSideBarHide = (status) => {
    sidebar.hide = status;
  };

  const changeLanguage = (val) => {
    language.value = val;
  };

  return {
    sidebar,
    language,
    locale,
    size,
    changeLanguage,
    toggleSideBar,
    closeSideBar,
    setSize,
    toggleSideBarHide
  };
});

export default useAppStore;
