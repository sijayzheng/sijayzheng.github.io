## 一天连续的二十四小时的时间轴

```javascript
var data1 = [
  ['2016-08-09 00:05:22', '17'],
  ['2016-08-09 00:15:23', '34'],
  ['2016-08-09 01:35:23', '87'],
  ['2016-08-09 06:55:29', '25'],
  ['2016-08-09 06:55:30', '36'],
  ['2016-08-09 09:35:31', '65'],
  ['2016-08-09 06:07:52', '3'],
]
var data2 = [
  ['2016-08-09 10:05:22', '15'],
  ['2016-08-09 10:15:23', '24'],
  ['2016-08-09 11:35:23', '37'],
  ['2016-08-09 16:55:29', '73'],
  ['2016-08-09 16:55:30', '56'],
  ['2016-08-09 19:35:31', '74'],
  ['2016-08-09 16:07:52', '97'],
]
var date = '2016-08-09'
var myChart = echarts.init(document.getElementById('chart'))
myChart.setOption({
  legend: {
    data: ['name1', 'name2'],
    left: 'center',
  },
  tooltip: {
    formatter: function (params) {
      return (
        params.seriesName +
        '<br><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' +
        params.color +
        ';"></span>' +
        params.value[0].substring(11, params.value[0].length) +
        ',  ' +
        params.value[1] +
        '%'
      )
    },
  },
  xAxis: [
    {
      type: 'time',
      min: date + ' ' + '00:00:00',
      max: date + ' ' + '23:59:59',
      splitNumber: 23,
      axisLabel: {
        formatter: function (value) {
          var date = new Date(value)
          var h = date.getHours()
          var m = date.getMinutes()
          h = h < 10 ? '0' + h : h
          m = m === 0 ? m + '0' : m
          var time = h + ':' + m
          return time
        },
      },
    },
  ],
  yAxis: [
    {
      type: 'value',
      min: 0,
      max: 100,
      interval: 10,
    },
  ],
  series: [
    {
      name: 'name1',
      type: 'scatter',
      data: data1,
    },
    {
      name: 'name2',
      type: 'scatter',
      data: data2,
    },
  ],
})
```
