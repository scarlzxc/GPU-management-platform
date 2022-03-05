<template>
  <div style="text-align: center">
    <div>checkInfo {{$route.query.type}}</div>
    <div>
      <el-table :data="tableData" border style="width: 80%;margin: 0 auto">
        <el-table-column prop="id" label="id" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" align="center"></el-table-column>
        <el-table-column prop="teacherName" label="老师id" align="center" v-if="$route.query.type==='student'"></el-table-column>
        <el-table-column prop="account" label="账户" align="center"></el-table-column>
        <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
        <el-table-column prop="password" label="密码" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="170">
          <template slot-scope="scope">
            <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="deleteRow(scope.row)" type="text" size="small" v-if="$route.query.type==='student'||$route.query.type==='teacher'">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="编辑信息" :visible="dialogFormVisible" width="50%">
        <el-form :model="formData">
          <el-form-item label="id" label-width="200px">
            <el-input v-model="formData.id" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="姓名" label-width="200px">
            <el-input v-model="formData.name" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="老师id" label-width="200px"  v-if="$route.query.type==='student'">
            <el-input v-model="formData.teacherName" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="账户" label-width="200px">
            <el-input v-model="formData.account" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" label-width="200px">
            <el-input v-model="formData.email" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="200px">
            <el-input v-model="formData.password" autocomplete="off" style="width: 50%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="confirm">确定</el-button>
        </div>
      </el-dialog>
    </div>
    <div style="margin-top: 5%">
      <el-button type="primary" @click="back">返回</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "checkInfo",
  data(){
    return{
      tableData: [],
      dialogFormVisible:false,
      formData:{},
    }
  },
  created(){
    this.refresh()
  },
  methods:{
    back(){
      this.$router.go(-1)
    },
    refresh() {
      let self=this
      if (this.$route.query.type==='student'){
        this.$axios.post('http://localhost:8080/admin/getallstudent',
          {},
          {headers: {
              'token':this.$store.state.token
            }
          })
          .then(function(response){
            // console.log(response)
            if (response.data.success===true){
              let data=response.data.detail
              for (let i=0;i<data.length;i++){
                self.tableData.push({id: data[i].id,password: data[i].password,email:data[i].email,
                  name: data[i]['student_name'],teacherName:data[i]['teacher_id'],account:data[i].account})
              }
              // console.log(data,self.tableData)
            }
          }).catch(function(error){
          console.log('error',error)
        })
      }else if (this.$route.query.type==='teacher'){
        this.$axios.post('http://localhost:8080/admin/getallteacher',
          {},
          {headers: {
              'token':this.$store.state.token
            }
          })
          .then(function(response){
            if (response.data.success===false){
              let data=response.data.detail
              for (let i=0;i<data.length;i++){
                self.tableData.push({id: data[i].id,name: data[i]['teacher_name'],password:data[i].password,
                  email:data[i].email,account:data[i].account})
              }
            }
          }).catch(function(error){
          console.log('error',error)
        })
      }else{
        this.$axios.post('http://localhost:8080/admin/getalladmin',
          {},
          {headers: {
              'token':this.$store.state.token
            }
          })
          .then(function(response){
            // console.log(response.data)
            if (response.data.success===false){
              let data=response.data.detail
              for (let i=0;i<data.length;i++){
                self.tableData.push({id: data[i].id,account: data[i].account,password:data[i].password,
                  email: data[i].email,name:data[i]['admin_name']})
              }
            }
          }).catch(function(error){
          console.log('error',error)
        })
      }
    },
    deleteRow(index, rows) {
      let self=this
      // console.log(index.id)
      this.$axios.post('http://localhost:8080/admin/delete'+this.$route.query.type+'?id='+index.id,
        {},
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
    edit(index,rows){
      this.dialogFormVisible=true;
      this.formData=index
    },
    confirm(){
      let self =this
      let url=''
      if (this.$route.query.type==='admin'){
        url='http://localhost:8080/admin/updateadmin'
      }else{
        url='http://localhost:8080/'+this.$route.query.type+'/update'
      }
      // this.$axios.post(url,
      //   {'id':this.$store.state.id,
      //     'student_name':this.studentName,
      //     'password':this.password,
      //     'email':this.email,
      //     'account':this.account
      //   },
      //   {headers: {
      //       'token':this.$store.state.token
      //     }
      //   })
      //   .then(function(response){
      //     // console.log('rrrrrrr',response)
      //     if (response.data.success===true){
      //       self.$store.state.password=response.data.detail.password
      //       self.$store.state.email=response.data.detail.email
      //       self.$store.state.account=response.data.detail.account
      //       self.$store.state.studentName=response.data.detail['student_name']
      //       // self.$alert(response.data.msg, '消息', {
      //       //   confirmButtonText: '确定',
      //       // });
      //     }else{
      //       self.$alert('提交失败，请重试！', '消息', {
      //         confirmButtonText: '确定',
      //       });
      //     }
      //   }).catch(function(error){
      //   console.log('error',error)
      // })
      this.dialogFormVisible=false;
    }
  }
}
</script>

<style scoped>

</style>
