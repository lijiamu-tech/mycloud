<template>
  <div id="app">
    <router-view v-if="isShow"/>
    <layout/>
  </div>
</template>

<script>
import Layout from "./components/Layout.vue";
export default {
  name: 'App',
  components:{
    Layout,
  },
  data(){
    return {
      isShow: true
    }
  },
  mounted() {
    // console.log(this.$route.path);
    // console.log(localStorage.getItem('token'));
    if(localStorage.getItem('token')){
      this.isShow = false;
    }
  }
}
</script>

<style>

</style>
