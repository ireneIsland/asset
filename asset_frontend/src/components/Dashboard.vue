<template>
  <div>
    <!-- 上欄位列表 -->
    <Navbar/>
    <div class="container-fluid">
      <div class="row">
        <!-- 左欄位列表 -->
        <Sidebar/>

        <!-- 主畫面 router -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <!-- 訊息 -->
          <vue-snotify/>

          <!-- router 分頁 -->
          <router-view/>

          <!-- footer -->
          <Footer />
        </main>
      </div>
    </div>

  </div>
</template>

<script>
  import Asterisk from '@/components/Asterisk.vue';
  import Navbar from '@/components/Navbar.vue';
  import Sidebar from '@/components/Sidebar.vue';
  import Footer from '@/components/Footer.vue'
export default {
  components: {
    Navbar,
    Sidebar,
    Footer,
    Asterisk
  }
}
</script>
