<template>
<div>
  <router-view></router-view>
</div>
  <plane></plane>
</template>

<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Plane from "@/components/sheep.vue";
</script>

<style>
*{
  margin: 0;
  padding: 0;
}
</style>


