<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  import Dashboard from '@/components/Dashboard.vue';
export default {
  components: {
    Dashboard,
  }
}
</script>

<style lang="scss">
@import "./assets/all";
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif, 'Microsoft JhengHei';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
.cus-mainPage {
  text-align: left;
}
.cus-input {   
  margin-right: 3em
}
.cus-label {
  margin-right: 3em;
}

.table {
  font-family: 'Avenir', Helvetica, Arial, sans-serif, 'Microsoft JhengHei';
}
</style>