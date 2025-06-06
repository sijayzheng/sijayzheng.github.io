<template>
  <div
      :class="{ show: show }"
      class="header-search"
  >
    <svg-icon
        class-name="search-icon"
        icon-class="search"
        @click.stop="click"
    />
    <el-select
        ref="headerSearchSelectRef"
        v-model="search"
        :remote-method="querySearch"
        class="header-search-select"
        default-first-option
        filterable
        placeholder="Search"
        remote
        @change="change"
    >
      <el-option
          v-for="option in options"
          :key="option.item.path"
          :label="option.item.title.join(' > ')"
          :value="option.item"
      />
    </el-select>
  </div>
</template>

<script setup>
import Fuse from 'fuse.js'
import {getNormalPath} from '@/util/ruoyi'
import {isHttp} from '@/util/validate'
import usePermissionStore from '@/store/permission'

const search = ref('')
const options = ref([])
const searchPool = ref([])
const show = ref(false)
const fuse = ref()
const headerSearchSelectRef = ref()
const router = useRouter()
const routes = computed(() => usePermissionStore().getRoutes())

const click = () => {
  show.value = !show.value
  if (show.value) {
    headerSearchSelectRef.value && headerSearchSelectRef.value.focus()
  }
}
const close = () => {
  headerSearchSelectRef.value && headerSearchSelectRef.value.blur()
  options.value = []
  show.value = false
}
const change = (val) => {
  const path = val.path
  const query = val.query
  if (isHttp(path)) {
    // http(s):// 路径新窗口打开
    const pindex = path.indexOf('http')
    window.open(path.substr(pindex, path.length), '_blank')
  } else {
    if (query) {
      router.push({
        path: path,
        query: JSON.parse(query)
      })
    } else {
      router.push(path)
    }
  }
  search.value = ''
  options.value = []
  nextTick(() => {
    show.value = false
  })
}
const initFuse = (list) => {
  fuse.value = new Fuse(list, {
    shouldSort: true,
    threshold: 0.4,
    location: 0,
    distance: 100,
    minMatchCharLength: 1,
    keys: [
      {
        name: 'title',
        weight: 0.7
      },
      {
        name: 'path',
        weight: 0.3
      }
    ]
  })
}
// Filter out the routes that can be displayed in the sidebar
// And generate the internationalized title
const generateRoutes = (routes, basePath = '', prefixTitle = []) => {
  let res = []
  routes.forEach((r) => {
    // skip hidden router
    if (!r.hidden) {
      const p = r.path.length > 0 && r.path[0] === '/' ? r.path : '/' + r.path
      const data = {
        path: !isHttp(r.path) ? getNormalPath(basePath + p) : r.path,
        title: [...prefixTitle],
        query: ''
      }
      if (r.meta && r.meta.title) {
        data.title = [...data.title, r.meta.title]
        if (r.redirect !== 'noRedirect') {
          // only push the routes with title
          // special case: need to exclude parent router without redirect
          res.push(data)
        }
      }

      if (r.query) {
        data.query = r.query
      }

      // recursive child routes
      if (r.children) {
        const tempRoutes = generateRoutes(r.children, data.path, data.title)
        if (tempRoutes.length >= 1) {
          res = [...res, ...tempRoutes]
        }
      }
    }
  })
  return res
}
const querySearch = (query) => {
  if (query !== '') {
    options.value = fuse.value.search(query)
  } else {
    options.value = []
  }
}

onMounted(() => {
  searchPool.value = generateRoutes(routes.value)
})

// watchEffect(() => {
//     searchPool.value = generateRoutes(routes.value)
// })

watch(show, (value) => {
  if (value) {
    document.body.addEventListener('click', close)
  } else {
    document.body.removeEventListener('click', close)
  }
})

watch(searchPool, (list) => {
  initFuse(list)
})
</script>

<style lang="scss" scoped>
.header-search {
  font-size: 0 !important;

  .search-icon {
    cursor: pointer;
    font-size: 18px;
    vertical-align: middle;
  }

  .header-search-select {
    font-size: 18px;
    transition: width 0.2s;
    width: 0;
    overflow: hidden;
    background: transparent;
    border-radius: 0;
    display: inline-block;
    vertical-align: middle;

    :deep(.el-input__inner) {
      border-radius: 0;
      border: 0;
      padding-left: 0;
      padding-right: 0;
      box-shadow: none !important;
      border-bottom: 1px solid #d9d9d9;
      vertical-align: middle;
    }
  }

  &.show {
    .header-search-select {
      width: 210px;
      margin-left: 10px;
    }
  }
}
</style>
