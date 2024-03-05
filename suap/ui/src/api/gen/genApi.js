// 代码生成

export const genApi = {
    // 根据id查询
    getGenProperties() {
        return request({
            url: '/gen/getGenProperties',
            method: 'get',
        })
    },
    save(data) {
        return request({
            url: '/gen/save',
            method: 'post',
            data: data
        })
    },
    update(data) {
        return request({
            url: '/gen/update',
            method: 'post',
            data: data
        })
    },
    preview(id) {
        return request({
            url: '/gen/preview',
            method: 'get',
            params: {id: id}
        })
    },
    download(id) {
        download("/gen/download", {id: id}, '代码.zip');
    },

    // 根据id查询
    getTableById(id) {
        return request({
            url: '/gen/getTableById',
            method: 'get',
            params: {id: id},
        })
    },
    listTable() {
        return request({
            url: '/gen/listTable',
            method: 'get',
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: '/gen/page',
            method: 'get',
            params: queryParam,
        })
    },
    // 删除
    remove(data) {
        return request({
            url: '/gen/remove',
            method: 'post',
            data: data,
        })
    },

    // 获取表的列信息
    listTableColumns(tableName, superClass) {
        return request({
            url: '/gen/listTableColumns',
            method: 'get',
            params: {
                tableName: tableName,
                superClass: superClass
            },
        })
    },
    listColumnByTableId(tableId) {
        return request({
            url: '/gen/listColumnByTableId',
            method: 'get',
            params: {tableId: tableId},
        })
    },
    generate(id) {
        return request({
            url: '/gen/generate',
            method: 'get',
            params: {id: id}
        })
    }
}