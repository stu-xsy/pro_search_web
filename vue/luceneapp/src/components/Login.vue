<template>
  <div>
    用户名:<input type="text" v-model="loginForm.username" placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" v-model="loginForm.password" placeholder="请输入密码"/>
    <br><br>
    <button v-on:click="Login">登录</button>
    <div>

        <p style="text-align: left" v-for="a in skulist" v-bind:key="a">
          <span v-text="a.answer"></span>
        </p>

    </div>
  </div>

</template>
<style>

</style>
<script>

export default {
  name: 'Login',
  data () {
    return {
      loginForm:{
        username:'',
        password:''
      },
      skulist: {
        id:'',
        answer:2
      },
      recordCount: 1
    }
  },
  methods: {
    Login () {
      this.$axios
        .post('http://localhost:8080/api/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            console.log(successResponse.data)
            this.recordCount =successResponse.data.recordCount
            this.skulist =successResponse.data.skulist

          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

