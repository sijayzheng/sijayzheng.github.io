## datagrid 动态加载列

动态加载列即表头的动态写入

页面代码

```html
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="./jquery.min.js"></script>
    <script src="./easyui.min.js"></script>
    <title>Document</title>
  </head>
  <body>
    <table id="dg"></table>
  </body>
</html>
```

js 代码

```javascript
$(function () {
  $.ajax({
    url: 'scoresData.json',
    type: 'post', // 数据发送方式
    dataType: 'json', // 接受数据格式
    success: function (data) {
      //通过ajax从后台获取学生信息、课程信息、成绩信息
      var students = data.students
      var scores = data.scores
      var subjects = data.subject
      var columns = new Array()
      var col = {
        title: '班级',
        field: 'grade',
        width: '100px',
      }
      columns.push(col)
      var col = {
        title: '姓名',
        field: 'name',
        width: '100px',
      }
      columns.push(col)
      $.each(subjects, function (i, n) {
        var col = {
          title: n.names,
          field: n.names,
          width: '80px',
        }
        columns.push(col)
      }) //动态写入科目表头
      var col = {
        title: '总成绩',
        field: 'scoresum',
        width: '100px',
      }
      columns.push(col)
      $('#dg').datagrid({
        singleSelect: true,
        pagination: true,
        striped: true,
        pageSize: 10,
        pageList: [5, 10, 15],
        columns: [columns],
        toolbar: '#toolbar',
      })
      var item = '{"total":' + students.length + ',"rows":['
      $.each(students, function (i, n) {
        var stuname = n[1]
        item += '{"grade":"' + n[0] + '",'
        item += '"name":"' + stuname + '",'
        var scoresum = 0
        $.each(subjects, function (j, m) {
          var subname = m.names
          item += '"' + subname + '":"'
          var score = 0
          $.each(scores, function (k, p) {
            if (stuname == p[0] && subname == p[1]) {
              score = p[2]
              return true
            }
          })
          item += score + '",'
          scoresum += score
          console.log(scoresum)
        })
        item += '"scoresum":"' + scoresum + '"'
        item += '},'
      }) //通过循环将学生、课程、成绩整合为一条json字符串
      item = item.substring(0, item.length - 1)
      item += ']}'
      itemjson = JSON.parse(item) //将json字符串转为json数组
      $('#dg')
        .datagrid({
          loadFilter: pagerFilter,
        })
        .datagrid('loadData', itemjson) //加载本地数据，并实现客户端分页(假分页)
    }, //success end
  })
})
function pagerFilter(data) {
  if (typeof data.length == 'number' && typeof data.splice == 'function') {
    // is array
    data = {
      total: data.length,
      rows: data,
    }
  }
  var dg = $(this)
  var opts = dg.datagrid('options')
  var pager = dg.datagrid('getPager')
  pager.pagination({
    onSelectPage: function (pageNum, pageSize) {
      opts.pageNumber = pageNum
      opts.pageSize = pageSize
      pager.pagination('refresh', {
        pageNumber: pageNum,
        pageSize: pageSize,
      })
      dg.datagrid('loadData', data)
    },
  })
  if (!data.originalRows) {
    data.originalRows = data.rows
  }
  var start = (opts.pageNumber - 1) * parseInt(opts.pageSize)
  var end = start + parseInt(opts.pageSize)
  data.rows = data.originalRows.slice(start, end)
  return data
}
```

## 本地化中文乱码

easyui 组件添加中文本地化之后组件内容乱码，修改本地化插件编码格式
