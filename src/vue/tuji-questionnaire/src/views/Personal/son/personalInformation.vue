<template>
  <div class="index">
    <div class="hard-box">
      <h1>个人信息</h1>
    </div>
    <div class="user-box">
      <h1>个人头像</h1>
    </div>
    <div class="box2">
      <el-card style="border-radius: 30px">
        <div slot="header">
        </div>
        <div class="user-card">
          <div class="user-card-item"><p>用户ID：{{ userId }}</p></div>
          <div class="user-card-item"> <p>昵  称：{{ nickname }}</p></div>
          <div class="user-card-item">  <p>性  别：{{ gender }}</p></div>
          <div class="user-card-item"> <p>邮  箱：{{ email }}</p></div>
          <div class="user-card-item">  <p>电  话：{{ phone }}</p></div>
        </div>
      </el-card>
    </div>
    <div class="box">
      <img  src="../../../imager/img.png" style=" width: 100%;height: 100%;object-fit:cover;">
    </div>
    <div class="bottom-button">
      <el-button style="margin:10px 40px">修改信息</el-button>
      <el-button > 上传头像</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import icon from '@/imager/img.png';

// const myimageurl = ../../../imager/img.png
 const userId=1
 const nickname='wang'
 const gender=123
 const email=456
    const phone=456789



</script>

<style scoped>
.index{
  height: 300px;
}
.hard-box{
width: 150px;
  top: 50px;
  left: 170px;
  position: absolute;
}
.user-box{
  width: 150px;
  top: 50px;
  left: 520px;
  position: absolute;
}
.user-card{
height:300px;
  display: grid;
}

.box {
  border-radius: 100px;
  width: 200px;
  height: 200px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  top:100px;
  left: 500px;
  position: absolute;
  /*相对页面定位*/
  /*position: fixed;*/
  /*top: 50%;*/
  /*left: -200px;*/
  animation: box-slide-in 3s forwards;
  overflow: hidden;
}
.box2 {
  border-radius: 30px;
  width: 300px;
  height: auto;
  background: linear-gradient(to bottom right, #f7d9aa, #e8a87c);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  top:100px;
  left: 100px;
   position: absolute;
  /*相对页面定位*/
  /*position: fixed;*/
  /*top: 50%;*/
  /*left: -200px;*/
  animation: slide-in 3s forwards;
}

@keyframes slide-in {
  0% {
    opacity:0;
    transform: translateX(-100px);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}
@keyframes box-slide-in {
  0% {
    opacity:0;
    transform: translateX(100px);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}
.user-card-item{
  display: flex;
  align-items: center;
}
.user-card-item:hover{

}
.bottom-button{
  top: 350px;
  left: 450px;
  width: 300px;
  height: 50px;
  position: absolute;
}

p{
  font-size: 16px;
}

</style>