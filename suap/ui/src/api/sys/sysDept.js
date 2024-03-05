// 部门信息
export const sysDeptApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: '/sysDept/getById',
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: '/sysDept/listAll',
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/sysDept/page',
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: '/sysDept/add',
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: '/sysDept/update',
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/sysDept/remove',
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download('/sysDept/downloadTemplate', {}, '部门信息_数据模板.xlsx')
    },
    // 导出数据
    exportData() {
        download('/sysDept/export', {}, '部门信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: '/sysDept/import',
            method: 'post',
            data: data
        })
    }
}
