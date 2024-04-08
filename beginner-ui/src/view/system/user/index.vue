<template>
<!--  el-container包含el-header或el-footer全部子元素垂直排列-->
  <el-container class="el-container">


    <el-header class="el-header">
      <el-form ref="form" :model="userForm"  label-width="80px" class="el-form-name">
        <el-form-item label="用户名称" label-width="80px" style="margin-top: 20px;margin-left: 20px;font-weight: bold">
          <el-input
            v-model="userForm.userName"
            placeholder="请输入用户名称"
            clearable
          class="el-input-name"></el-input>
        </el-form-item>
      </el-form>
      <el-row class="el-button-group">
        <el-button type="danger" class="el-button-select" @click='handleQuery'>查询</el-button>
        <el-button type="info" class="el-button-insert" @click='addUser'>添加</el-button>
        <el-button type="primary" class="el-button-update" @click="updateUser">修改</el-button>
        <el-button type="success" class="el-button-delete" @click="deleteUser">删除</el-button>
        <el-button type="warning" class="el-button-reset" @click="resetQuery">重置</el-button>
      </el-row>
    </el-header>


    <el-main class="el-main">

<!--      table-->
        <el-table
          ref="multipleTable"
          v-loading="loading"
          :data="userList"
          height="400"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="userId"
            label="用户编号"
            key = 'userId'
            sortable
            width="100">
          </el-table-column>
          <el-table-column
            prop="userName"
            label="姓名"
            key = 'userName'
            width="100">
          </el-table-column>
          <el-table-column
            prop="userPwd"
            key = 'userPwd'
            label="密码"
            width="100">
          </el-table-column>
          <el-table-column
            prop="email"
            key = 'email'
            label="邮箱"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="phone"
            key = 'phone'
            label="手机号"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="createdTime"
            key = 'createdTime'
            label="创建时间"
            sortable
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="updateTime"
            key = 'updateTime'
            label="修改时间"
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
            <el-form-item label="用户ID" prop="userId" style="width: 300px">
              <el-input v-model="form.userId" disabled  maxlength="20" placeholder="系统自动生成" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名称" prop="userName" style="width: 300px">
              <el-input v-model="form.userName" placeholder="请输入用户名称" maxlength="20" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户密码" prop="userPwd" style="width: 300px">
              <el-input v-model="form.userPwd" placeholder="请输入密码" maxlength="16" minlength="6" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone" style="width: 300px">
              <el-input v-model="form.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email" style="width: 300px">
              <el-input v-model="form.email" placeholder="请输入邮箱"  />
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

import {
  query,
  addUser,
  delUser,
  queryAll,
  queryByPage,
  updateUser,
} from "../../../api/system/user/user";
// import {resetForm} from "../../../utils/format";
import 'element-ui/lib/theme-chalk/index.css';

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
      userList: null,
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
      userForm: {
        userId: undefined,
        userName: undefined,
        userPwd: undefined,
        email: undefined,
        phone: undefined,
        createdTime: undefined,
        updateTime: undefined
      },
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        userPwd: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 6 和 16 之间', trigger: 'blur' }
        ],
        phoneNumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    handleSizeChange(val){
      this.queryParam.size = val;
      this.getListPage();
    },
    handleCurrentChange(val){
      this.queryParam.page = val;
      this.getListPage();
    },
    //表单多选框
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
    });
    } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    getListPage(){
      this.loading = true;
      queryByPage(this.userForm,
                  this.queryParam.page,
                  this.queryParam.size)
        .then(res =>{
          console.log(res);
          this.userList = res.result.records;
          this.total = res.result.total;
          this.loading = false;
      })
    },
    getList(){
      this.loading = true;
      queryAll(this.userForm).then(res => {
        this.userList = res.result.records;
        this.queryParam.page = 1;
        this.total = res.result.total;
        this.loading = false;
      })
    },
    addUser(){
      this.reset();
      this.open = true;
      this.title = "添加用户信息";
    },
    updateUser(row){
      const userId = row.userId || this.ids;
      if (this.ids.length > 1){
        this.$message("请选择一条数据进行编辑")
      }else {
        this.reset();
        this.form.userId = this.ids[0]
        // console.log(this.userForm);
        query(this.form).then(response =>{
          this.form =  response.result[0];
          this.open = true;
          this.title = "修改用户信息";
        })
      }
    },
    deleteUser(row){

    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        userName: undefined,
        userPwd: undefined,
        phone: undefined,
        email: undefined,
        createdTime: undefined,
        updatedTime: undefined
      };
      // console.log('form');
      // console.log(this.$refs["form"]);
      this.resetForm("form");
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("userForm");
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
      this.queryParam.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId !== undefined) {
            updateUser(this.form).then(response => {
              this.$message("修改成功");
              this.open = false;
              this.resetQuery();
            });
          } else {
            addUser(this.form).then(response => {
              this.$message("新增成功");
              this.open = false;
              this.resetQuery();
            });
          }
        }
      });
    },

  },
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
