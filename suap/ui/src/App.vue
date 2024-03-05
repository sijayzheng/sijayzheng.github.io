<template>
  <ElConfigProvider :button="{autoInsertSpace:true}" :message="{max:5}">
    <router-view/>
  </ElConfigProvider>
</template>
