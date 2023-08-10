import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import VueSetupExtend from 'vite-plugin-vue-setup-extend'
import AutoImport from 'unplugin-auto-import/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import IconsResolver from 'unplugin-icons/resolver'
import Components from 'unplugin-vue-components/vite'
import Icons from 'unplugin-icons/vite'

export default defineConfig({
        resolve: {
            alias: {
                '~': path.resolve(__dirname, './'),
                '@': path.resolve(__dirname, './src'),
            },
            extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
        },
        plugins: [
            vue(),
            VueSetupExtend(),
            AutoImport({
                imports: [
                    'vue',
                    'vue-router',
                    'pinia',
                    '@vueuse/core',
                ],
                dts: './dts/auto-imports.d.ts'
            }),
            Components({
                resolvers: [
                    ElementPlusResolver(),
                    IconsResolver({
                        enabledCollections: ['ep']
                    })
                ],
                dts: './dts/components.d.ts'
            }),
            Icons({
                // 自动安装图标库
                autoInstall: true
            }),
        ],
        server: {
            port: 9999,
            proxy: {
                '/api': {
                    target: 'http://localhost:9990',
                    changeOrigin: true,
                    rewrite: (path) => path.replace(new RegExp('^/api'), '')
                }
            }
        },
    }
)
