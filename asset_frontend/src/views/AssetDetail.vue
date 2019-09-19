<template>
  <div class="home">
    <div class="container">

      <div class="py-4">
        <h2>資產詳情</h2>
        <p class="lead">....</p>
      </div>

      <div class="main">

        <div class="row">
          <!-- 左6 -->
          <div class="col-md-4">
            <div class="text-left">
              <h2><span style="color: black">資產編號:</span>{{ asset.assetNo }}</h2>
              <h5 style="color: blue"><span style="color: black">主資產編號: </span> {{ asset.assetNoParent }} </h5>
              <h5 style="color: blue"><span style="color: black">狀態:</span> {{ asset.status }} </h5>
              <h5 style="color: blue"><span style="color: black">資產類別:</span> {{ asset.assetType }} </h5>
              <h5 style="color: blue"><span style="color: black">存放地點:</span> {{ asset.loaction }} </h5>
              <h5 style="color: blue"><span style="color: black">保管人:</span> {{ asset.assignee }} </h5>
              <h5 style="color: blue"><span style="color: black">金額:</span> {{ asset.amount }} </h5>
              <h5 style="color: blue"><span style="color: black">建立時間:</span> {{ asset.crateTime }} </h5>
              <h5 style="color: blue"><span style="color: black">說明:</span> {{ asset.desc }} </h5>
            </div>
          </div>



          <!-- 右6-->
          <div class="col-md-8">
            <!-- grid view -->
            <div>
              <h5 class="text-left">附屬文件</h5>
              <table class="table table-sm">
                <thead class="thead-light">
                  <tr>
                    <th style="width: 35px">#</th>
                    <th>名稱</th>
                    <th>圖片</th>
                    <th>說明</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 從後端取得圖片的方式還未處理  暫時先放假圖 -->
                  <tr v-for="(item) in asset.attachDocList" :key="item.seq">
                    <td> {{ item.seq }} </td>
                    <td> {{ item.docName }} </td>
                    <td><img v-bind:src="'https://www.ikea.com/tw/zh/images/products/linnmon-adils-zhuo-zi-bai-se__0737165_PE740925_S4.JPG'" class="cusimg" alt=""></td>
                    <td> {{ item.desc }} </td>
                  </tr>
                  <!-- <tr>
                <td> 2 </td>
                <td> 物品B </td>
                <td><img v-bind:src="'https://www.ikea.com/tw/zh/images/products/linnmon-adils-zhuo-zi-bai-se__0737165_PE740925_S4.JPG'" class="cusimg" alt=""></td>
                <td> 物品B詳細內容為XXOOOX </td>
              </tr> -->
                </tbody>
              </table>
            </div>
          </div>

        </div> <!-- row -->
      </div> <!-- main -->
    </div> <!-- container -->
  </div>
</template>

<script>
  export default {
    created() {
      this.assetNo = this.$route.params.assetNo;
      this.getNewAssetNo();
    },
    data() {
      return {
        assetNo: '',
        asset: {},
      };
    },
    methods: {
      getNewAssetNo() {
        const url =
          `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_ASSET}/getAssetDetail/?assetNo=${this.assetNo}`;
        const vm = this;
        this.$http.get(url).then((response) => {
            if (response.data.success) {
              console.log(response.data);
              vm.asset = response.data.result;
            }
          })
          .catch((error) => {

          })
      },
    },
  }

</script>

<style lang="scss">
  .cusimg {
    width: 150px;
    height: 150px;
  }

</style>
