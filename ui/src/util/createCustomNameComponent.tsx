/**
 * 后台返回的路由动态生成name 解决缓存问题
 * 感谢 @fourteendp
 * 详见 https://github.com/vbenjs/vue-vben-admin/issues/3927
 */
import { defineComponent, h } from 'vue';

export function createCustomNameComponent(loader, options = {}) {
  const { name } = options;
  let component = null;

  const load = async () => {
    try {
      const { default: loadedComponent } = await loader();
      component = loadedComponent;
    } catch (error) {
      console.error(`Cannot resolve component ${name}, error:`, error);
    }
  };

  return async () => {
    if (!component) {
      await load();
    }

    return Promise.resolve(
      defineComponent({
        name,
        render() {
          return h(component);
        }
      })
    );
  };
}
