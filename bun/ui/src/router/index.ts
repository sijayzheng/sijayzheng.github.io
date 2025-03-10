import type {RouteLocationRaw, RouteRecordRaw} from 'vue-router'
import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/Layout.vue'

export const staticRoutes: RouteRecordRaw[] = [
  // {
  //   path: '/redirect',
  //   component: Layout,
  //   meta: {
  //     hidden: true,
  //   },
  //   children: [
  //     {
  //       path: '/redirect/:path(.*)',
  //       component: () => import('@/components/Redirect.vue')
  //     }
  //   ]
  // },
  {
    path: '/login',
    component: () => import('@/views/Login.vue'),
    meta: {
      hidden: true,
    },
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/components/404.vue'),
    meta: {
      hidden: true,
    },
  },
  {
    path: '/401',
    component: () => import('@/components/401.vue'),
    meta: {
      hidden: true,
    },
  },
  {
    path: '',
    component: Layout,
    redirect: '/index',
    children: [
      {
        path: '/index',
        component: () => import('@/views/index.vue'),
        name: 'Index',
        meta: {title: '首页', icon: 'dashboard', affix: true},
      },
    ],
  },
  //
  // {
  //   name: 'Config1849348904518836226',
  //   path: '/config',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '配置管理',
  //     icon: 'category',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'User100',
  //       path: 'user', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '用户管理',
  //         icon: 'user',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'OaFlow1793538741356142593',
  //       path: 'oaFlow', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '数据映射',
  //         icon: '#',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Role101',
  //       path: 'role', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '角色管理',
  //         icon: 'peoples',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // },
  // {
  //   name: 'System1',
  //   path: '/system',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '系统管理',
  //     icon: 'system',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'Menu102',
  //       path: 'menu', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '菜单管理',
  //         icon: 'tree-table',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Dept103',
  //       path: 'dept', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '部门管理',
  //         icon: 'tree',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Post104',
  //       path: 'post', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '岗位管理',
  //         icon: 'post',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Dict105',
  //       path: 'dict', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '字典管理',
  //         icon: 'dict',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Config106',
  //       path: 'config', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '参数设置',
  //         icon: 'edit',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Notice107',
  //       path: 'notice', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '通知公告',
  //         icon: 'message',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Log108',
  //       path: 'log',
  //       redirect: 'noRedirect',
  //       component: Layout,
  //       meta: {
  //         hidden: false,
  //         title: '日志管理',
  //         icon: 'log',
  //         noCache: false,
  //       },
  //       children: [
  //         {
  //           name: 'Operlog500',
  //           path: 'operlog', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '操作日志',
  //             icon: 'form',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'Logininfor501',
  //           path: 'logininfor', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '登录日志',
  //             icon: 'logininfor',
  //             noCache: false,
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       name: 'Online109',
  //       path: 'online', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '在线用户',
  //         icon: 'online',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Cache113',
  //       path: 'cache', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '缓存监控',
  //         icon: 'redis',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Oss118',
  //       path: 'oss', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '文件管理',
  //         icon: 'upload',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Client123',
  //       path: 'client', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '客户端管理',
  //         icon: 'international',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // },
  // {
  //   name: 'Tenant6',
  //   path: '/tenant',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '租户管理',
  //     icon: 'chart',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'Tenant121',
  //       path: 'tenant', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '租户管理',
  //         icon: 'list',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'TenantPackage122',
  //       path: 'tenantPackage', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '租户套餐管理',
  //         icon: 'form',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // },
  // {
  //   name: 'Monitor2',
  //   path: '/monitor',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '系统监控',
  //     icon: 'monitor',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'Admin117',
  //       path: 'Admin', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: 'Admin监控',
  //         icon: 'dashboard',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Snailjob120',
  //       path: 'snailjob', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '任务调度中心',
  //         icon: 'job',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // },
  // {
  //   name: 'Tool3',
  //   path: '/tool',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '系统工具',
  //     icon: 'tool',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'Gen115',
  //       path: 'gen', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '代码生成',
  //         icon: 'code',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // },
  // {
  //   name: 'Https://gitee.com/dromara/RuoYi-Vue-Plus4',
  //   path: '/https://gitee.com/dromara/RuoYi-Vue-Plus',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: 'PLUS官网',
  //     icon: 'guide',
  //     noCache: false,
  //     link: 'https://gitee.com/dromara/RuoYi-Vue-Plus'
  //   }
  // },
  // {
  //   name: 'Demo5',
  //   path: '/demo',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '测试菜单',
  //     icon: 'star',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'Tree1506',
  //       path: 'tree', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '测试树表',
  //         icon: '#',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Demo1500',
  //       path: 'demo', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '测试单表',
  //         icon: '#',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // },
  // {
  //   name: 'Oa1793475094898528258',
  //   path: '/oa',
  //   redirect: 'noRedirect',
  //   component: Layout,
  //   meta: {
  //     hidden: false,
  //     title: '合同管理',
  //     icon: 'chart',
  //     noCache: false,
  //   },
  //   children: [
  //     {
  //       name: 'OaIntegrateLedger1793477646889242625',
  //       path: 'oaIntegrateLedger', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '财务台账',
  //         icon: '#',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'Contract1849657585382948866',
  //       path: 'contract', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '合同台账',
  //         icon: '',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'AlertRemind1798621164997607426',
  //       path: 'alertRemind',
  //       redirect: 'noRedirect',
  //       component: Layout,
  //       meta: {
  //         hidden: false,
  //         title: '预警信息',
  //         icon: '#',
  //         noCache: false,
  //       },
  //       children: [
  //         {
  //           name: 'NetProfitMargin1846925610712772609',
  //           path: 'netProfitMargin', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '净利润偏低',
  //             icon: '',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'BusinessFees1846926502996422658',
  //           path: 'businessFees', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '商务费超标',
  //             icon: '',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'WarrantyExpired1846926205246976001',
  //           path: 'warrantyExpired', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '质保金到期',
  //             icon: '',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'OaDataErrorInfo1846570449089044481',
  //           path: 'oaDataErrorInfo', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '数据录入错',
  //             icon: '#',
  //             noCache: false,
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       name: 'OaBusinessMetric1855804785206128650',
  //       path: 'oaBusinessMetric',
  //       redirect: 'noRedirect',
  //       component: Layout,
  //       meta: {
  //         hidden: false,
  //         title: '经营指标',
  //         icon: '#',
  //         noCache: false,
  //       },
  //       children: [
  //         {
  //           name: 'Cycle1855804785206128651',
  //           path: 'cycle', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '整体情况',
  //             icon: '',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'Center1855804785206128655',
  //           path: 'center', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '成本中心',
  //             icon: '',
  //             noCache: false,
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       name: 'Period1855804785206128660',
  //       path: 'period',
  //       redirect: 'noRedirect',
  //       component: Layout,
  //       meta: {
  //         hidden: false,
  //         title: '同期数据',
  //         icon: '#',
  //         noCache: false,
  //       },
  //       children: [
  //         {
  //           name: 'Cycle1855804785206128661',
  //           path: 'cycle', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '整体情况',
  //             icon: '',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'Center1855804785206128665',
  //           path: 'center', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '成本中心',
  //             icon: '',
  //             noCache: false,
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       name: 'Project1861299264478453762',
  //       path: 'project',
  //       redirect: 'noRedirect',
  //       component: Layout,
  //       meta: {
  //         hidden: false,
  //         title: '项目信息',
  //         icon: '',
  //         noCache: false,
  //       },
  //       children: [
  //         {
  //           name: 'OaContractTypeStats1861296508371947521',
  //           path: 'oaContractTypeStats', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '整体情况',
  //             icon: '#',
  //             noCache: false,
  //           }
  //         },
  //         {
  //           name: 'OaContractTypeStatsCenter1861296508371947531',
  //           path: 'oaContractTypeStatsCenter', component: () => import('@/components/404.vue'),
  //           meta: {
  //             hidden: false,
  //             title: '成本中心',
  //             icon: '#',
  //             noCache: false,
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       name: 'OaFrameworkContract1798966513771708417',
  //       path: 'oaFrameworkContract', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '框架匹配',
  //         icon: '#',
  //         noCache: false,
  //       }
  //     },
  //     {
  //       name: 'OaPurchaseContract1798966511745859586',
  //       path: 'oaPurchaseContract', component: () => import('@/components/404.vue'),
  //       meta: {
  //         hidden: false,
  //         title: '综合采购',
  //         icon: '#',
  //         noCache: false,
  //       }
  //     }
  //   ]
  // }
]
export const whiteList = ['/login']

const router = createRouter({
  history: createWebHistory(),
  routes: staticRoutes,
  // 刷新时，滚动条位置还原
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return {top: 0}
    }
  },
})

router.beforeEach(async (to, from, next) => {
  loading.start()
  if (getToken()) {
    to.meta.title && useSettingStore().setTitle(to.meta.title)
    /* has token */
    if (to.path === '/login') {
      next({path: '/'})
    } else if (whiteList.includes(to.path as string)) {
      next()
    } else {
      const userStore = useUserStore()
      if (userStore.roles.length === 0) {
        userStore.getInfo()
          .then(async (res) => {
            if (res.isNew) {
              next({path: '/user/profile'})
              ElMessage({
                showClose: true,
                message: '您是首次登录，请先修改密码',
                type: 'warning',
                duration: 0,
              })
            } else {
              userStore.setReLogin(false)
              const accessRoutes = await usePermissionStore().generateRoutes()
              // 根据roles权限生成可访问的路由表
              accessRoutes.forEach((route) => {
                if (!isHttp(route.path)) {
                  router.addRoute(route) // 动态添加可访问路由表
                }
              })
              next({path: to.path, replace: true, params: to.params, query: to.query, hash: to.hash, name: to.name as string} as RouteLocationRaw)
            }
          })
          .catch((err) => {
            ElMessage.error(err)
            userStore.logout()
            next({path: '/'})
          })
      } else {
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.includes(to.path as string)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      const redirect = encodeURIComponent(to.fullPath || '/')
      next(`/login?redirect=${redirect}`) // 否则全部重定向到登录页
    }
  }
})

router.afterEach(() => {
  loading.stop()
})

export default router
