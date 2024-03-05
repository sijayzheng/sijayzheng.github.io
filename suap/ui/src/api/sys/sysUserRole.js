// 用户角色
export const sysUserRoleApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysUserRole/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysUserRole/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysUserRole/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysUserRole/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysUserRole/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysUserRole/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysUserRole/downloadTemplate', {}, '用户角色_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysUserRole/export', {}, '用户角色.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysUserRole/import',
            method: 'post',
            data: data
        })
    }
}
