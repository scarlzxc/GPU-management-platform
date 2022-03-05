import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import addInfo from "../components/addInfo";
import adminPage from "../components/adminPage";
import allocateGPU from "../components/allocateGPU";
import applyGPU from "../components/applyGPU";
import checkApplication from "../components/checkApplication";
import login from "../components/login";
import studentPage from "../components/studentPage";
import teacherPage from "../components/teacherPage";
import changeInfo from "../components/changeInfo";
import checkInfo from "../components/checkInfo";
import addGPUInfo from "../components/addGPUInfo";
import checkGPUInfo from "../components/checkGPUInfo";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },{
      path: '/addInfo',
      name: 'addInfo',
      component: addInfo
    },{
      path: '/adminPage',
      name: 'adminPage',
      component: adminPage
    },{
      path: '/allocateGPU',
      name: 'allocateGPU',
      component: allocateGPU
    },{
      path: '/addGPUInfo',
      name: 'addGPUInfo',
      component: addGPUInfo
    },{
      path: '/applyGPU',
      name: 'applyGPU',
      component: applyGPU
    },{
      path: '/changeInfo',
      name: 'changeInfo',
      component: changeInfo
    },{
      path: '/checkInfo',
      name: 'checkInfo',
      component: checkInfo
    },{
      path: '/checkGPUInfo',
      name: 'checkGPUInfo',
      component: checkGPUInfo
    },{
      path: '/checkApplication',
      name: 'checkApplication',
      component: checkApplication
    },{
      path: '/',
      name: 'login',
      component: login
    },{
      path: '/studentPage',
      name: 'studentPage',
      component: studentPage
    },{
      path: '/teacherPage',
      name: 'teacherPage',
      component: teacherPage
    },
  ]
})
