<template>
  <div style="text-align: center">
    <div>
      addInfo {{$route.query.type}}
    </div>
    <div style="width: 30%;margin: 0 auto;margin-top: 3%">
      <el-row style="margin-top: 3%" >
        <el-col :span="8" style="font-size: 20px">
          资源拥有者
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="resourceOwner"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%" >
        <el-col :span="8" style="font-size: 20px">
          资源使用者
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="resourceUser"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          主机名称
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="hostName"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          ip
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="ip"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          GPU资源
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="GPU"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          用户名
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="username"></el-input>
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
          ssd
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="ssd"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          disk
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="disk"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          内存
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="memory"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          cpu
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="cpu"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          操作系统
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="os"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          备注
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="remarks"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          使用者
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="user"></el-input>
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
  name: "addGPUInfo",
  data() {
    return {
      resourceOwner:'',
      resourceUser:'',
      hostName:'',
      ip:'',
      GPU:'',
      username:'',
      password:'',
      ssd:'',
      disk:'',
      memory:'',
      cpu:'',
      os:'',
      remarks:'',
      user:'',
    }
  },
  methods:{
    submitChange(){
      let self=this
      this.$axios.post('http://localhost:8080/admin/insertgpu',
        {'resource_owner':this.resourceOwner,
          'resource_user':this.resourceUser,
          'host_name':this.hostName,
          'ip':this.ip,
          'gpu_resource':this.GPU,
          'username':this.username,
          'password':this.password,
          'ssd':this.ssd,
          'disk':this.disk,
          'memory':this.memory,
          'cpu':this.cpu,
          'os':this.os,
          'remarks':this.remarks,
          'user':this.user,
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
    },
    back(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
