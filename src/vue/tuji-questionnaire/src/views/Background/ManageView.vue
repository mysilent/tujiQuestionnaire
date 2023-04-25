<template>
    <div style="">
        <el-container >
            <el-aside :width="sideWidth + 'px'" class="sidebar" style="background-color: rgb(238, 241, 246);overflow-x: hidden;box-shadow: 2px 0 6px rgb(0,21,3);">
                <sidebar :isCollapse="isCollapse" :login="login"/>  <!--侧边栏-->
            </el-aside>
            <el-container>
                <el-header style="border-bottom: 1px solid #828279" class="ml-5">
                    <HeaderView :collapseBtnClass="collapseBtnClass " :collapse="collapse"></HeaderView>
                </el-header>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script lang="ts">
    import sidebar from '@/components/Background/Sidebar.vue'
    import HeaderView from '@/components/Background/Header.vue'
    import User from '@/views/Background/User.vue'
    import {defineComponent} from "vue";
    export default defineComponent({
        data() {
            return {
                isCollapse: false,
                collapseBtnClass:true,
                sideWidth:200,
                login:true,
            }
        },

        components:{
            sidebar,
            HeaderView,
            User
        },
        methods :{
            collapse(){  //点击收缩按钮触发
                this.isCollapse=!this.isCollapse;
                if (this.isCollapse){
                    this.sideWidth=64;
                    this.collapseBtnClass=true;
                    this.login=false
                }else {
                    this.sideWidth=200;
                    this.collapseBtnClass=false;
                    this.login=true
                }
            }

        }

    })
</script>


<style>
    .el-header {
        line-height: 60px;
    }
    .sidebar{
      transition: width .3s ease-in-out ;
    }


</style>