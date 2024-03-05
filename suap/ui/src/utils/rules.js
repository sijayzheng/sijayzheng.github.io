export const relus = {
    required: (field, trigger = 'blur') => {
        return {
            required: true,
            message: field + '不能为空',
            trigger: trigger
        }
    },
    maxLength: (field, max) => {
        return {
            message: field + '长度最大为' + max,
            trigger: "change",
            max: max
        }
    },
    length: (field, min = 0, max) => {
        return {
            message: field + `长度在${min}到${max}之间`,
            trigger: "change",
            max: max
        }
    },
    max: (field, value) => {
        return {
            message: field + '最大值为' + value,
            trigger: "change",
            max: max
        }
    },
    date: () => {
    }

}