<template>
<index-top></index-top>
  <router-view></router-view>
</template>



<script>
import IndexTop from "@/components/index-top.vue";
export default {
  name: "homeView",
  components: {IndexTop},
}

</script>
<style>

</style>