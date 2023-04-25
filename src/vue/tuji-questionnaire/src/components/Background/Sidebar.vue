<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      style="height:100vh ;overflow-x: hidden ;border-right-color:transparent;  min-height: 400px;"
      :collapse="isCollapse"
      background-color="#545c64"
      active-text-color="#ffd04b"
      text-color="#fff"
      router="turn"
      popper-offset="-50px"
      :default-active="user"
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon><Monitor /></el-icon>
        <span>后台管理</span>
      </template>
      <el-menu-item-group>
        <template #title><span>信息管理</span></template>
        <el-menu-item index="user">账户信息管理</el-menu-item>
        <el-menu-item index="administrator">管理员信息管理</el-menu-item>
        <el-menu-item index="1-2">问卷信息管理</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="Group Two">
        <el-menu-item index="1-3">item three</el-menu-item>
      </el-menu-item-group>
      <el-sub-menu index="1-4">
        <template #title><span>item four</span></template>
        <el-menu-item index="1-4-1">item one</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
  </el-menu>
</template>
<script lang="ts">
import {defineComponent, ref} from "vue";
import {Location, Monitor,} from '@element-plus/icons-vue'
    export default defineComponent({
      name:"sidebar",
      components: {Monitor, Location},
        props:{
            isCollapse:Boolean,
            login:Boolean
        },
    })

</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px
}

</style>