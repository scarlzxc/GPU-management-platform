<template>
  <div style="text-align: center">
    <div>allocateGPU</div>
    <div>
      <el-table :data="tableData" border style="width: 80%;margin: 0 auto">
        <el-table-column prop="id" label="id" width="120" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" width="150" align="center"></el-table-column>
        <el-table-column prop="reason" label="原因" width="270" align="center"></el-table-column>
        <el-table-column prop="startTime" label="开始时间" width="170" align="center"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="170" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" width="130" align="center"></el-table-column>
        <el-table-column prop="GPUID" label="GPU id" width="130" align="center"></el-table-column>
        <el-table-column label="操作" align="center" fixed="right" width="120">
          <template slot-scope="scope">
            <el-popover trigger="click">
              <el-table :data="gpuData">
                <el-table-column prop="resourceOwner" label="资源拥有者" align="center"></el-table-column>
                <el-table-column prop="resourceUser" label="资源使用者" align="center"></el-table-column>
                <el-table-column prop="hostName" label="主机名称" align="center"></el-table-column>
                <el-table-column prop="ip" label="ip" width="170" align="center"></el-table-column>
                <el-table-column prop="GPU" label="GPU资源" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名" align="center"></el-table-column>
                <el-table-column prop="password" label="密码" align="center"></el-table-column>
                <el-table-column prop="ssd" label="ssd" align="center"></el-table-column>
                <el-table-column prop="disk" label="disk" align="center"></el-table-column>
                <el-table-column prop="memory" label="内存" align="center"></el-table-column>
                <el-table-column prop="cpu" label="cpu" align="center"></el-table-column>
                <el-table-column prop="os" label="操作系统" align="center"></el-table-column>
                <el-table-column prop="remarks" label="备注" align="center"></el-table-column>
                <!--                <el-table-column prop="user" label="使用者" align="center"></el-table-column>-->
                <el-table-column label="操作" align="center" fixed="right" width="170">
                  <template slot-scope="scope">
                    <el-button @click="choose(scope.row)" type="text" size="small">选择</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-button slot="reference" @click="allocate(scope.row)" type="text" size="small">分配</el-button>
            </el-popover>
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
  name: "allocateGPU",
  data(){
    return{
      tableData: [],
      gpuData:[],
      appID:'',
    }
  },
  created(){
    this.refresh()
  },
  methods:{
    back(){
      this.$router.go(-1)
    },
    refresh(){
      this.tableData=[]
      let self=this
      this.$axios.post('http://localhost:8080/admin/listtogive',{},
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log(response)
          if (response.data.success===false){
            let data=response.data.detail
            for (let i=0;i<data.length;i++){
              self.tableData.push({id:data[i].id,
                startTime:data[i]['start_time'],
                endTime:data[i]['end_time'],
                name:data[i].name,
                reason:data[i].reason,
                status:data[i].status,
                GPUID:data[i]['gpu_id']})
            }
          }
        }).catch(function(error){
        console.log('error',error)
      })
    },
    allocate(index,row){
      this.gpuData=[]
      this.appID=index.id
      let self=this
      this.$axios.post('http://localhost:8080/admin/availablegpu',{},
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          console.log(response)
          if (response.data.success===false){
            let data=response.data.detail
            for (let i=0;i<data.length;i++){
              self.gpuData.push({id:data[i].id,
                resourceOwner:data[i]['resource_owner'],
                resourceUser:data[i]['resource_user'],
                hostName:data[i]['host_name'],
                ip:data[i].ip,
                GPU:data[i]['gpu_resource'],
                username:data[i].username,
                password:data[i].password,
                ssd:data[i].ssd,
                disk:data[i].disk,
                memory:data[i].memory,
                cpu:data[i].cpu,
                os:data[i].os,
                remarks:data[i].remarks})
            }
          }
        }).catch(function(error){
        console.log('error',error)
      })
    },
    choose(index,row){
      let self=this
      this.$axios.post('http://localhost:8080/admin/givegpu',
        {
          'id':this.appID,
          'gpu_id':index.id,
          'status':'2'
        },
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          console.log(response)
          if (response.data.success===true){
            self.$alert(response.data.msg, '消息', {
              confirmButtonText: '确定',
            });
            self.refresh()
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
