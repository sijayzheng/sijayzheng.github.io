// 登录日志
export const logLoginApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/logLogin/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/logLogin/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/logLogin/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/logLogin/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/logLogin/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/logLogin/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/logLogin/downloadTemplate', {}, '登录日志_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/logLogin/export', {}, '登录日志.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/logLogin/import',
            method: 'post',
            data: data
        })
    }
}
