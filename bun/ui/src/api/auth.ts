import type {LoginParam} from '@/api/types'
import {hola} from '@/utils/request'

export const authApi = {
  login: (data: LoginParam) => {
    return hola('get', '/login', data)
  },
  logout: () => {

  },
}
