interface LoginParam {
  module: number
  username: string
  password: string
  code?: string
  uuid?: string
  rememberMe?: boolean
}

interface IdEntity {
  id?: number
}

interface BaseEntity extends IdEntity {
  createDept?: string | number
  createBy?: string | number
  createTime?: string
  updateBy?: string | number
  updateTime?: string
}

/**
 * 分页查询参数
 */
interface PageQuery {
  pageNum?: number
  pageSize?: number
  field?: string
  asc?: boolean
}

interface Option {
  label: string
  value: string | number
  children: Option[]
}
