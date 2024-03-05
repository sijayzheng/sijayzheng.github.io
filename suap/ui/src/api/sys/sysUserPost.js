// 用户岗位
export const sysUserPostApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysUserPost/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysUserPost/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysUserPost/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysUserPost/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysUserPost/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysUserPost/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysUserPost/downloadTemplate', {}, '用户岗位_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysUserPost/export', {}, '用户岗位.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysUserPost/import',
            method: 'post',
            data: data
        })
    }
}
