<template>
  <div>
    <nav class="navbar navbar-light fixed-top flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0">Is-Land System</a>
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <button type="button" class="btn btn-sm btn-outline-dark" @click="signout">登出</button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
  export default {
    name: '',
    data() {
      return {
      };
    },

    methods: {
      signout() {
        // const api = `${process.env.VUE_APP_APIPATH}/${process.env.XXXXX}`;
        // console.log(api + '/XXXX')
        // this.$http.post(api + '/XXXXX').then((response) => {
          //   console.log(response.data)
        // })
        this.$router.push('/login');
      }
    }
  }

</script>

<style lang="scss">
  .navbar {
    background-color: #c6d1d8;
  }
  .navbar-brand {
    background-color: #129dfa;
  }
</style>
