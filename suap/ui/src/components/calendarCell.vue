<template>
  <div class="date-cell">
    <div class="date-cell-day">
      <div class="date-cell-gday">
        {{ date.cDay }}
      </div>
      <div>
        {{ date.lDay === 1 ? date.IMonthCn : date.IDayCn }}
      </div>
    </div>
    <div v-if="date.Term" class="festival">
      {{ date.Term }}
    </div>
    <div v-if="date.festival" class="festival">
      {{ date.festival }}
    </div>
    <div v-if="date.lunarFestival" class="festival">
      {{ date.lunarFestival }}
    </div>
  </div>
</template>
<script name="CalendarCell" setup>


;

const props = defineProps({
  data: {
    type: Object,
    default: {}
  }
})
const date = ref(props.data)
watch(() => props.data, (newValue, oldValue) => {
  date.value = newValue
})
</script>

<style scoped>
.date-cell {
  font-size: 0.8rem;

  .date-cell-day {
    display: flex;
    align-items: center;
    justify-content: space-between;

    .date-cell-gday {
      font-size: 1rem;
    }
  }

  .festival {
    text-align: right;
    font-size: 0.75rem;
  }
}
</style>