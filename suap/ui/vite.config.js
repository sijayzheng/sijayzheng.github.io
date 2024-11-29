import path from 'path'
import vue from '@vitejs/plugin-vue'
import {defineConfig, loadEnv} from 'vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import Unocss from 'unocss/vite'
import {presetAttributify, presetIcons, presetUno, transformerDirectives, transformerVariantGroup,} from 'unocss'

// https://vitejs.dev/config/
export default defineConfig(({mode}) => {
  const env = loadEnv(mode, process.cwd())
  return {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src'),
        'iconify': '~icons'
      }
    },
    server: {
      host: '0.0.0.0',
      port: env.VITE_APP_PORT,
      cors: true,
      proxy: {
        ['^' + env.VITE_APP_BASE_API]: {
          target: env.VITE_APP_BACK,
          changeOrigin: true,
          rewrite: (path) => path.replace(new RegExp('^' + env.VITE_APP_BASE_API), ''),
          ws: true,
        },
      },
    },
    plugins: [vue(), AutoImport({
      imports: ['vue', 'pinia', 'vue-router', {axios: [['default', 'axios']]}, '@vueuse/core'],
      dirs: ['src/api/**', 'src/composable/**', 'src/directive/**', 'src/router/**', 'src/store/**', 'src/utils/**'],
      vueTemplate: true,
      resolvers: [ElementPlusResolver(), IconsResolver({prefix: 'Icon'}),],
      dts: 'types/auto-imports.d.ts',
    }), Components({
      extensions: ['vue', 'md'], // allow auto import and register components used in markdown
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
      resolvers: [IconsResolver({enabledCollections: ['ep']}), ElementPlusResolver({importStyle: 'sass'}),],
      types: [{
        from: 'vue-router',
        names: ['RouterLink', 'RouterView'],
      },],
      dirs: ['src/components/**', 'src/layout/**', 'src/views/**'],
      dts: 'types/components.d.ts',
    }), Icons({
      compiler: 'vue3',
      autoInstall: true
    }), Unocss({
      presets: [presetUno(), presetAttributify(), presetIcons({
        scale: 1,
        warn: true,
      }),],
      transformers: [transformerDirectives(), transformerVariantGroup(),]
    }),],
    build: {
      outDir: '../src/main/resources/static',
    }
  }
})
