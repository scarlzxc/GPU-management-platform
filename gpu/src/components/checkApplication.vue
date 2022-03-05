<template>
  <div style="text-align: center">
    <div>checkApplication</div>
    <div>
      <el-table :data="tableData" border style="width: 80%;margin: 0 auto">
        <el-table-column prop="id" label="申请id" width="130" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" width="150" align="center"></el-table-column>
        <el-table-column prop="reason" label="原因" width="270" align="center"></el-table-column>
        <el-table-column prop="startTime" label="开始时间" width="170" align="center"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="170" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button @click="pass(scope.row)" type="text" size="small">通过</el-button>
            <el-button @click="decline(scope.row)" type="text" size="small">拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="margin-top: 5%">
      <el-button type="primary" @click="back">返回</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "checkApplication",
  data(){
    return{
      tableData: [],
    }
  },
  created(){
    let self=this
    this.$axios.post('http://localhost:8080/teacher/listtocheck',{},
      {headers: {
          'token':this.$store.state.token
        }
      })
      .then(function(response){
        if (response.data.success===false){
          let data=response.data.detail
          for (let i=0;i<data.length;i++){
            self.tableData.push({id: data[i].id,name: data[i].name,reason:data[i].reason,
              startTime: data[i]['start_time'],endTime:data[i]['end_time']})
          }
        }
      }).catch(function(error){
      console.log('error',error)
    })
  },
  methods:{
    back(){
      this.$router.go(-1)
    },
    pass(index){
      console.log(index.id)
      let self=this
      this.$axios.post('http://localhost:8080/teacher/check',{
          id: index.id,
        status: '1'
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
          }
        }).catch(function(error){
        console.log('error',error)
      })
    },
    decline(index){
      let self=this
      this.$axios.post('http://localhost:8080/teacher/check',{
          id: index.id,
          status: '3'
        },
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log('rrrrrrr',response)
          if (response.data.success===true){
            self.$alert('操作成功', '消息', {
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
