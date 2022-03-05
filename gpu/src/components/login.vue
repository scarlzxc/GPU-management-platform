<template>
  <div style="text-align: center">
    <h1>登录</h1>
    <div style="font-size: 20px;margin-top: 5%">
      账号
      <el-input v-model="account" placeholder="请输入用户名" style="width: 20%"></el-input>
    </div>
    <div style="font-size: 20px;margin-top: 2%">
      密码
      <el-input placeholder="请输入密码" v-model="password" show-password style="width: 20%"></el-input>
    </div>
    <div style="height: 200px;width: 50%;margin: 0 auto;margin-top: 3%">
      <el-col :span="8">
        <el-card :body-style="{ padding: '3%' }" shadow="hover"
                 style="border-radius: 15px;height: 98%;cursor: pointer;"
                 :style="{'border': $store.state.loginType==='student'?'1px solid #409EFF':'1px solid #DCDFE6'}"
                 @click.native="chooseLoginType('student')">
          <div style="width: 100%;height: 195px;display: block">
            学生
            <!--                <img :src=chartImgList[i-1] style="height: 100%;width: 100%;object-fit: cover;border-radius: 15px">-->
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card :body-style="{ padding: '3%' }" shadow="hover"
                 style="border-radius: 15px;height: 98%;cursor: pointer;margin-left:2%"
                 :style="{'border': $store.state.loginType==='teacher'?'1px solid #409EFF':'1px solid #DCDFE6'}"
                 @click.native="chooseLoginType('teacher')">
          <div style="width: 100%;height: 195px;display: block">
            老师
            <!--                <img :src=chartImgList[i-1] style="height: 100%;width: 100%;object-fit: cover;border-radius: 15px">-->
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card :body-style="{ padding: '3%' }" shadow="hover"
                 style="border-radius: 15px;height: 98%;cursor: pointer;margin-left:2%"
                 :style="{'border': $store.state.loginType==='admin'?'1px solid #409EFF':'1px solid #DCDFE6'}"
                 @click.native="chooseLoginType('admin')">
        <div style="width: 100%;height: 195px;display: block">
            管理员
            <!--                <img :src=chartImgList[i-1] style="height: 100%;width: 100%;object-fit: cover;border-radius: 15px">-->
          </div>
        </el-card>
      </el-col>
    </div>
    <div style="margin-top: 5%">
      <el-button type="primary" @click="loginSystem">登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      account: '',
      password: '',
    }
  },
  mounted() {

  },
  computed:{

  },
  watch:{

  },
  methods: {
    chooseLoginType(type){
      this.$store.state.loginType=type
    },
    loginSystem(){
      //验证用户名密码正确
      let self=this
      // console.log(this.account,this.password)
      this.$axios.post('http://localhost:8080/'+this.$store.state.loginType+'/login',{'account':this.account,'password':this.password})
        .then(function(response){
        // console.log(response)
          if (response.data.success===true){
            self.$store.state.token=response.data.token
            self.$store.state.id=response.data.detail.id
            self.$store.state.password=response.data.detail.password
            self.$store.state.email=response.data.detail.email
            self.$store.state.account=response.data.detail.account
            self.$store.state.teacherName=response.data.detail['teacher_name']
            self.$store.state.studentName=response.data.detail['student_name']
            self.$store.state.adminName=response.data.detail['admin_name']
            self.$store.state.teacherId=response.data.detail['teacher_id']
            self.$router.push('/'+self.$store.state.loginType+'Page')
          }else{
            self.$alert('用户名或密码错误', '消息', {
              confirmButtonText: '关闭',
            });
          }
      }).catch(function(error){
        console.log('error',error)
      })
    },
  }
}
</script>

<style scoped>

</style>
