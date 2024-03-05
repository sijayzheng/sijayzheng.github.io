// 岗位信息
export const sysPostApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysPost/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysPost/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysPost/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysPost/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysPost/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysPost/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysPost/downloadTemplate', {}, '岗位信息_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysPost/export', {}, '岗位信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysPost/import',
            method: 'post',
            data: data
        })
    }
}
