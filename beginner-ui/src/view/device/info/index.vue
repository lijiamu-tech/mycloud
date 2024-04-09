<template>
  <!--  el-container包含el-header或el-footer全部子元素垂直排列-->
  <el-container class="el-container">
    <el-header class="el-header">
      <el-form ref="form" :model="deviceForm"  label-width="80px" class="el-form-name">
        <el-form-item label="设备名称" label-width="80px" style="margin-top: 20px;margin-left: 20px;font-weight: bold">
          <el-input
            v-model="deviceForm.deviceName"
            placeholder="请输入设备名称"
            clearable
            class="el-input-name"></el-input>
        </el-form-item>
      </el-form>
      <el-row class="el-button-group">
        <el-button type="danger" class="el-button-select" @click='handleQuery'>查询</el-button>
        <el-button type="info" class="el-button-insert" @click='insert'>添加</el-button>
        <el-button type="primary" class="el-button-update" @click="update">修改</el-button>
        <el-button type="success" class="el-button-delete" @click="deleteDevice">删除</el-button>
        <el-button type="warning" class="el-button-reset" @click="resetQuery">重置</el-button>
      </el-row>
    </el-header>

    <el-main class="el-main">

      <!--      table-->
      <el-table
        ref="multipleTable"
        v-loading="loading"
        :data="deviceList"
        height="400"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="deviceId"
          label="设备编号"
          key = 'deviceId'
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="deviceName"
          label="设备名称"
          key = 'deviceName'
          width="100">
        </el-table-column>
        <el-table-column
          prop="deviceModel"
          key = 'deviceModel'
          label="设备型号"
          width="100">
        </el-table-column>
        <el-table-column
          prop="manufacturer"
          key = 'manufacturer'
          label="生产厂商"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="location"
          key = 'location'
          label="安装位置"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="currentStatus"
          key = 'currentStatus'
          label="设备状态"
          sortable
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="statusTime"
          key = 'statusTime'
          label="状态时间"
          sortable
          show-overflow-tooltip>
        </el-table-column>
      </el-table>

      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="this.queryParam.page"
          :page-sizes="this.queryParam.sizes"
          :page-size="this.queryParam.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-main>
    <el-footer v-show="false" class="el-footer">

    </el-footer>

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body class="el-dialog-user">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="el-form-user">
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备ID" prop="deviceId" style="width: 300px">
              <el-input v-model="form.deviceId" disabled  maxlength="20" placeholder="系统自动生成" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备名称" prop="deviceName" style="width: 300px">
              <el-input v-model="form.deviceName" placeholder="请输入设备名称" maxlength="20" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备型号" prop="deviceModel" style="width: 300px">
              <el-input v-model="form.deviceModel" placeholder="请输入设备型号" maxlength="16" minlength="6" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产厂商" prop="manufacturer" style="width: 300px">
              <el-input v-model="form.manufacturer" placeholder="请输入生产厂商" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="安装位置" prop="location" style="width: 300px">
              <el-input v-model="form.location" placeholder="请输入安装位置"  />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备状态" prop="currentStatus" style="width: 300px">
              <el-input v-model="form.currentStatus" placeholder="请输入设备状态"  />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import {queryAll,query, queryByPage, updateDevice, deleteDevice, addDevice} from "../../../api/device/info/deviceInfo";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 最大ID
      maxID: 0,
      // 是否显示弹出层
      open: false,
      // 弹出层标题
      title: "",
      // 用户表格数据
      deviceList: null,
      // 表单参数
      form: {},
      // 分页查询requestPageable
      queryParam: {
        //当前页数
        page: 1,
        //每页显示数
        size: 10,
        //每页显示数选项
        sizes: [10, 20],
      },
      // 用户查询模板
      deviceForm: {
        statusId: undefined,
        deviceId: undefined,
        deviceName: undefined,
        deviceModel: undefined,
        manufacturer: undefined,
        location: undefined,
        currentStatus: undefined,
        StatusTime: undefined
      },
      rules: {
        deviceName: [
          {required: true, message: "设备名称不能为空", trigger: "blur"}
        ],
        manufacturer: [
          {required: true, message: "生产厂商不能为空", trigger: "blur"}
        ],
        currentStatus: [
          {required: true, message: "设备状态不能为空", trigger: "blur"}
        ],
        deviceModel: [
          {required: true, message: "设备型号不能为空", trigger: "blur"}
        ]
      }
    }
  },
  created() {
    this.getList();
  },
  methods:{
    getList(){
      this.loading = true;
      query(this.deviceForm).then(res =>{
        this.deviceList = res.result.records;
        this.queryParam.page = 1;
        this.total = res.result.total;
        this.loading = false;
      })
    },
    getListPage() {
      this.loading = true;
      queryByPage(this.deviceForm,
                  this.queryParam.page,
                  this.queryParam.size).then(res =>{
        this.deviceList = res.result.records;
        this.total = res.result.total;
        this.loading = false;
      })
    },
    insert(){
      this.reset();
      this.open = true;
      this.title = "添加设备信息";
    },
    update(){
      if (this.ids.length > 1){
        this.$message("请选择一条数据进行编辑")
      }else {
        this.reset();
        this.form.statusId = this.ids[0]
        queryAll(this.form).then(response =>{
          console.log(response);
          this.form =  response.result[0];
          this.open = true;
          this.title = "修改设备信息";
        })
      }
    },
    deleteDevice(){
      this.$confirm('是否确认删除选中的数据项？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function (){
        return deleteDevice(this.ids);
      }).then(() =>{
        this.$notify({
          title: "成功",
          message: "删除成功！",
          type: "success",
          duration: 1500
        });
        this.getList();
      }).catch(() =>{
        this.$notify({
          title: "警告",
          message: "已取消删除！",
          type: "warning",
          duration: 1500
        });
      })
    },
    handleSizeChange(row){
      this.queryParam.size = row;
      this.getListPage();
    },
    handleCurrentChange(row){
      this.queryParam.page = row;
      this.getListPage();
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        statusId: undefined,
        deviceId: undefined,
        deviceName: undefined,
        deviceModel: undefined,
        manufacturer: undefined,
        location: undefined,
        currentStatus: undefined,
        StatusTime: undefined
      };
      // console.log('form');
      // console.log(this.$refs["form"]);
      this.resetForm("form");
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("deviceForm");
      this.handleQuery();
    },
    resetForm(refName) {
      // console.log('1');
      if (this.$refs[refName]) {
        this.$refs[refName].resetFields();
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParam.page = 1;
      this.getList();
      this.$notify({
        title: "成功",
        message: "查询成功",
        type: "success",
        duration: 1500
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.statusId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.statusId !== undefined) {
            updateDevice(this.form).then(response => {
              this.$notify({
                title: "成功",
                message: "修改成功",
                type: "success",
                duration: 1500
              });
              this.open = false;
              this.resetQuery();
            });
          } else {
            addDevice(this.form).then(response => {
              this.$notify({
                title: "成功",
                message: "添加成功",
                type: "success",
                duration: 1500
              });
              this.open = false;
              this.resetQuery();
            });
          }
        }
      });
    },
  }
}
</script>
<style scoped>
.el-input-name{
  width: 200px;
}
.el-form-name{
  float: left;
}
.el-button-group{
  float: right;
  margin-top: 25px;
}
.block{
  float: right;
}
</style>
