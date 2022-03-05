。<template>
  <div style="text-align: center">
    <div>
      addInfo {{$route.query.type}}
    </div>
    <div style="width: 30%;margin: 0 auto;margin-top: 3%">
      <el-row style="margin-top: 3%" v-if="$route.query.type==='student'||'admin'" >
        <el-col :span="8" style="font-size: 20px">
          姓名
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="name"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%" v-if="$route.query.type==='student'" >
        <el-col :span="8" style="font-size: 20px">
          老师姓名
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="studentTeacher"></el-input>
          </div>
        </el-col>
      </el-row>
<!--      <el-row v-if="$route.query.type!==('student'&&'admin')" style="margin-top: 3%">-->
<!--        <el-col :span="8" style="font-size: 20px">-->
<!--          id-->
<!--        </el-col>-->
<!--        <el-col :span="16">-->
<!--          <div>-->
<!--            <el-input v-model="id"></el-input>-->
<!--          </div>-->
<!--        </el-col>-->
<!--      </el-row>-->
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
      <el-button type="primary" @click="submitChange">提交</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "addInfo",
  data() {
    return {
      name:'',
      password:'',
      email: '',
      account:'',
      id:'',
      studentTeacher:'',
    }
  },
  methods:{
    submitChange(){
      let self=this
      if (this.$route.query.type==='student'){
        this.$axios.post('http://localhost:8080/admin/insertstudent',
          {'account':this.account,
            'password':this.password,
            'email':this.email,
            'teacher_name':this.studentTeacher,
            'student_name':this.name
          },
          {headers: {
              'token':this.$store.state.token
            }
          })
          .then(function(response){
            console.log('rrrrrrr',response)
            if (response.data.success===true){
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
      }else if (this.$route.query.type==='teacher'){
        this.$axios.post('http://localhost:8080/admin/insertteacher',
          {'account':this.account,
            'password':this.password,
            'email':this.email,
            'teacher_name':this.name,
          },
          {headers: {
              'token':this.$store.state.token
            }
          })
          .then(function(response){
            // console.log('rrrrrrr',response)
            if (response.data.success===true){
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
      }else{
        this.$axios.post('http://localhost:8080/admin/insertadmin',
          {'account':this.account,
            'password':this.password,
            'email':this.email,
            'admin_name':this.name,
          },
          {headers: {
              'token':this.$store.state.token
            }
          })
          .then(function(response){
            // console.log('rrrrrrr',response)
            if (response.data.success===true){
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
      }
    },
    back(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
