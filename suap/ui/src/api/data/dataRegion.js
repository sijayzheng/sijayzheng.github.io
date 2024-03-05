// 行政区划数据
export const dataRegionApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/dataRegion/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/dataRegion/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/dataRegion/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/dataRegion/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/dataRegion/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/dataRegion/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/dataRegion/downloadTemplate', {}, '行政区划数据_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/dataRegion/export', {}, '行政区划数据.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/dataRegion/import',
            method: 'post',
            data: data
        })
    }
}
