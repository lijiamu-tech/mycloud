<!-- Layout.vue -->
<template>
  <div class="layout" v-show="isAuthenticated">
    <div class="sidebar">
      <el-tree
        :data="treeData"
        :node-key="treeData.key"
        :default-expand-all="true"
        :expand-on-click-node="true"
        :highlight-current="true"
        :default-expanded-keys="[activeMenu]"
        @node-click="handleNodeClick">
      </el-tree>
    </div>
    <div class="main">
      <div class="myHeader">
        <div class="el-div-header">设备管理系统</div>
        <el-button class="el-button-logout" v-show="isAuthenticated" @click="logout">
          <p class="zhuxiao">注销</p>
        </el-button>
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Layout',
  data(){
    return {
      isAuthenticated: false,
      // 导航栏显示内容
      activeMenu: '1', // 当前选中的菜单项
      treeData: [ // 左侧导航栏的菜单项数据
        {
          id: '1',
          label: '首页',
          key: '/home '
        },
        {
          id: '2',
          label: '系统管理',
          children: [
            { id: '21', label: '用户管理', key: '/system/user' },
            { id: '22', label: '权限管理', key: '/system/role' }
          ]
        },
        {
          id: '3',
          label: '设备管理',
          children: [
            { id: '21', label: '设备信息', key: '/device/info' },
            { id: '22', label: '入库信息', key: '/device/storage/info' }
          ]
        },
      // 其他父菜单项
      ]
    };
  },
  mounted() {
    // console.log(1);
    if(!this.isAuthenticated){
      // console.log(this.isAuthenticated);
      if (localStorage.getItem("refresh")){
        // console.log(localStorage.getItem('refresh'))
        window.location.reload();
        localStorage.removeItem('refresh');
      }
    }
  },
  created() {
    // 检查用户是否登录，假设登录状态保存在 localStorage 中
    this.isAuthenticated = !!localStorage.getItem('token');
    this.$router.beforeEach((to, from, next) => {
      // 每次路由变化时检查登录状态
      if (to.matched.some(record => record.meta.requiresAuth) && !this.isAuthenticated) {
        next('/');
      } else {
        next();
      }
    });
  },
  methods: {
    logout() {
      // 清除 localStorage 中的 token
      localStorage.removeItem('token');
      // 更新登录状态为未登录
      this.isAuthenticated = false;
      // 可以跳转到登录页面或者其他页面
      this.$router.push('/');
      window.location.reload();
    },
    // 导航栏点击时间
    handleMenuSelect(index) {
      this.activeMenu = index;
      // 处理菜单项点击事件的逻辑，例如跳转到对应的页面
      // console.log('选中的菜单项ID:', index);
    },
    handleNodeClick(data) {
      this.activeMenu = data.id;
      this.path = data.key;
      this.$router.push(this.path);
      // 处理菜单项点击事件的逻辑，例如跳转到对应的页面
      // console.log('选中的菜单项ID:', data.id);
    },

  }
};
</script>

<style >
.layout {
  display: flex;
}
.myHeader{
  background-color: #304156;
  width: 100%;
  height: 40px;
  border-left: 1px solid #f0f0f0;
  border-radius: 0px 10px 0px 0px;
  padding-top: 15px;
}
.el-button-logout{
  margin-top: -27px;
  margin-right: 5px;
  width: 60px;
  height: 30px;
  float: right;
  background-color: #619fd2;
  border-radius: 15px;
  border: 0px;
}
.zhuxiao{
  margin-top: -4px;
  margin-left: 6%;
  color: #f0f0f0;
  font-weight: bold;
}
.el-div-header{
  margin-left: 40%;
  font-size: 20px;
  color: #f0f0f0;
}
.sidebar {
  flex: 0 0 200px; /* 固定宽度的侧边栏 */
  background-color: #f0f0f0;
}
.custom-icon {
  margin-right: 10px; /*调整图标与文字之间的间距 */
}
.menu-text{
  line-height: 60px;  /*调整文字的垂直居中 */
}
.expanded {
  display: inline-block;
}
.el-tree{
  height: 625px;
}

.el-tree-node__label{
  margin-left: 20px;
  font-size: 17px;
}
.el-tree-node{
  padding-top: 14px;
}
.el-tree-node__content{
  width: 150px;
  height: 45px;
  color: #909eaf;
}
.el-tree-node-select{
  color: #3a87c7;
}
.el-tree{
  background-color: #304156;
  border-radius: 10px 0px 0px 10px;
}
.el-tree-node__children{
  background-color: #1f2d3d;
}
.main {
  flex: 1; /* 自适应宽度的主内容区 */
}

.el-tree, .header{
  display: block;
}
</style>
