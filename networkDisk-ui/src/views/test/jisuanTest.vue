<template>
  <div>
    <el-table v-model="datalist">
      <el-table-column label="原名" align="center" prop="age" />
      <el-table-column prop="unitPrice" align="center" label="税率(%)" width="120">
        <template slot-scope="scope">
          <el-select v-model="scope.ob" placeholder="请选择税率" @change="changeUnitPrice(scope.row)">
<!--            <el-option v-for="item in rateList" :label="item.label" :value="item.value"-->
<!--                       :key="item.value"></el-option>-->
            <el-option v-for="" :label="1" :value="2"
                       :key="3"></el-option>
          </el-select>
          <!-- <el-input v-model="scope.row.tax" placeholder="请输入税率(%)" @change="changeUnitPrice(scope.row)"></el-input> -->
        </template>
      </el-table-column>
    </el-table>
    <el-table v-model="datalist">
      <el-table-column label="原名" align="center" prop="int" />
      <el-table-column label="计算结果" align="center" prop="" >
        <template slot-scope="scope">
          {{this.data.jisuan}}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// 参数池
export default {
  data(){
      return {
        datalist: [{age:1,int:10},{age:3,int:12}],
        form:{
        },
      }
  },
  methods:{//响应式参数池
  },
  changeUnitPrice(row) {
  },
  reset() {
    this.form = {
      age: 10,
      int: 10,
      jisuan: 0,
      ob: "",
    };
    this.resetForm("form");
  },
  datatest() {
}
};

</script>

<style scoped lang="scss">

</style>


<!--该文件为测试文件-->
