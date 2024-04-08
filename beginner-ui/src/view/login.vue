<template>
  <div class="login-container">

    <el-form ref="loginForm" :model="loginForm" label-width="50px" label-position="left" class="login-form">
      <h2>设备管理系统</h2>
      <el-form-item label="用户名"  label-width="15" prop="username" class="el-form-item-login">
        <el-input v-model="loginForm.username" placeholder="请输入用户名" class="el-input"></el-input>
      </el-form-item>
      <el-form-item label="密码" label-width="6" prop="password" class="el-form-item-login">
        <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" class="el-input-password"></el-input>
      </el-form-item>
      <el-form-item class="el-form-item-button">
        <el-button type="primary" @click="login" class="el-button"><p class="dengLu">登录</p></el-button>
      </el-form-item>
      <el-form-item class="el-form-item-link">
        <el-link href="/#/register" type="primary" class="el-link">注册</el-link>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {getToken} from '../api/system/user/user';
import 'element-ui/lib/theme-chalk/index.css';
import Layout from '../components/Layout.vue';
export default {

  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    };
  },
  mounted() {
    if (localStorage.getItem('token')){
      localStorage.removeItem('token');
      this.refreshPageOnce();
    }

  },
  created() {

  },
  methods: {
    loginToken(){
      getToken().then(res =>{
        localStorage.setItem("token",res.result);
      })
    },
    login() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          // 执行登录操作，可以调用后端接口进行登录验证等操作
          // console.log('用户名：', this.loginForm.username);
          // console.log('密码：', this.loginForm.password);
          this.loginToken();
          // localStorage.setItem("token","token");
          localStorage.setItem("refresh",1);
          localStorage.setItem("appload",1);
          // 模拟登录成功，跳转到首页或者其他页面
          this.$router.push('/home');
          Layout.mounted();
        }
      });
    },
    delToken() {
      //清除token
      localStorage.removeItem('token')
    },
    refreshPageOnce() {
      // 调用 window.location.reload() 方法来刷新页面
      window.location.reload();
    }
  }
};
</script>

<style scoped>

.login-container{
  background-image: url("../../static/images/login-background.jpg");
  height: 100vh;
  width: 100%;
  background-size: cover; /* 让背景图片自适应铺满整个元素 */
  background-position: center; /* 设置背景图片的位置为居中 */
  background-repeat: no-repeat; /* 禁止背景图片重复 */
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-form {
  opacity: 0.5;
  border-radius: 20px;
  text-align: center;
  width: 500px;
  height: 300px;
  margin: 0 auto;
  background: white;
}
.el-input, .el-input-password{
  width: 200px;
}
.el-input-password{
  margin-left: 13px;
}
.el-form-item-login{
  margin-left: 20%;
  width: 55%;
}
.el-form-item-button, .el-form-item-link{
  height: 20px;
  margin-right: 50px;
}
.el-button{
  height: 30px;
}
.dengLu{
  margin-top: -6px;
}
</style>
