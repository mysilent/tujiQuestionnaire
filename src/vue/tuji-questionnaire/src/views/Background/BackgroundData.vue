<template>
  <div class="main">
    <div id="box" ref="box"></div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue';
import * as echarts from 'echarts';
import {answerCreateData, surveyCreateData} from "@/axios/api/Background";


let box = ref(null)
onMounted(async () => {
  let myecharts = echarts.init(box.value!);
  let option = {
    title: {
      text: '问卷信息'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        label: {
          backgroundColor: '#6a7985'
        }
      }
    },
    //类型分类
    legend: {
      data: ['问卷创建数量','问卷作答数量']
    },
    //坐标
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true//如果坐标图上xy轴显示的有标示，一定要为true，否则展示不全，内容会被隔挡
    },
    //X轴的命名
    xAxis: [
      {
        type: 'category',
        // boundaryGap: false,
        // data: options.data   //['周一', '周二', '周三', '周四', '周五', '周六', '周日']
      }
    ],
    yAxis: [{
        type: 'value'
      }],
    series: [
      //bar--柱状图
      //line -- 线图
      //pie -- 饼图
      {
        name: '问卷创建数量',
        type: 'bar',
        stack: null,//'Total',
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        // data: surveyData.count,
        barWidth: '30%',
        barGap: '20%',
        barCategoryGap: '40%'
      },{
        name: '问卷作答数量',
        type: 'bar',
        stack:null,//'Total',
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        // data: answerData.count,
        barWidth: '30%',
        barGap: '20%',
        barCategoryGap: '40%'
      },
    ]
  };
  myecharts.setOption(option)
  surveyCreateData(id).then(map => {
    const sortedKeys = Object.keys(map.data.data).sort(); // 对属性名进行排序
    for (const key of sortedKeys) {
      surveyData.data.push(key);
      surveyData.count.push(map.data.data[key]);
    }

  })
  answerCreateData(id).then(map => {
    const sortedKeys = Object.keys(map.data.data).sort(); // 对属性名进行排序
    for (const key of sortedKeys) {
      answerData.data.push(key);
      answerData.count.push(map.data.data[key]);
    }

    myecharts.setOption({
      xAxis: {
        data: surveyData.data
      },
      series: [{
        data: surveyData.count
      },{
        data: answerData.count
      }]
    });
  })
// 更新option

  window.onresize = function () {
    // console.log('窗口大小发生改变了')
    // echarts适配
    myecharts.resize();

  }
})
const surveyData = reactive({data: [], count: []})
const answerData = reactive({data: [], count: []})
const id = reactive({id: 1})


</script>

<style scoped>
#box {
  width: 100%;
  height: 400px;
}

.main {

  padding: 10px;

  border-radius: 5px;
  height: 500px;
  margin-top: 10px;
}
</style>