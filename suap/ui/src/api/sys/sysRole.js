// 角色信息
export const sysRoleApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysRole/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysRole/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysRole/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysRole/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysRole/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysRole/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysRole/downloadTemplate', {}, '角色信息_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysRole/export', {}, '角色信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysRole/import',
            method: 'post',
            data: data
        })
    }
}
