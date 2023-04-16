<template>
  <personal-top></personal-top>
  <PersonalCenter></PersonalCenter>
<!--  <router-view></router-view>-->
</template>
<script lang="ts">

import {defineComponent} from "vue";
import PersonalTop from "@/components/Personal-top.vue";
import PersonalCenter from "@/views/Personal/PersonalCenter.vue";
export default defineComponent({
  components: {PersonalCenter, PersonalTop},
  setup(){

  }
})


</script>

<style scoped>

</style>