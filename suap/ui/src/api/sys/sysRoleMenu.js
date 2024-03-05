// 角色菜单
export const sysRoleMenuApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysRoleMenu/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysRoleMenu/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysRoleMenu/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysRoleMenu/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysRoleMenu/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysRoleMenu/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysRoleMenu/downloadTemplate', {}, '角色菜单_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysRoleMenu/export', {}, '角色菜单.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysRoleMenu/import',
            method: 'post',
            data: data
        })
    }
}
