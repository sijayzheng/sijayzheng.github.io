// 数据字典
export const dataDictApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/dataDict/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/dataDict/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/dataDict/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/dataDict/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/dataDict/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/dataDict/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/dataDict/downloadTemplate', {}, '数据字典_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/dataDict/export', {}, '数据字典.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/dataDict/import',
            method: 'post',
            data: data
        })
    }
}
