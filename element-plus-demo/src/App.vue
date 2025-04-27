<template>
  <div style="display: flex">
    <div style="text-align: center">
      <el-button circle type="primary" @click="value=value<100?0:value-100">
        <el-icon>
          <arrow-left-bold/>
        </el-icon>
      </el-button>
    </div>
    <div style="width: 1600px">
      <el-scrollbar ref="scrollbarRef" always height="32px" @scroll="scroll">
        <div ref="areaBarRef" class="area-bar">
          <div v-for="item in 20" :key="item" class="area-item">
            Item {{ item }}
          </div>
        </div>
      </el-scrollbar>
    </div>
    <div style="text-align: center">
      <el-button circle type="primary" @click="value=value>max-100?max:value+100">
        <el-icon>
          <arrow-right-bold/>
        </el-icon>
      </el-button>
    </div>
  </div>
</template>

<script setup>
const max = ref(0)
const value = ref(0)
const areaBarContainerBar = ref()
const areaBarRef = ref()
const scrollbarRef = ref()

onMounted(() => {
  max.value = areaBarRef.value.clientWidth - 1600
})
watch(value, (val) => {
  scrollbarRef.value.setScrollLeft(val)
})

const inputSlider = (value) => {
  scrollbarRef.value.setScrollLeft(value)
}
const scroll = ({scrollLeft}) => {
  value.value = scrollLeft
}

</script>
<style>
.area-bar {
  display: flex;
  width: fit-content;
  transition: transform 0.3s ease;
}

.area-bar .area-item {
  min-width: 100px;
  height: 24px;
  box-sizing: border-box;
  margin: 0 5px;
  border: 1px solid #ccc;
  background-color: #fff;
}

.el-icon {
  font-size: 16px;
}
</style>