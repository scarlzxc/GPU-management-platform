<template>
  <div style="text-align: center">
    <div>
      changeInfo {{$route.query.type}}
    </div>
    <div style="width: 30%;margin: 0 auto;margin-top: 3%">
      <el-row style="margin-top: 3%" v-if="$route.query.type==='student'" >
        <el-col :span="8" style="font-size: 20px">
          学生姓名
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="studentName"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row v-if="$route.query.type==='teacher'||$route.query.type==='admin'" style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          id
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="id"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          密码
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="password"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          邮箱
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="email"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          账户
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="account"></el-input>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 5%">
      <el-button type="primary" @click="back">返回</el-button>
      <el-button type="primary" @click="submitChange">提交修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "changeInfo",
  data() {
    return {
      studentName:'',
      password:'',
      email: '',
      account:'',
      id:null,
    }
  },
  created(){
    this.studentName=this.$store.state.studentName
    this.password=this.$store.state.password
    this.email=this.$store.state.email
    this.account=this.$store.state.account
    this.id=this.$store.state.id
  },
  methods:{
    submitChange(){
      let self=this
      let url=''
      if (this.$store.state.loginType==='admin'){
        url='http://localhost:8080/admin/updateadmin'
      }else{
        url='http://localhost:8080/'+this.$store.state.loginType+'/update'
      }
      this.$axios.post(url,
        {'id':this.$store.state.id,
          'student_name':this.studentName,
          'password':this.password,
          'email':this.email,
          'account':this.account
        },
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log('rrrrrrr',response)
          if (response.data.success===true){
            self.$store.state.password=response.data.detail.password
            self.$store.state.email=response.data.detail.email
            self.$store.state.account=response.data.detail.account
            self.$store.state.studentName=response.data.detail['student_name']
            self.$alert(response.data.msg, '消息', {
              confirmButtonText: '确定',
            });
          }else{
            self.$alert('提交失败，请重试！', '消息', {
              confirmButtonText: '确定',
            });
          }
        }).catch(function(error){
        console.log('error',error)
      })
    },
    back(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
