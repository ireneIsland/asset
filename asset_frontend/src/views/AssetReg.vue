<template>
  <div>
    
    <loading :active.sync="isLoading"></loading>
    <div class="container">

      <div class="py-4">
        <h2>資產入帳</h2>
        <p>資產定義：1000元的物品</p>
      </div>
      <form @submit.prevent="formSubmit">
        <div class="row text-left">

          <!-- 左邊表單 -->
          <div class="col-md-5">
            <button type="button" class="btn btn-outline-info" @click="addNewAttachDocForm">+</button><span style="margin-left: 1em;">增加附屬文件</span>
            <hr>
            <div class="card mb-3" v-for="(item, index) in tempAsset.attachDocList" :key="index">
              <div class="card-body">
                <span class="btn-delete-x float-right" @click="deleteAttachDocForm(index)">X</span>
                <h5 class="card-title">附屬文件 ({{index + 1}})</h5>

                  <div class="form-group">
                    <Asterisk/><label for="iptAttachName">物品名稱</label> 
                    <input type="text" class="form-control" id="iptAttachName" v-model="item.docName" required>
                    <div class="invalid-feedback">
                      請輸入名稱
                    </div>
                  </div>
                  <div class="form-group">
                    <label style="margin-right: 2px;">上傳圖片</label><i class="fas fa-spinner fa-spin" v-if="tempAsset.attachDocList[index].fileUploading"></i>
                    <input type="file" class="form-control-file" ref="mFiles" id="iptFile" @change="eventploadFile(index)">
                  </div>
                  <div class="form-group">
                    <textarea class="form-control mb-3" placeholder="說明" id="txtDesc" v-model="item.desc"></textarea>
                  </div>

              </div>
            </div>


          </div>

          <!-- 空一列 -->
          <div class="col-md-1"></div>

          <!-- 右邊表單 -->
          <div class="col-md-6">
            <div class="form-group">
              <label for="iptAmount">金額</label>
              <input type="text" class="form-control" id="iptAmount" v-model="tempAsset.amount">
              <div class="invalid-feedback">
                請輸入金額
              </div>
            </div>

            <div class="form-group">
              <Asterisk/>
              <label for="iptAssignee">保管人<span class="text-muted">(預設為總務)</span></label>
              <input type="text" class="form-control" name="assignee" id="iptAssignee" 
                v-model="tempAsset.assignee"
                v-validate="'required'"
                :class="{'is-invalid':errors.has( 'assignee' )}">
              <span class="text-danger" v-if="errors.has( 'assignee' )">保管人不得為空</span>
            </div>

            <br>
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="ckbIsParent" v-model="isParent" @click="settingParent">
              <label class="form-check-label" for="ckbIsParent">
                是否為主要資產
              </label>
            </div>
            <br>

            <div class="form-group">
              <label for="iptassetNoParent">主資產編號</label>
              <input type="text" class="form-control" id="iptassetNoParent" v-model="tempAsset.assetNoParent" :disabled="isParent">
            </div>

            <div class="form-group">
              <label for="selAssetType">資產類別</label>
              <select class="form-control" id="selAssetType" name="assetType"
                v-validate="'required'"
                v-model="tempAsset.assetType">
                <!-- <option disabled value="">請選擇</option> -->
                <option v-for="item in assetTypeList" :value="item" :key="item.seq">
                  {{item}}
                </option>
              </select>
              <span class="text-danger" v-if="errors.first('assetType')">保管人不得為空</span>
            </div>

            <div class="form-group">
              <Asterisk/><label for="selLocation">存放地點</label>
              <select class="form-control" id="selLocation" v-model="tempAsset.location" required>
                <option disabled value="">請選擇</option>
                <option v-for="item in locationList" :value="item" :key="item.seq">
                  {{item}}
                </option>
              </select>
            </div>

            <div class="form-group">
              <label for="txtDesc">說明</label>
              <textarea class="form-control" id="txtDesc" v-model="tempAsset.desc"></textarea>
            </div>
            <hr class="mb-4">
            <button type="submit" class="btn btn-primary" style="width: 6em;">登記</button>
            <button class="btn btn-danger" @click="resetData" style="margin-left: 1em;">清除</button>
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
        fileUploading: false,
        
        assetTypeList: [],
        locationList: [],
        uploadFileList: [],

        assetNo: '',
        
        // Asset MODEL
        tempAsset: {
          lmUser: 'is0240',
          status: '庫存',
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
        attachDocList.forEach(function(item, index){
          attachDocList[index].seq = index+1;
        })
      },

      // 刪除副署文件表單
      deleteAttachDocForm(index) {
        this.tempAsset.attachDocList.splice(index, 1)
      },

      // 圖片上傳
      eventploadFile(index) {        
        let seq = index+1;
        let vm = this;
        this.uploadFileList.push({
          file: vm.$refs.mFiles[index].files[0],
          seq: seq
        })
      },

      // 表單送出
      formSubmit() {
        this.$validator.validate().then(valid => {
          if (!valid) {
            his.reqUploadFile();
          }
        });

      },

      reqUploadFile() {
        const url = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_ASSET}/upload`;
        const vm = this;
        const formData = new FormData();
        let doc = this.tempAsset.attachDocList;

        this.uploadFileList.forEach((item) => {
          formData.append('file', item.file);
          formData.append('seq', item.seq)
        })
          
        formData.append('assetNo', parseInt(vm.assetNo)+1);

        this.$http.post(url, formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
        }).then((response) => {

          if(response.data.success) {
            let datas = response.data.result;

            doc.forEach((item, i) => {
              for(var j=0; j<datas.length; j++) {
                if(item.seq == datas[j].seq) {
                  item.path = datas[j].path
                }
              }
            })
          }
          // 呼叫註冊function
        vm.reqAssetReg();
        })
      },

      // 資產註冊
      reqAssetReg(data) {
        this.isLoading = true;
        const url = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_ASSET}/regAsset`;
        const vm = this;
        this.$http.post(url, vm.tempAsset).then((response) => {
          
          if(response.data.success) {
            vm.successNotify("資產編號:" + response.data.result.assetNo + "登入完畢");
            vm.resetData();
          }
        })
        .catch((error) => {
            vm.errorNotify("資產登入失敗");
          })
        this.isLoading = false;
      },

      // 取得系統參數
      getSystemparam() {
        const api = `${process.env.VUE_APP_APIPATH}/${process.env.VUE_APP_SYSTEM}/presetparam`;
        const vm = this;


          vm.$http.get(api).then((response) => {
            if(response.data.success){
              let values = response.data.result;
              values.assetType.forEach(function(item, index, array){
                vm.assetTypeList.push(item.sysItemValue1)
              })
              vm.locationList.push(values.location.sysItemValue1);
              vm.tempAsset.assignee = values.assignee.sysItemValue1;
              vm.assetNo = values.assetNoEnd.sysItemValue1;
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
      errorNotify(message) {
        this.$snotify.error(message);
      },
      settingParent() {
          this.tempAsset.assetNoParent = '';
      },

      // 重置欄位資料
      resetData() {
        this.tempAsset.assetType = '';
        this.tempAsset.desc = '';
        this.tempAsset.assetNoParent = '';
        this.tempAsset.amount = '';
        this.tempAsset.location = '';
        this.tempAsset.assignee = '';
        this.tempAsset.attachDocList = [];
        this.assetTypeList = [];
        this.locationList = [];
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
    cursor:pointer;
    border: 1px solid rgb(114, 114, 114);
  }
</style>