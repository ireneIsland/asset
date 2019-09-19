<template>
  <div>
    <!-- MODAL -->
    <b-modal class="cus-model" id="empModal" size="sm" hide-footer>
      <div>
          <table class="table table-borderless table-sm table-hover">
            <thead>
              <tr>
                <th>工號</th>
                <th>姓名</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item) in empListData" :key="item.empId" @click="chooseEmp(item)" style="cursor: pointer;">
                <td> {{item.empId}} </td>
                <td> {{item.empName}} </td>
              </tr>
            </tbody>
          </table>
      </div>
    </b-modal>
    <div class="container-fluid">

      <div class="py-4">
        <h2>資產查詢</h2>
        <p class="lead">....</p>
      </div>

      <div class="main">

        <div>
          <form @submit.prevent="formSubmit">
            <!-- line 1 -->
            <div class="form-inline ">
              <div class="form-group">
                <label for="iptAssetNo" class="col-form-label cus-label">資產編號</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="iptAssetNo" placeholder=""
                    v-model="tempAssetQueryForm.assetNo">
                </div>
              </div>

              <div class="form-group">
                <label for="iptAssetNoParent" class="col-form-label cus-label">主資產編號</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="iptAssetNoParent" placeholder=""
                    v-model="tempAssetQueryForm.assetNoParent">
                </div>
              </div>

              <div class="form-group">
                <label for="iptAssignee" class="col-form-label cus-label">保管人</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="iptAssignee" placeholder="" @click="openModalForEmp"
                    v-model="tempAssetQueryForm.assignee">
                </div>
              </div>

            </div>
            <br>

            <!-- line 2 -->
            <div class="form-inline ">

              <div class="form-group">
                <label for="input" class="col-form-label cus-label">存放地點</label>
                <div class="cus-input">
                  <select class="form-control" ref="selLocation" id="selLocation" name="location"
                    v-model="tempAssetQueryForm.location">
                    <option value="">請選擇</option>
                    <option v-for="item in defaultVal.locationList" :value="item" :key="item.seq">
                      {{item}}
                    </option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label for="input" class="col-form-label cus-label">資產類別</label>
                <div class="cus-input">
                  <select class="form-control" ref="selAssetType" id="selAssetType" name="assetType"
                    v-model="tempAssetQueryForm.assetType">
                    <option value="">請選擇</option>
                    <option v-for="item in defaultVal.assetTypeList" :value="item" :key="item.seq">
                      {{item}}
                    </option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-form-label cus-label">狀態</label>
                <div class="cus-input">
                  <select class="form-control" ref="selAssetType" id="selAssetType" name="assetType"
                    v-model="tempAssetQueryForm.assetType">
                    <option value="">請選擇</option>
                    <option v-for="item in defaultVal.statusList" :value="item" :key="item.seq">
                      {{item}}
                    </option>
                  </select>
                </div>
              </div>

            </div>
            <br>

            <!-- line 3 -->
            <div class="form-inline ">

              <div class="form-group">
                <label class="col-form-label cus-label">建立時間</label>
                <div class="cus-input">
                  <input type="date" class="form-control" placeholder="" v-model="tempAssetQueryForm.stDate"> ~
                  <input type="date" class="form-control" placeholder="" v-model="tempAssetQueryForm.ltDate">
                </div>
              </div>

              <div class="form-group">
                <label class="col-form-label cus-label">金額</label>
                <div class="cus-input">
                  <input type="text" class="form-control" placeholder="" v-model="tempAssetQueryForm.stAmount"> ~
                  <input type="text" class="form-control" placeholder="" v-model="tempAssetQueryForm.ltAmount">
                </div>
              </div>

            </div>
            <br>

            <div class="text-left">
              <b-button variant="primary" type="submit" style="width: 10em;">確定</b-button>
              <b-button variant="danger" @click="resetData" style="margin: 1em">清除</b-button>
            </div>
          </form>
        </div>

        <br>
        <!-- grid view -->
        <div>
          <table class="table table-sm table-hover">
            <thead class="thead-light">
              <tr>
                <th>資產編號</th>
                <th>狀態</th>
                <th>資產類別</th>
                <th>存放地點</th>
                <th>主資產編號</th>
                <th>保管人</th>
                <th>金額</th>
                <th>說明</th>
                <th>建立時間</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item) in gridViewData" :key="item.assetNo">
                <td> {{ item.assetNo }} </td>
                <td> {{ item.status }} </td>
                <td> {{ item.assetType }} </td>
                <td> {{ item.location }} </td>
                <td> {{ item.assetNoParent }} </td>
                <td> {{ item.assignee }} </td>
                <td> {{ item.amount }} </td>
                <td> {{ item.desc }} </td>
                <td> {{ item.crateTime }} </td>
                <td><button type="button" class="btn btn-outline-info btn-sm"
                    @click="getAttachDoc(item.assetNo)">詳情</button></td>
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
      this.getAllEmpIdAndName();
    },
    data() {
      return {
        tempAssetQueryForm: {
          assetNo: '',
          status: '',
          assetType: '',
          location: '',
          assetNoParent: '',
          assignee: '',
          desc: '',
          stAmount: '',
          ltAmount: '',
          stDate: '',
          ltDate: ''
        },

        defaultVal: {
          lastAssetNo: '',
          newAssetNo: '',
          assignee: '',
          assetTypeList: [],
          locationList: [],
          statusList: [],
        },

        gridViewData: [],
        empListData: [],
      }
    },
    methods: {

      openModalForEmp() {
        this.$bvModal.show('empModal')
      },

      chooseEmp(empData) {
        this.tempAssetQueryForm.assignee = empData.empId;
        this.$bvModal.hide('empModal')
      },

      getAttachDoc(id) {
        console.log(id);
        this.$router.push(`/asset_detail/${id}`);
      },
      formSubmit() {
        this.postQueryConditions();
      },
      resetData() {
        let docModel = this.tempAssetQueryForm;
        docModel.assetNo = '';
        docModel.status = '';
        docModel.assetType = '';
        docModel.location = '';
        docModel.assetNoParent = '';
        docModel.assignee = '';
        docModel.desc = '';
        docModel.stAmount = '';
        docModel.ltAmount = '';
        docModel.stDate = '';
        docModel.ltDate = '';
        this.gridViewData = [];
      },

      // 送出查詢條件
      postQueryConditions() {
        const url = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_ASSET}/queryAsset`;
        const vm = this;
        this.$http.post(url, vm.tempAssetQueryForm).then((response) => {
            if (response.data.success) {
              // 將回傳資料顯示再 gridView
              vm.gridViewData = response.data.result;
            }
          })
          .catch((error) => {

          })
      },

      getAllEmpIdAndName() {
        const api = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_EMP}/getAllEmpData`;
        const vm = this;

        this.$http.get(api).then((response) => {
            if (response.data.success) {
              vm.empListData = response.data.result;
            }
          })
          .catch((error) => {
            vm.errorNotify("系統參數取得失敗，請確認伺服器是否異常");
          })
      },

      // 取得系統參數
      getSystemparam() {
        const api = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_SYSTEM}/presetparam`;
        const vm = this;

        this.$http.get(api).then((response) => {
            if (response.data.success) {

              let values = response.data.result;
              console.log(values);
              values.assetType.forEach(function (item, index) {
                vm.defaultVal.assetTypeList.push(item.sysItemValue1)
              })
              values.status.forEach(function (item, index) {
                vm.defaultVal.statusList.push(item.sysItemValue1)
              })
              vm.defaultVal.locationList.push(values.location.sysItemValue1);
            }
          })
          .catch((error) => {
            vm.errorNotify("系統參數取得失敗，請確認伺服器是否異常");
          })
      },

      // 提示視窗
      successNotify(message) {
        this.$snotify.success(message);
      },
      warningNotify(message) {
        this.$snotify.warning(message);
      },
      errorNotify(message) {
        this.$snotify.error(message);
      },
    },
  }

</script>

<style lang="scss">



</style>
