<template>
  <div class="home">
    <div class="container-fluid">

      <div class="py-4">
        <h2>資產查詢</h2>
        <p class="lead">....</p>
      </div>

      <div class="main">

        <div>
          <form>
            <!-- line 1 -->
            <div class="form-inline ">
              <div class="form-group">
                <label for="input" class="col-form-label">資產編號</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="input" placeholder="">
                </div>
              </div>

              <div class="form-group">
                <label for="input" class="col-form-label">主資產編號</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="input" placeholder="">
                </div>
              </div>

              <div class="form-group">
                <label for="input" class="col-form-label">保管人</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="input" placeholder="">
                </div>
              </div>

            </div>
            <br>

            <!-- line 2 -->
            <div class="form-inline ">
              
              <div class="form-group">
                <label for="input" class="col-form-label">狀態</label>
                <div class="cus-input">
                  <!-- <input type="text" class="form-control" id="input" placeholder="XXXX"> -->
                  <b-form-select v-model="selected" :options="options"></b-form-select>
                </div>
              </div>

              <div class="form-group">
                <label for="input" class="col-form-label">資產類別</label>
                <div class="cus-input">
                  <!-- <input type="text" class="form-control" id="input" placeholder=""> -->
                  <b-form-select v-model="selected" :options="options"></b-form-select>
                </div>
              </div>

              <div class="form-group">
                <label for="input" class="col-form-label">存放地點</label>
                <div class="cus-input">
                  <!-- <input type="text" class="form-control" id="input" placeholder="XXXX"> -->
                  <b-form-select v-model="selected" :options="options"></b-form-select>
                </div>
              </div>
            </div>
            <br>

            <!-- line 3 -->
            <div class="form-inline ">
              <div class="form-group">
                <label for="input" class="col-form-label">金額</label>
                <div class="cus-input">
                  <input type="text" class="form-control" id="input" placeholder="0"> ~
                  <input type="text" class="form-control" id="input" placeholder="">
                </div>
              </div>
            </div>
            <br>

            <!-- line 4 -->
            <div class="form-inline ">
              <div class="form-group">
                <label for="input" class="col-form-label">建立時間</label>
                <div class="cus-input">
                  <input type="date" class="form-control" id="input" placeholder="0"> ~
                  <input type="date" class="form-control" id="input" placeholder="">
                </div>
              </div>
            </div>
            <br>

            <div class="text-left">
              <b-button variant="primary" style="width: 10em;">確定</b-button>
              <b-button variant="danger" style="margin: 1em">reset</b-button>
            </div>
          </form>
        </div>

        <br>
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
                <th>最後修改時間</th>
                <th>最後修改人員</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
              </tr>
              <tr>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
              </tr>
              <tr>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
              </tr>
              <tr>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
                <td> - </td>
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
    data() {
      return {
        selected: null,
        options: [{
            value: null,
            text: '請選擇'
          },
          {
            value: 'a',
            text: 'This is First option'
          },
          {
            value: 'b',
            text: 'Selected Option'
          },
          {
            value: {
              C: '3PO'
            },
            text: 'This is an option with object value'
          },
          {
            value: 'd',
            text: 'This one is disabled',
            disabled: true
          }
        ]
      }
    }
  }

</script>

<style scoped lang="scss">

</style>
