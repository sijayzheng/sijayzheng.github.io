import type {RouteMeta as VRouteMeta} from 'vue-router'

declare module 'vue-router' {
  interface RouteMeta extends VRouteMeta {
    title?: string
    icon?: string
    hidden?: boolean
    cache?: boolean
    link?: string
    query?: string
  }

}

export {}
