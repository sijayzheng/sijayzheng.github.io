// 系统配置
export const sysConfigApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysConfig/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysConfig/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysConfig/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysConfig/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysConfig/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysConfig/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysConfig/downloadTemplate', {}, '系统配置_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysConfig/export', {}, '系统配置.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysConfig/import',
            method: 'post',
            data: data
        })
    }
}
