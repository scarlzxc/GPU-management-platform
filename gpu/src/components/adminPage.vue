<template>
  <div style="text-align: center">
    <h1>管理员界面</h1>
    <div style="margin-top: 3%">
      <router-link :to="{path:'/changeInfo',query: {type: 'admin'}}">
        <el-button type="primary">修改个人信息</el-button>
      </router-link>
    </div>
    <div style="margin-top: 3%">
      <router-link :to="{path:'/addInfo',query: {type: 'student'}}">
        <el-button type="primary">录入学生信息</el-button>
      </router-link>
      <router-link :to="{path:'/addInfo',query: {type: 'teacher'}}">
        <el-button type="primary">录入老师信息</el-button>
      </router-link>
      <router-link :to="{path:'/addInfo',query: {type: 'admin'}}">
        <el-button type="primary">录入管理员信息</el-button>
      </router-link>
    </div>
    <div style="margin-top: 3%">
      <router-link :to="{path:'/checkInfo',query: {type: 'student'}}">
        <el-button type="primary">查看学生信息</el-button>
      </router-link>
      <router-link :to="{path:'/checkInfo',query: {type: 'teacher'}}">
        <el-button type="primary">查看老师信息</el-button>
      </router-link>
      <router-link :to="{path:'/checkInfo',query: {type: 'admin'}}">
        <el-button type="primary">查看管理员信息</el-button>
      </router-link>
    </div>
    <div style="margin-top: 3%">
      <router-link to="/checkGPUInfo">
        <el-button type="primary">查看GPU信息</el-button>
      </router-link>
    </div>
    <div style="margin-top: 3%">
      <router-link to="/allocateGPU">
        <el-button type="primary">查看待分配</el-button>
      </router-link>
    </div>
    <div style="margin-top: 5%">
      <el-button type="primary" @click="exit">退出登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "adminPage",
  methods:{
    exit(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
