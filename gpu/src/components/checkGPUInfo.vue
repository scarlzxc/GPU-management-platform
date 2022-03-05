<template>
  <div style="text-align: center">
    <div>checkGPUInfo</div>
    <div>
      <el-table :data="tableData" border style="width: 80%;margin: 0 auto">
        <el-table-column prop="id" label="id" align="center"></el-table-column>
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
        <el-table-column prop="user" label="使用者" align="center"></el-table-column>
        <el-table-column label="操作" align="center" fixed="right" width="170">
          <template slot-scope="scope">
            <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
            <el-button @click="del(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="编辑信息" :visible="dialogFormVisible" width="50%">
        <el-form :model="formData">
          <el-form-item label="id" label-width="200px">
            <el-input v-model="formData.id" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="资源拥有者" label-width="200px">
            <el-input v-model="formData.resourceOwner" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="资源使用者" label-width="200px">
            <el-input v-model="formData.resourceUser" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="主机名称" label-width="200px"  v-if="$route.query.type==='student'">
            <el-input v-model="formData.hostName" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="ip" label-width="200px">
            <el-input v-model="formData.ip" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="GPU资源" label-width="200px">
            <el-input v-model="formData.GPU" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="用户名" label-width="200px">
            <el-input v-model="formData.username" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="200px">
            <el-input v-model="formData.password" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="ssd" label-width="200px">
            <el-input v-model="formData.ssd" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="disk" label-width="200px">
            <el-input v-model="formData.disk" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="内存" label-width="200px">
            <el-input v-model="formData.memory" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="cpu" label-width="200px">
            <el-input v-model="formData.cpu" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="操作系统" label-width="200px">
            <el-input v-model="formData.os" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="备注" label-width="200px">
            <el-input v-model="formData.remarks" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="使用者" label-width="200px">
            <el-input v-model="formData.user" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="confirm">确定</el-button>
        </div>
      </el-dialog>
    </div>
    <div style="margin-top: 5%">
        <router-link to="/addGPUInfo">
          <el-button type="primary">录入GPU信息</el-button>
        </router-link>
      <el-button type="primary" @click="back">返回</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "checkGPUInfo",
  data(){
    return{
      tableData:[],
      formData:{},
      dialogFormVisible:false,
    }
  },
  created(){
    this.refresh()
  },
  methods:{
    back(){
      this.$router.go(-1)
    },
    edit(index,row){
      this.dialogFormVisible=true
      this.formData=index
    },
    confirm(){
      let self=this
      this.$axios.post('http://localhost:8080/admin/updategpu',
        {'id':this.formData.id,
          'resource_owner':this.formData.resourceOwner,
          'resource_user':this.formData.resourceUser,
          'host_name':this.formData.hostName,
          'ip':this.formData.ip,
          'gpu_resource':this.formData.GPU,
          'username':this.formData.username,
          'password':this.formData.password,
          'ssd':this.formData.ssd,
          'disk':this.formData.disk,
          'memory':this.formData.memory,
          'cpu':this.formData.cpu,
          'os':this.formData.os,
          'remarks':this.formData.remarks,
          'user':this.formData.user,
        },
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log('rrrrrrr',response)
          if (response.data.success===true){
            self.dialogFormVisible=false;
            self.$alert(response.data.msg, '消息', {
              confirmButtonText: '确定',
            });
          }else{
            self.$alert('修改失败，请重试！', '消息', {
              confirmButtonText: '确定',
            });
          }
        }).catch(function(error){
        console.log('error',error)
      })
    },
    del(index,row){
      let self=this
      this.$axios.post('http://localhost:8080/admin/deletegpu?id='+index.id,{},
        {headers: {
            'token':this.$store.state.token
          }
        })
        .then(function(response){
          // console.log(response)
          if (response.data.success===true){
            self.$alert(response.data.detail, '消息', {
              confirmButtonText: '确定',
            });
            self.tableData=[]
            self.refresh()
          }
        }).catch(function(error){
        console.log('error',error)
      })
    },
    refresh(){
      let self=this
      this.$axios.post('http://localhost:8080/admin/getallgpu',{},
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
                remarks:data[i].remarks,
                user:data[i].user,})
            }
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
