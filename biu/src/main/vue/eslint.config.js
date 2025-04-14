import antfu from '@antfu/eslint-config'

export default antfu({
  stylistic: {
    indent: 2,
    quotes: 'single',
  },
  vue: true,
  unocss: true,
  typescript: true,
  formatters: true,
}, {
  ignores: ['node_modules/', 'dist/', '*.d.ts'],
}, {
  rules: {
    'vue/block-order': ['error', {
      order: ['template', 'script', 'style'],
    }],
    'style/brace-style': ['error', '1tbs', { allowSingleLine: true }],
    'no-undef': 'off',
    'no-unused-vars': 'off',
    'unused-imports/no-unused-vars': 'off',
  },
})
