import path from 'node:path'

import vue from '@vitejs/plugin-vue'
import Unocss from 'unocss/vite'
import AutoImport from 'unplugin-auto-import/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import Components from 'unplugin-vue-components/vite'
import {defineConfig} from 'vite'

const elementPlusResolver = ElementPlusResolver({
  importStyle: 'sass',
})
export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    },
  },
  server: {
    port: 7788,
    cors: true,
    proxy: {
      '/api': {
        target: 'http://localhost:8888',
        changeOrigin: true,
        rewrite: (path) => {
          console.log(path.replace(/^\/api/, ''))
          return path.replace(/^\/api/, '')
        },
      },
    },
  },
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@use "@/styles/ep.scss" as *;`,
      },
    },
  },
  plugins: [
    vue(),
    Components({
      extensions: ['vue', 'md'],
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
      resolvers: [elementPlusResolver],
      dts: 'src/types/components.d.ts',
    }),
    AutoImport({
      include: [/\.[tj]sx?$/, /\.vue$/, /\.vue\?vue/],
      resolvers: [elementPlusResolver],
      dirs: ['src/utils', 'src/stores', 'src/api/**', 'src/composables', 'src/router'],
      imports: [
        'vue',
        'vue-router',
        'pinia',
        '@vueuse/core',
        {
          axios: [['default', 'axios']],
        },
        {
          from: 'axios',
          imports: ['AxiosResponse', 'InternalAxiosRequestConfig'],
          type: true,
        },
        {
          from: 'element-plus',
          imports: ['FormInstance'],
          type: true,
        },
      ],
      dts: 'src/types/auto-imports.d.ts',
    }),
    Unocss(),
  ],
})
