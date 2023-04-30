<template>
  <div style=" font-size: 12px ;display: flex;height: 50px">
    <div style="flex: 1;font-size: 18px;">
        <span @click="collapse" style="cursor: pointer">
          <el-icon v-if="!collapseBtnClass"><Fold/></el-icon>
          <el-icon v-if="collapseBtnClass"><Expand/></el-icon>
        </span>
      <div style="display: inline-block; margin-left: 20px">
      </div>
    </div>
    <div>
      <el-dropdown>
        <div class="right-div" style="target: none">{{ adminLogin.name }}
          <User class="block-col"/>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="exit">
              <el-icon>
                <SwitchButton/>
              </el-icon>&nbsp;退出
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive} from "vue";
import {Expand, Fold, Memo, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";
import {useAdminLoginStore} from "@/stores/UserLogin";
import {useRouter} from "vue-router";


export default defineComponent({
  setup(){
    const router = useRouter()
    const store = useAdminLoginStore()
    const adminLogin=reactive({
      name:store.name,
    })
    const exit=()=>{
      router.push({
        path:'/BackgroundLogin'
      })
    }
    return{
      adminLogin,
    }
  },
  name: "header",
  components: {User, Expand, Fold, Memo, UserFilled, SwitchButton},
  props: {
    collapseBtnClass: Boolean,
    collapse: Function
  },
})
</script>


<style scoped>

.right-div {
  width: 100px;
  height: 40px;
  line-height: 36px;
  cursor: pointer;
}

.block-col {
  top: 3px;
  left: 2px;
  /*display: inline-flex;*/
  height: 14px;
  width: 14px;
}
</style>
