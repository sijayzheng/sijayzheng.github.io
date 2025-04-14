export {}
declare module 'axios' {
  interface AxiosResponse<T = any> {
    data?: T
    code?: number
    message?: string
    rows?: T[]
    total?: number
  }
}
