import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)
// const apiURL=process.env.API_URL

const state = {
  token:'',
  loginType:'',
  id:null,
  studentName:'',
  password:'',
  email:'',
  teacherId:null,
  account:'',
  teacherName:'',
  adminName:'',
}

const store =new Vuex.Store({
  state,
  actions,
})

export default store;
