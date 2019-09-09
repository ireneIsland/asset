<template>
  <div class="home">
    <div class="container">

      <div class="py-4">
        <h2>資產詳情</h2>
        <p class="lead">....</p>
      </div>

        <div class="main">
        
            <div class="text-left">
                <h2><span style="color: black">資產編號:</span> 1900001</h2>
                <h5 style="color: blue"><span style="color: black">主資產編號: </span></h5>
                <h5 style="color: blue"><span style="color: black">狀態:</span> 庫存</h5>
                <h5 style="color: blue"><span style="color: black">資產類別:</span> OA</h5>
                <h5 style="color: blue"><span style="color: black">存放地點:</span> 新竹辦公室倉庫</h5>
                <h5 style="color: blue"><span style="color: black">保管人:</span> is0240</h5>
                <h5 style="color: blue"><span style="color: black">金額:</span> 4000$</h5>
                <h5 style="color: blue"><span style="color: black">建立時間:</span> 2019-9-9</h5>
                <h5 style="color: blue"><span style="color: black">說明:</span> 於XXXX購買</h5>
            </div>
            <br>

            <!-- grid view -->
        <div>
        <h5 class="text-left">附屬文件</h5>
          <table class="table table-sm table-hover">
            <thead class="thead-light">
              <tr>
                <th style="width: 35px">-</th>
                <th>名稱</th>
                <th>圖片</th>
                <th>說明</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td> 1 </td>
                <td> 物品A </td>
                <td><img v-bind:src="'https://www.ikea.com/tw/zh/images/products/ronninge-can-yi__0642047_PE700849_S4.JPG'" class="cusimg" alt=""></td>
                <td> 物品A詳細內容為XXOOOX </td>
              </tr>
              <tr>
                <td> 2 </td>
                <td> 物品B </td>
                <td><img v-bind:src="'https://www.ikea.com/tw/zh/images/products/linnmon-adils-zhuo-zi-bai-se__0737165_PE740925_S4.JPG'" class="cusimg" alt=""></td>
                <td> 物品B詳細內容為XXOOOX </td>
              </tr>
            </tbody>
          </table>
        </div>

        </div>
    </div>
  </div>
</template>

<script>

export default {

}
</script>

<style lang="scss">
    .cusimg {
        width: 150px;
        height: 150px;
    }
</style>
