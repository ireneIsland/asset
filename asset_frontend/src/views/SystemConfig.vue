<template>
  <div>
    <div class="container">

      <div class="py-4">
        <h2>系統參數設定</h2>
        <p class="lead">....</p>
      </div>
        <div class="row">


          <div class="col-md-12">
              
              <div class="text-left">
                  <strong> 最後使用資產流水號: </strong> <span> {{ assetNoEnd.sysItemValue1 }} </span>
              </div>
              <table class="table table-sm table-hover">

                <thead class="thead-light">
                    <tr>
                        <th></th>
                        <th>資料1</th>
                        <th>資料2</th>
                        <th>資料3</th>
                        <th>最後修改時間</th>
                        <th>修改人</th>
                    </tr>
                </thead>

                <br>
                <strong> 資產類別 </strong>
                <tbody>
                    <tr v-for="(item) in assetType" :key="item.seq">
                        <td>
                            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                <label class="btn btn-outline-info btn-sm">
                                    <input type="radio"> 修改
                                </label>
                                <label class="btn btn-outline-danger btn-sm">
                                    <input type="radio"> 刪除
                                </label>
                            </div>
                        </td>
                        <td>{{ item.sysItemValue1 }}</td>
                        <td>{{ item.sysItemValue2 }}</td>
                        <td>{{ item.sysItemValue3 }}</td>
                        <td>{{ item.lmTime }}</td>
                        <td>{{ item.lmUser }}</td>
                    </tr>
                </tbody>

                <br>
                <strong> 報廢方式 </strong>
                <tbody>
                    <tr v-for="(item) in retirementType" :key="item.seq">
                        <td>
                            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                <label class="btn btn-outline-info btn-sm">
                                    <input type="radio"> 修改
                                </label>
                                <label class="btn btn-outline-danger btn-sm">
                                    <input type="radio"> 刪除
                                </label>
                            </div>
                        </td>
                        <td>{{ item.sysItemValue1 }}</td>
                        <td>{{ item.sysItemValue2 }}</td>
                        <td>{{ item.sysItemValue3 }}</td>
                        <td>{{ item.lmTime }}</td>
                        <td>{{ item.lmUser }}</td>
                    </tr>
                </tbody>

                <br>
                <strong> 預設保管人 </strong>
                <tbody>
                    <tr class="table-warning">
                        <td>
                            <button class="btn btn-outline-info btn-sm">修改</button>
                        </td>
                        <td>{{ assignee.sysItemValue1 }}</td>
                        <td>{{ assignee.sysItemValue2 }}</td>
                        <td>{{ assignee.sysItemValue3 }}</td>
                        <td>{{ assignee.lmTime }}</td>
                        <td>{{ assignee.lmUser }}</td>
                    </tr>
                </tbody>

                <br>
                <strong> 預設存放地點 </strong>
                <tbody>
                    <tr class="table-warning">
                        <td>
                            <button class="btn btn-outline-info btn-sm">修改</button>
                        </td>
                        <td>{{ location.sysItemValue1 }}</td>
                        <td>{{ location.sysItemValue2 }}</td>
                        <td>{{ location.sysItemValue3 }}</td>
                        <td>{{ location.lmTime }}</td>
                        <td>{{ location.lmUser }}</td>
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
      // initial
    created() {
      this.getSystemparam();
    },

    data() {
      return {
          assetNoEnd: {},
          assignee: {},
          location: {},
          assetType: [],
          retirementType: [],
      };
    },

    methods: {
        // 取得系統參數
      getSystemparam() {
        const api = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_SYSTEM}/presetparam`;
        const vm = this;
        this.$http.get(api).then((response) => {
          if(response.data.success){
            console.log(response.data.result);
            this.assetNoEnd = response.data.result.assetNoEnd;
            this.location = response.data.result.location;
            this.assignee = response.data.result.assignee;
            this.assetType = response.data.result.assetType;
            this.retirementType = response.data.result.retirementType;
          }
        })
        .catch((error) => {
            vm.errorNotify("系統參數取得失敗，請確認伺服器是否異常");
        })
      },
    },
  }

</script>

