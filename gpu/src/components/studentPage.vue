<template>
  <div style="text-align: center">
    <h1>学生界面</h1>
    <div style="margin-top: 5%">
      <router-link :to="{path:'/changeInfo',query: {type: 'student'}}">
        <el-button type="primary">修改个人信息</el-button>
      </router-link>
    </div>
    <div style="margin-top: 5%">
      <router-link to="/applyGPU">
        <el-button type="primary">申请GPU</el-button>
      </router-link>
    </div>
    <div style="margin-top: 5%">
        <el-button type="primary" @click="exit">退出登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "studentPage",
  methods:{
    exit(){
      let self=this
      this.$axios.post('http://localhost:8080/student/logout',
        {'token':this.$store.state.token},
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log('rrrrrrr',response)
          if (response.data.success===false){
            self.$store.state.token=null
            self.$router.go(-1)
          }else{
            self.$alert('退出失败，请重试！', '消息', {
              confirmButtonText: '确定',
            });
          }
        }).catch(function(error){
        console.log('error',error)
      })
    }
  }
}
</script>

<style scoped>

</style>
