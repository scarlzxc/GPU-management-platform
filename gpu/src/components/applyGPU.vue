<template>
  <div style="text-align: center">
    <div>
      apply GPU
    </div>
    <div style="width: 30%;margin: 0 auto;margin-top: 3%">
      <el-row>
        <el-col :span="8" style="font-size: 20px">
          学生姓名
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="studentName"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          申请原因
        </el-col>
        <el-col :span="16">
          <div>
            <el-input v-model="reason"></el-input>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          开始时间
        </el-col>
        <el-col :span="16">
          <div>
            <el-date-picker v-model="startTime" type="date" placeholder="选择开始日期" style="width: 100%" value-format="yyyyMMdd"></el-date-picker>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 3%">
        <el-col :span="8" style="font-size: 20px">
          结束时间
        </el-col>
        <el-col :span="16">
          <div>
            <el-date-picker v-model="endTime" type="date" placeholder="选择结束日期" style="width: 100%" value-format="yyyyMMdd"></el-date-picker>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 5%">
      <el-button type="primary" @click="back">返回</el-button>
      <el-button type="primary" @click="submitChange">提交申请</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "applyGPU",
  data() {
    return {
      studentName: '',
      reason: '',
      startTime: '',
      endTime: '',
    }
  },
  methods:{
    submitChange(){
      let self=this
      // console.log(this.$store.state.token)
      this.$axios.post('http://localhost:8080/student/applyapplication',
        {'status':'0',
          'name':this.studentName,
          'reason':this.reason,
          'start_time':this.startTime,
          'end_time':this.endTime
        },
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log('rrrrrrr',response)
          if (response.data.success===true){
            self.$alert('提交成功', '消息', {
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
