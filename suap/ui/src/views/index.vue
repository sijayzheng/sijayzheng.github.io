<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="14">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
      <el-col :span="10">
        <el-card>
          <el-row :gutter="10" class="date-weather">
            <el-col :span="4" style="text-align: center">
              <div style="font-size: 36px">{{ today.cDay }}</div>
              <div>{{ today.ncWeek }}</div>
            </el-col>
            <el-col :span="4">
              <div>
                {{ `${today.gzYear} ${today.Animal}年` }}
              </div>
              <div>
                {{ `${today.IMonthCn}${today.IDayCn}` }}
              </div>
            </el-col>
            <el-col :span="16">
              <el-row :gutter="5" style="align-items: center">
                <el-col :span="8" style="text-align: center">
                  <img :src="`/src/assets/icons/weather/${currentWeather.wtIcon}.png`">
                  <div class="weather-temp-name">
                    {{ currentWeather.wtNm }}
                  </div>
                </el-col>

                <el-col :span="7" class="temps">
                  <div class="temp-c">
                    {{ `${currentWeather.wtTemp}℃` }}
                  </div>
                  <div class="temp-hl">
                    <div class="temp-h">
                      {{ `${todayWeather.wtTemp1}℃` }}
                    </div>
                    <div class="temp-l">
                      {{ `${todayWeather.wtTemp2}℃` }}
                    </div>
                  </div>
                </el-col>
                <el-col :span="9">
                  <div class="weather-humi">
                    {{ `湿度：${currentWeather.wtHumi}%` }}
                  </div>
                  <div class="weather-wind">
                    {{ `${currentWeather.wtWindNm} ${currentWeather.wtWinp}级` }}
                  </div>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
          <ElCalendar>
            <template #date-cell="{ data }">
              <CalendarCell :data="transDate(data.day)"/>
            </template>
          </ElCalendar>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script name="Index" setup>
import 'dayjs/locale/zh-cn'
import calendar from "js-calendar-converter";

const today = ref({
  "date": "2023-11-24",
  "lunarDate": "2023-10-12",
  "festival": null,
  "lunarFestival": null,
  "lYear": 2023,
  "lMonth": 10,
  "lDay": 12,
  "Animal": "兔",
  "IMonthCn": "十月",
  "IDayCn": "十二",
  "cYear": 2023,
  "cMonth": 11,
  "cDay": 24,
  "gzYear": "癸卯",
  "gzMonth": "癸亥",
  "gzDay": "丙戌",
  "isToday": true,
  "isLeap": false,
  "nWeek": 5,
  "ncWeek": "星期五",
  "isTerm": false,
  "Term": null,
})
const area = ref({})
const currentWeather = ref({})
const todayWeather = ref({})
const futureDay = ref({})
const futureHour = ref({})

onBeforeMount(() => {
  today.value = calendar.solar2lunar()
  // secretApi.listAllOfCurrentUser().then(secrets => {
  //   const secret = {}
  //   secrets.map(item => secret[item.code] = item.value)
  //   // axios.get('https://sapi.k780.com', {
  //   //   params: {
  //   //     app: 'weather.realtime',
  //   //     cityNm: '济南',
  //   //     ag: 'today,futureDay,lifeIndex,futureHour',
  //   //     appkey: secret['appKey'],
  //   //     sign: secret['sign'],
  //   //     format: 'json'
  //   //   }
  //   // }).then(res => {
  const res = {
    "data": {
      "success": "1",
    },
    "status": 200,
  }
  if (res.status === 200) {
    if (res.data.success === '1') {
      // const weather = res.data.result
      const weather = {
        "weaid": "299",
        "cityid": "101120101",
        "area_1": "山东",
        "area_2": "济南",
        "area_3": "城区",
        "realTime": {
          "week": "星期五",
          "wtId": "1",
          "wtNm": "晴",
          "wtIcon": "00",
          "wtTemp": "4",
          "wtHumi": "21",
          "wtWindId": "1",
          "wtWindNm": "东北风",
          "wtWinp": "2",
          "wtWins": "10",
          "wtAqi": "28",
          "wtVisibility": "30.00",
          "wtRainfall": "0.00",
          "wtPressurel": "1013"
        },
        "today": {
          "wtBlueSkyId": "1",
          "wtId1": "1",
          "wtId2": "3",
          "wtNm1": "晴",
          "wtNm2": "阴",
          "wtIcon1": "00",
          "wtIcon2": "02",
          "wtTemp1": "5",
          "wtTemp2": "-3",
          "wtWindId1": "0",
          "wtWindId2": "0",
          "wtWindNm1": "无持续风向",
          "wtWindNm2": "无持续风向",
          "wtWinpId1": "0",
          "wtWinpId2": "0",
          "wtWinpNm1": "小于3级",
          "wtWinpNm2": "小于3级",
          "wtSunr": "06:57",
          "wtSuns": "16:58",
          "lifeIndex": {
            "uv": {
              "liNo": "uv",
              "liNm": "紫外线指数",
              "liAttr": "中等",
              "liDese": "涂擦SPF大于15、PA+防晒护肤品。"
            },
            "ct": {
              "liNo": "ct",
              "liNm": "穿衣指数",
              "liAttr": "冷",
              "liDese": "建议着棉衣加羊毛衫等冬季服装。"
            },
            "xc": {
              "liNo": "xc",
              "liNm": "洗车指数",
              "liAttr": "较适宜",
              "liDese": "无雨且风力较小，易保持清洁度。"
            }
          }
        },
        "futureDay": [
          {
            "dateYmd": "2023-11-25",
            "week": "星期六",
            "wtBlueSkyId": "0",
            "wtId1": "8",
            "wtId2": "2",
            "wtNm1": "小雨",
            "wtNm2": "多云",
            "wtIcon1": "07",
            "wtIcon2": "01",
            "wtTemp1": "8",
            "wtTemp2": "2",
            "wtWindId1": "0",
            "wtWindId2": "0",
            "wtWindNm1": "无持续风向",
            "wtWindNm2": "无持续风向",
            "wtWinpId1": "0",
            "wtWinpId2": "0",
            "wtWinpNm1": "小于3级",
            "wtWinpNm2": "小于3级",
            "wtSunr": "06:58",
            "wtSuns": "16:57",
            "lifeIndex": {
              "uv": {
                "liNo": "uv",
                "liNm": "紫外线指数",
                "liAttr": "最弱",
                "liDese": "辐射弱，涂擦SPF8-12防晒护肤品。"
              },
              "ct": {
                "liNo": "ct",
                "liNm": "穿衣指数",
                "liAttr": "冷",
                "liDese": "建议着棉衣加羊毛衫等冬季服装。"
              },
              "xc": {
                "liNo": "xc",
                "liNm": "洗车指数",
                "liAttr": "不宜",
                "liDese": "有雨，雨水和泥水会弄脏爱车。"
              }
            }
          },
          {
            "dateYmd": "2023-11-26",
            "week": "星期日",
            "wtBlueSkyId": "1",
            "wtId1": "1",
            "wtId2": "1",
            "wtNm1": "晴",
            "wtNm2": "晴",
            "wtIcon1": "00",
            "wtIcon2": "00",
            "wtTemp1": "11",
            "wtTemp2": "3",
            "wtWindId1": "4",
            "wtWindId2": "4",
            "wtWindNm1": "南风",
            "wtWindNm2": "南风",
            "wtWinpId1": "1",
            "wtWinpId2": "1",
            "wtWinpNm1": "3-4级",
            "wtWinpNm2": "3-4级",
            "wtSunr": "06:59",
            "wtSuns": "16:57",
            "lifeIndex": {
              "uv": {
                "liNo": "uv",
                "liNm": "紫外线指数",
                "liAttr": "中等",
                "liDese": "涂擦SPF大于15、PA+防晒护肤品。"
              },
              "ct": {
                "liNo": "ct",
                "liNm": "穿衣指数",
                "liAttr": "较冷",
                "liDese": "建议着厚外套加毛衣等服装。"
              },
              "xc": {
                "liNo": "xc",
                "liNm": "洗车指数",
                "liAttr": "较不宜",
                "liDese": "风力较大，洗车后会蒙上灰尘。"
              }
            }
          },
          {
            "dateYmd": "2023-11-27",
            "week": "星期一",
            "wtBlueSkyId": "1",
            "wtId1": "1",
            "wtId2": "1",
            "wtNm1": "晴",
            "wtNm2": "晴",
            "wtIcon1": "00",
            "wtIcon2": "00",
            "wtTemp1": "12",
            "wtTemp2": "-3",
            "wtWindId1": "0",
            "wtWindId2": "0",
            "wtWindNm1": "无持续风向",
            "wtWindNm2": "无持续风向",
            "wtWinpId1": "0",
            "wtWinpId2": "0",
            "wtWinpNm1": "小于3级",
            "wtWinpNm2": "小于3级",
            "wtSunr": "07:00",
            "wtSuns": "16:57",
            "lifeIndex": {
              "uv": {
                "liNo": "uv",
                "liNm": "紫外线指数",
                "liAttr": "中等",
                "liDese": "涂擦SPF大于15、PA+防晒护肤品。"
              },
              "ct": {
                "liNo": "ct",
                "liNm": "穿衣指数",
                "liAttr": "较冷",
                "liDese": "建议着厚外套加毛衣等服装。"
              },
              "xc": {
                "liNo": "xc",
                "liNm": "洗车指数",
                "liAttr": "适宜",
                "liDese": "天气较好，适合擦洗汽车。"
              }
            }
          },
          {
            "dateYmd": "2023-11-28",
            "week": "星期二",
            "wtBlueSkyId": "1",
            "wtId1": "1",
            "wtId2": "1",
            "wtNm1": "晴",
            "wtNm2": "晴",
            "wtIcon1": "00",
            "wtIcon2": "00",
            "wtTemp1": "6",
            "wtTemp2": "0",
            "wtWindId1": "0",
            "wtWindId2": "0",
            "wtWindNm1": "无持续风向",
            "wtWindNm2": "无持续风向",
            "wtWinpId1": "0",
            "wtWinpId2": "0",
            "wtWinpNm1": "小于3级",
            "wtWinpNm2": "小于3级",
            "wtSunr": "07:01",
            "wtSuns": "16:56",
            "lifeIndex": {
              "uv": {
                "liNo": "uv",
                "liNm": "紫外线指数",
                "liAttr": "中等",
                "liDese": "涂擦SPF大于15、PA+防晒护肤品。"
              },
              "ct": {
                "liNo": "ct",
                "liNm": "穿衣指数",
                "liAttr": "冷",
                "liDese": "建议着棉衣加羊毛衫等冬季服装。"
              },
              "xc": {
                "liNo": "xc",
                "liNm": "洗车指数",
                "liAttr": "适宜",
                "liDese": "天气较好，适合擦洗汽车。"
              }
            }
          },
          {
            "dateYmd": "2023-11-29",
            "week": "星期三",
            "wtBlueSkyId": "1",
            "wtId1": "1",
            "wtId2": "1",
            "wtNm1": "晴",
            "wtNm2": "晴",
            "wtIcon1": "00",
            "wtIcon2": "00",
            "wtTemp1": "7",
            "wtTemp2": "-2",
            "wtWindId1": "0",
            "wtWindId2": "0",
            "wtWindNm1": "无持续风向",
            "wtWindNm2": "无持续风向",
            "wtWinpId1": "0",
            "wtWinpId2": "0",
            "wtWinpNm1": "小于3级",
            "wtWinpNm2": "小于3级",
            "wtSunr": "07:02",
            "wtSuns": "16:56",
            "lifeIndex": {
              "uv": {
                "liNo": "uv",
                "liNm": "紫外线指数",
                "liAttr": "中等",
                "liDese": "涂擦SPF大于15、PA+防晒护肤品。"
              },
              "ct": {
                "liNo": "ct",
                "liNm": "穿衣指数",
                "liAttr": "冷",
                "liDese": "建议着棉衣加羊毛衫等冬季服装。"
              },
              "xc": {
                "liNo": "xc",
                "liNm": "洗车指数",
                "liAttr": "适宜",
                "liDese": "天气较好，适合擦洗汽车。"
              }
            }
          },
          {
            "dateYmd": "2023-11-30",
            "week": "星期四",
            "wtBlueSkyId": "1",
            "wtId1": "1",
            "wtId2": "1",
            "wtNm1": "晴",
            "wtNm2": "晴",
            "wtIcon1": "00",
            "wtIcon2": "00",
            "wtTemp1": "3",
            "wtTemp2": "-4",
            "wtWindId1": "0",
            "wtWindId2": "0",
            "wtWindNm1": "无持续风向",
            "wtWindNm2": "无持续风向",
            "wtWinpId1": "0",
            "wtWinpId2": "0",
            "wtWinpNm1": "小于3级",
            "wtWinpNm2": "小于3级",
            "wtSunr": "07:03",
            "wtSuns": "16:56",
            "lifeIndex": {
              "uv": {
                "liNo": "uv",
                "liNm": "紫外线指数",
                "liAttr": "中等",
                "liDese": "涂擦SPF大于15、PA+防晒护肤品。"
              },
              "ct": {
                "liNo": "ct",
                "liNm": "穿衣指数",
                "liAttr": "寒冷",
                "liDese": "建议着厚羽绒服等隆冬服装。"
              },
              "xc": {
                "liNo": "xc",
                "liNm": "洗车指数",
                "liAttr": "适宜",
                "liDese": "天气较好，适合擦洗汽车。"
              }
            }
          }
        ],
        "futureHour": [
          {
            "dateYmdh": "2023-11-24 15:00:00",
            "wtId": "1",
            "wtNm": "晴",
            "wtIcon": "00",
            "wtTemp": "3",
            "wtWindId": "1",
            "wtWindNm": "东北风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 16:00:00",
            "wtId": "1",
            "wtNm": "晴",
            "wtIcon": "00",
            "wtTemp": "2",
            "wtWindId": "1",
            "wtWindNm": "东北风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 17:00:00",
            "wtId": "1",
            "wtNm": "晴",
            "wtIcon": "00",
            "wtTemp": "1",
            "wtWindId": "1",
            "wtWindNm": "东北风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 18:00:00",
            "wtId": "1",
            "wtNm": "晴",
            "wtIcon": "00",
            "wtTemp": "0",
            "wtWindId": "1",
            "wtWindNm": "东北风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 19:00:00",
            "wtId": "1",
            "wtNm": "晴",
            "wtIcon": "00",
            "wtTemp": "-1",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 20:00:00",
            "wtId": "1",
            "wtNm": "晴",
            "wtIcon": "00",
            "wtTemp": "-2",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 21:00:00",
            "wtId": "2",
            "wtNm": "多云",
            "wtIcon": "01",
            "wtTemp": "-2",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 22:00:00",
            "wtId": "2",
            "wtNm": "多云",
            "wtIcon": "01",
            "wtTemp": "-2",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-24 23:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "-2",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 00:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "-2",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 01:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "-2",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 02:00:00",
            "wtId": "2",
            "wtNm": "多云",
            "wtIcon": "01",
            "wtTemp": "-2",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 03:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "-1",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 04:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "-1",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 05:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "-1",
            "wtWindId": "2",
            "wtWindNm": "东风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 06:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "0",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 07:00:00",
            "wtId": "3",
            "wtNm": "阴",
            "wtIcon": "02",
            "wtTemp": "0",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 08:00:00",
            "wtId": "2",
            "wtNm": "多云",
            "wtIcon": "01",
            "wtTemp": "0",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 09:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "2",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 10:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "4",
            "wtWindId": "3",
            "wtWindNm": "东南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 11:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "5",
            "wtWindId": "4",
            "wtWindNm": "南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 12:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "6",
            "wtWindId": "4",
            "wtWindNm": "南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 13:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "7",
            "wtWindId": "4",
            "wtWindNm": "南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 14:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "7",
            "wtWindId": "4",
            "wtWindNm": "南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          },
          {
            "dateYmdh": "2023-11-25 15:00:00",
            "wtId": "8",
            "wtNm": "小雨",
            "wtIcon": "07",
            "wtTemp": "7",
            "wtWindId": "4",
            "wtWindNm": "南风",
            "wtWinpId": "0",
            "wtWinpNm": "小于3级"
          }
        ]
      }
      area.value = {
        area1: weather.area_1,
        area2: weather.area_2,
        area3: weather.area_3,
      }
      currentWeather.value = weather.realTime
      todayWeather.value = weather.today
      futureDay.value = weather.futureDay
      futureHour.value = weather.futureHour
    } else {
      ElMessage.warning('天气信息获取失败：' + res.data.msg)
    }
  }
  //   // })
  // })
})

const transDate = (str) => {
  let [year, month, day] = str.split('-')
  return calendar.solar2lunar(year, month, day)
}
const parseAqi = (aqi) => {
  if (aqi < 0) {
    return {
      aqi: aqi,
      level: '',
      desc: '',
      notice: ''
    }
  } else if (aqi < 51) {
    return {
      aqi: aqi,
      level: '1级',
      desc: '优',
      notice: '参加户外活动呼吸清新空气'
    }
  } else if (aqi < 101) {
    return {
      aqi: aqi,
      level: '2级',
      desc: '良',
      notice: '可以正常进行室外活动'
    }
  } else if (aqi < 151) {
    return {
      aqi: aqi,
      level: '3级',
      desc: '轻度污染',
      notice: '敏感人群减少体力消耗大的户外活动'
    }
  } else if (aqi < 201) {
    return {
      aqi: aqi,
      level: '4级',
      desc: '中度污染',
      notice: '对敏感人群影响较大'
    }
  } else if (aqi < 301) {
    return {
      aqi: aqi,
      level: '5级',
      desc: '重度污染',
      notice: '所有人应适当减少室外活动'
    }
  } else {
    return {
      aqi: aqi,
      level: '6级',
      desc: '严重污染',
      notice: '尽量不要留在室外'
    }
  }
}
</script>

<style scoped>
.date-weather {
  align-items: center;

  .temps {
    display: flex;
    align-items: center;

    .temp-c {
      font-size: 28px;
    }

    .temp-hl {
      .temp-h {
        margin-left: 5px;
        color: #ff8a34;
      }

      .temp-l {
        margin-left: 5px;
        color: #0984ff;
      }
    }
  }

  .weather-humi, .weather-wind {
  }
}
</style>
