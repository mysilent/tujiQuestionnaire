<template>
  <div id="plane" @click.stop>
    <i class="fa fa-paper-plane" aria-hidden="true"></i>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

let plane = null;
let deg = ref(0);
let ex = ref(0);
let ey = ref(0);
let vx = ref(0);
let vy = ref(0);
let count = ref(0);

onMounted(() => {
  plane = document.getElementById('plane');
  window.addEventListener('mousemove', (e) => {
    ex.value = e.x - plane.offsetLeft - plane.clientWidth / 2;
    ey.value = e.y - plane.offsetTop - plane.clientHeight / 2;
    deg.value = 360 * Math.atan(ey.value / ex.value) / (2 * Math.PI) + 45;
    if (ex.value < 0) {
      deg.value += 180;
    }
    count.value = 0;
  });
});

function draw() {
  plane.style.transform = 'rotate(' + deg.value + 'deg)';
  if (count.value < 100) {
    vx.value += ex.value / 100;
    vy.value += ey.value / 100;
  }
  plane.style.left = vx.value + 'px';
  plane.style.top = vy.value + 'px';
  count.value++;
}

setInterval(draw, 1);
</script>

<style>
#plane{
  color: #fff;
  font-size: 70px;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  pointer-events: none;
}
.fa-paper-plane {
  color: #0062cc;
  opacity: 0.3;
  /*background: linear-gradient(to right bottom, #828892, #728a9c, #598ea1, #38929e, #0d9592);*/
}
</style>