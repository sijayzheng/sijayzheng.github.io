// 数据字典

export const commonApi = {
    // 查询字典数据
    listDictDataOptions(code) {
        return request({
            url: '/common/listDictDataOptions',
            method: 'get',
            params: {code: code},
        })
    },
    // 查询枚举数据
    listEnumDataOptions(enumClass) {
        return request({
            url: '/common/listEnumDataOptions',
            method: 'get',
            params: {enumClass: enumClass},
        })
    },
    // 查询表数据数据
    listTableDataOptions(tableName) {
        return request({
            url: '/common/listTableDataOptions',
            method: 'get',
            params: {tableName: tableName},
        })
    },
    // 查询父类字段
    listSuperClassField(superClassName) {
        return request({
            url: '/common/listSuperClassField',
            method: 'get',
            params: {superClassName: superClassName},
        })
    }
}
