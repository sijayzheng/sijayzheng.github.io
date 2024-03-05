// 业务日志
export const logBusinessApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/logBusiness/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/logBusiness/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/logBusiness/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/logBusiness/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/logBusiness/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/logBusiness/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/logBusiness/downloadTemplate', {}, '业务日志_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/logBusiness/export', {}, '业务日志.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/logBusiness/import',
            method: 'post',
            data: data
        })
    }
}
