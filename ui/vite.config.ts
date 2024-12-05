import { defineConfig, loadEnv, UserConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import path from 'path'
import IconsResolver from 'unplugin-icons/resolver'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import Icons from 'unplugin-icons/vite'

export default defineConfig(({ mode, command }): UserConfig => {
  const env = loadEnv(mode, process.cwd())
  console.log(env.VITE_APP_ENV)
  return {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src')
      }
    },
    server: {
      // host: true,
      port: 1989,
      proxy: {
        '/api': {
          target: 'http://localhost:8888',
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          api: 'modern-compiler'
        }
      }
    },
    plugins: [
      vue(),
      AutoImport({
        include: [/\.[tj]sx?$/, /\.vue$/, /\.vue\?vue/, /\.md$/],
        imports: [
          'vue',
          '@vueuse/core',
          'pinia',
          {
            'vue-router': ['createRouter', 'createWebHistory', 'useRoute', 'useRouter'],
            'axios': [
              ['default', 'axios'] // import { default as axios } from 'axios',
            ],
            'await-to-js': [['to', 'ato']]
            //'[package-name]': ['[import-names]',// alias['[from]', '[alias]'],],
          }
        ],
        dirs: ['src/plugins/**', 'src/router/**', 'src/store/**', 'src/util/**', 'src/api/**', 'src/types/**'],
        vueTemplate: true,
        resolvers: [
          IconsResolver({
            prefix: 'Icon'
          })
        ],
        viteOptimizeDeps: true,
        dts: true,
        injectAtEnd: true
      }),
      Components({
        resolvers: [
          IconsResolver({
            enabledCollections: ['ep']
          })
        ],
        dts: true
      }),
      createSvgIconsPlugin({
        // 指定需要缓存的图标文件夹
        iconDirs: [path.resolve(path.resolve(__dirname, 'src'), 'assets/icons')],
        // 指定symbolId格式
        symbolId: 'icon-[dir]-[name]',
        svgoOptions: command === 'build'
      }),
      Icons({
        // 自动安装图标库
        autoInstall: true
      })
    ],
    // 预编译
    optimizeDeps: {
      include: [
        // 'vue',
        // 'vue-router',
        // 'pinia',
        // 'axios',
        // '@vueuse/core',
        // 'echarts',
        // 'element-plus/es/components/**/css'

      ]
    }
  }
})
