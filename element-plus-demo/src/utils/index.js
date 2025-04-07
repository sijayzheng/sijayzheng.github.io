
let position = 0
const spanArr = []

export const getSpanArr = (data, key) => {
  spanArr.length = 0
  data.forEach((item, index) => {
    if (index === 0) {
      spanArr.push(1)
      position = 0
    } else {
      if (item[key] === data[index - 1][key]) {
        spanArr[position] += 1
        spanArr.push(0)
      } else {
        spanArr.push(1)
        position = index
      }
    }
  })
  return spanArr
}