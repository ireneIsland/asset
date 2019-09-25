<template>
  <div>
    
    <!-- LOADING IMAGE -->
    <loading :active.sync="isLoading"></loading>

      <!-- MODAL -->
      <b-modal id="confirmRegAsset" title="Confirm" centered  size="sm" @ok="handleOk">
        <p>確定要送出?</p>
      </b-modal>

    <div class="container">

      <div class="py-4">
        <h2>資產入帳</h2>
        <p>資產定義：1000元的物品</p>
      </div>
      <form class="text-left" @submit.prevent="formSubmit">
        <div class="row">

          <!-- 左邊表單 -->
          <div class="col-md-6">
            <div class="form-group">
              <label for="iptAmount">金額</label>
              <input type="number" class="form-control" id="iptAmount" pattern="[0-9]*" min="0" v-model="tempAsset.amount">
            </div>

            <div class="form-group">
              <Asterisk />
              <label for="iptAssignee">保管人<span class="text-muted">(預設為總務)</span></label>
              <input type="text" class="form-control" name="assignee" id="iptAssignee" v-model="tempAsset.assignee"
                v-validate="'required'" :class="{'is-invalid':errors.has( 'assignee' )}">
              <span class="text-danger" v-if="errors.has( 'assignee' )">保管人不得為空</span>
            </div>

            <br>
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="ckbIsParent" v-model="isParent"
                @click="settingParent">
              <label class="form-check-label" for="ckbIsParent">
                是否為主要資產
              </label>
            </div>
            <br>

            <div class="form-group">
              <label for="iptassetNoParent">主資產編號</label>
              <input type="text" class="form-control" id="iptassetNoParent" v-model="tempAsset.assetNoParent"
                :disabled="isParent">
            </div>

            <div class="form-group">
              <label for="selAssetType">資產類別</label>
              <select class="form-control" ref="selAssetType" id="selAssetType" name="assetType" v-model="tempAsset.assetType">
                <option disabled value="">請選擇</option>
                <option v-for="item in defaultVal.assetTypeList" :value="item" :key="item.seq">
                  {{item}}
                </option>
              </select>
            </div>

            <div class="form-group">
              <Asterisk /><label for="selLocation">存放地點</label>
              <select class="form-control" ref="selLocation" id="selLocation" name="location" v-model="tempAsset.location"
                v-validate="'required'" 
                :class="{'is-invalid':errors.has( 'location' )}">
                <option disabled value="">請選擇</option>
                <option v-for="item in defaultVal.locationList" :value="item" :key="item.seq">
                  {{item}}
                </option>
              </select>
              <span class="text-danger" v-if="errors.has('location')">請選擇存放地點</span>
            </div>

            <div class="form-group">
              <label for="txtDesc">說明</label>
              <textarea class="form-control" id="txtDesc" v-model="tempAsset.desc"></textarea>
            </div>

            <hr class="mb-4">
            <button type="submit" class="btn btn-primary" style="width: 10em;">登記</button>
            <button type="button" class="btn btn-danger" @click="resetData" style="margin-left: 1em;">清除</button>
          </div>

          <!-- 空一列 -->
          <div class="col-md-1"></div>

          <!-- 右邊表單 -->
          <div class="col-lg-5">
            <br>
            <button type="button" class="btn btn-outline-info" @click="addNewAttachDocForm">+</button><span
              style="margin-left: 1em;">增加附屬文件</span>
            <hr>
            <div class="card mb-3" v-for="(item, index) in tempAsset.attachDocList" :key="index">
              <div class="card-body">
                <span class="btn-delete-x float-right" @click="deleteAttachDocForm(index)">X</span>
                <h5 class="card-title">附屬文件 ({{index + 1}})</h5>

                <div class="form-group">
                  <Asterisk /><label for="iptAttachName">物品名稱</label>
                  <input type="text" class="form-control" id="iptAttachName" name="attachName" v-model="item.docName"
                    v-validate="'required'" :class="{'is-invalid':errors.has( 'attachName' )}">
                  <span class="text-danger" v-if="errors.has( 'attachName' )">物品名稱不得為空</span>
                </div>

                <div class="form-group">
                  <label for="iptFile" class="btn btn-info"><i class="fas fa-file-upload"></i>&nbsp; 檔案上傳</label> 
                  <strong v-if="uploadFileList[index]"><span> {{uploadFileList[index].fileName}} </span></strong>
                  <input type="file" class="form-control-file" ref="mFiles" id="iptFile"
                    @change="eventUploadFile(index)" hidden>
                </div>
                <div class="form-group">
                  <textarea class="form-control mb-3" placeholder="說明" id="txtDesc" v-model="item.desc"></textarea>
                </div>

              </div>
            </div>

          </div>

        </div>
      </form>
    </div>

  </div>
</template>

<script>
  import Asterisk from '@/components/Asterisk.vue';
  export default {
    // initial
    created() {
      this.getSystemparam();
    },
    data() {
      return {
        isLoading: false,
        isParent: false,

        uploadFileList: [],

        defaultVal: {
          lastAssetNo: '',
          newAssetNo: '',
          assignee: '',
          assetTypeList: [],
          locationList: [],
        },

        // Asset MODEL
        tempAsset: {
          assetNo: '',
          lmUser: 'is0240',
          status: '',
          assetType: '',
          desc: '',
          location: '',
          assetNoParent: '',
          assignee: '',
          amount: '',
          attachDocList: [],
        },
      };
    },
    methods: {
      // 新增一個附屬文件表單
      addNewAttachDocForm() {
        let attachDocList = this.tempAsset.attachDocList;
        attachDocList.push({
          seq: 0,
          docName: '',
          path: '',
          desc: '',
        })
        attachDocList.forEach(function (item, index) {
          attachDocList[index].seq = index + 1;
        })
      },

      // 刪除副署文件表單
      deleteAttachDocForm(index) {
        this.tempAsset.attachDocList.splice(index, 1)
      },

      // 圖片上傳
      eventUploadFile(index) {
        let seq = index + 1;
        let vm = this;
        let file = this.$refs.mFiles[index].files[0];
        let type = file.type.substring(0,file.type.indexOf('/'));
 
        // 檢查檔案格式
        if(type != 'image') {
            vm.warningNotify("請上傳圖片檔案格式");
            this.$refs.mFiles[index].value = '';
            return;
        }

        // 圖片限制 2M
        let max_size = 2 * (1024 * 1024); // 2mb
        let p_size = 0;
        p_size = file.size;//byte
        if(p_size > max_size) {
              vm.warningNotify("圖檔超過 2mb 請壓縮後再上傳");
              this.$refs.mFiles[index].value = '';
              return;
        }

        this.uploadFileList.push({
          fileName: file.name,
          file: file,
          seq: seq
        })
      },

      // 表單送出
      formSubmit() {
        this.$validator.validate().then(valid => {
          if (valid) {
            this.confirm();
          }
        });
      },

      handleOk() {
        this.isLoading = true;
        this.getNewAssetNo();
        
      },

      getNewAssetNo() {
        let lastAssetNo = this.defaultVal.lastAssetNo;
        const url = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_SYSTEM}/newassetno/?assetNo=${lastAssetNo}`;
        const vm = this;
        this.$http.get(url).then((response) => {
            if (response.data !== undefined) {
              vm.tempAsset.assetNo = response.data;
              console.log(vm.tempAsset.assetNo);
              vm.requestHandler();
            }
          })
          .catch((error) => { 
            vm.errorNotify("系統流水號更新失敗，請確認伺服器是否異常");
            this.isLoading = false;
          })
      },

      requestHandler() {
        if(this.uploadFileList.length > 0 && this.uploadFileList !== undefined) {
          this.postUploadFile();
        } else {
          this.postAssetReg();
        }
      },

      postUploadFile() {
        const url = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_ASSET}/upload`;
        const vm = this;
        const formData = new FormData();
        let doc = this.tempAsset.attachDocList;

        this.uploadFileList.forEach((item) => {
          formData.append('file', item.file);
          formData.append('seq', item.seq)
        })

        formData.append('assetNo', vm.tempAsset.assetNo);

        this.$http.post(url, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        }).then((response) => {

          if (response.data.success) {
            let datas = response.data.result;
            datas.assetNo = this.tempAsset.assetNo
            doc.forEach((item, i) => {
              for (var j = 0; j < datas.length; j++) {
                if (item.seq == datas[j].seq) {
                  item.path = datas[j].path
                }
              }
            })
          }
          // 呼叫註冊function
          vm.postAssetReg();
        })
      },

      // 資產註冊
      postAssetReg() {
        const url = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_ASSET}/regAsset`;
        const vm = this;
        this.$http.post(url, vm.tempAsset).then((response) => {

            if (response.data.success) {
              vm.successNotify("資產編號:" + response.data.result.assetNo + "登入完畢");
              vm.resetData();
            } else {
              vm.errorNotify("資產登入失敗");
            }
          })
          .catch((error) => {
            vm.errorNotify("系統異常 請聯絡管理員");
          })
        this.isLoading = false;
      },

      // 取得系統參數
      getSystemparam() {
        const api = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_SYSTEM}/presetparam`;
        const vm = this;

        this.$http.get(api).then((response) => {
            if (response.data.success) {
              let values = response.data.result;
              values.assetType.forEach(function (item, index, array) {
                vm.defaultVal.assetTypeList.push(item.sysItemValue1)
              })
              vm.defaultVal.locationList.push(values.location.sysItemValue1);
              vm.defaultVal.assignee = values.assignee.sysItemValue1;
              vm.tempAsset.assignee = values.assignee.sysItemValue1;
              vm.defaultVal.lastAssetNo = values.assetNoEnd.sysItemValue1;
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
      confirm() {
        this.$bvModal.show('confirmRegAsset')
      },

      settingParent() {
        this.tempAsset.assetNoParent = '';
      },

      // 重置欄位資料
      resetData() {
        this.isParent = false;
        this.tempAsset.assetType = '';
        this.tempAsset.desc = '';
        this.tempAsset.assetNoParent = '';
        this.tempAsset.amount = '';
        this.tempAsset.location = '';
        this.tempAsset.assignee = this.defaultVal.assignee;
        this.tempAsset.attachDocList = [];
        this.defaultVal.locationList = [];
        this.defaultVal.assetTypeList = [];
        this.uploadFileList = [];
        this.getSystemparam();
      },
    },
    components: {
      Asterisk
    },
  }

</script>

<style scoped lang="scss">
  .btn-delete-x {
    padding: 5px;
    padding-top: 0;
    padding-bottom: 0;
    color: rgb(114, 114, 114)
  }

  .btn-delete-x:hover {
    cursor: pointer;
    border: 1px solid rgb(114, 114, 114);
  }

  #modal-container {
    font-size:18px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif, 'Microsoft JhengHei';
  }

</style>
