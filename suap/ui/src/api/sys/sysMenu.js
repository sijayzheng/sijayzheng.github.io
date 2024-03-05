// 菜单信息
export const sysMenuApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysMenu/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysMenu/listAll',
            method: 'get'
        })
    },
    //查询树形数据
    listTree() {
        return request({
            url: '/sysMenu/listTree',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysMenu/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysMenu/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysMenu/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysMenu/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysMenu/downloadTemplate', {}, '菜单信息_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysMenu/export', {}, '菜单信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysMenu/import',
            method: 'post',
            data: data
        })
    }
}
