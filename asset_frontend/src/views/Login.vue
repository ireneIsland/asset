<template>
  <div>
    <form class="form-signin" @submit.prevent="signin">
      <h1 class="h3 mb-3 font-weight-normal">系統登入</h1>
      <label for="iptEmpId" class="sr-only"></label>
      <input type="text" id="iptEmpId" class="form-control" placeholder="工號" autofocus v-model="EmpData.empId">
      <label for="iptPassword" class="sr-only">Password</label>
      <input type="password" id="iptPassword" class="form-control" placeholder="Password"
        v-model="EmpData.password">
      <button class="btn btn-lg btn-info btn-block" type="submit">登入</button>
    </form>
  </div>
</template>

<script>
  export default {
    name: '',
    data() {
      return {
        EmpData: {
          empId: '',
          password: ''
        }
      };
    },
    components: {
    },
    methods: {
      signin() {
        this.$router.push("asset/");
        // const api = `${process.env.VUE_APP_APIPATH}/${process.env.XXXXX}`;
        // console.log(api + '/XXXX')
        // this.$http.post(api + '/XXXXX').then((response) => {
        //   console.log(response.data)
        // })
      }
    }
  }

</script>


<style scoped lang="scss">
  html,
  body {
    height: 100%;
  }

  body {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
  }

  .form-signin {
    width: 100%;
    max-width: 330px;
    padding: 30px;
    margin: auto;
    margin-top: 80px;
  }

  .form-signin .checkbox {
    font-weight: 400;
  }

  .form-signin .form-control {
    position: relative;
    box-sizing: border-box;
    height: auto;
    padding: 10px;
    font-size: 16px;
  }

  .form-signin .form-control:focus {
    z-index: 2;
  }

  .form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }

</style>
