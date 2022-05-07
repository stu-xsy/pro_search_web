<template>
  <div>
    <div class="box2">
      <div class="searchBox2">
        <input type="text" v-model="keyword" placeholder="请输入关键词" class="searchInput2" style="padding-left: 14px">
        <input type="button"  @click="_search" value="搜索" class="searchButton2">
        <input type="button" @click="_refer" value="推荐"  class="referButton2">
      </div>
    </div>
  </div>

</template>
<style>
.box2{
  margin: 0 auto;
  padding-top: 20px;
  height: 50px;
  width: 100%;
}
.searchBox2{
  margin: 0 auto;
  width: 60%;
  position: relative;
}
.searchInput2{
  display: inline-block;
  width: 80%;
  height: 50px;
  border: 1px solid #cccccc;
  float: left;
  box-sizing: border-box;
  -moz-box-sizing:border-box; /* Firefox */
  -webkit-box-sizing:border-box; /* Safari */
  border-bottom-left-radius: 5px;
  border-top-left-radius: 5px;
  margin-top: 300px;
}
.searchButton2{
  display: inline-block;
  width: 10%;
  height: 50px;
  line-height: 40px;
  float: left;
  background-color: #00a0e9;
  font-size: 16px;
  cursor: pointer;
  border-bottom-right-radius: 5px;
  border-top-right-radius: 5px;
  border: none;
  color: #fff;
  margin-top: 300px;
}
.referButton2{
  display: inline-block;
  width: 10%;
  height: 50px;
  line-height: 40px;
  float: right;
  background-color: #00a0e9;
  font-size: 16px;
  cursor: pointer;
  border-bottom-right-radius: 5px;
  border-top-right-radius: 5px;
  border: none;
  color: #fff;
  margin-top: 300px;
}
</style>

<script>

export default {
  name: 'Login',
  data () {
    return {
      keyword:'',
      skulist: {
        id:'',
        answer:2
      },
      recordcount: 1,
      pageCount:1
    }
  },
  activated () {

  },
  methods: {
    _search () {
      this.$axios
        .post('http://localhost:8080/api/login', {
          keyword: this.keyword,
          page:1,
          password:123456
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {

            this.pageCount =successResponse.data.pageCount
            this.skulist =successResponse.data.skulist
            this.recordcount =successResponse.data.recordcount

            this.$router.push({
              path:'/index',
              name:'Appindex',
              query:{
                skulist :this.skulist,
                keyword:this.keyword,
                pageCount:this.pageCount,
                recordCount:this.recordcount
              }
            })

          }
        })
        .catch(failResponse => {
        })
    },
    _refer () {
      this.$axios
        .post('http://localhost:8080/api/search',{
          keyword:'search',
          page:1,
          password:123456
        })
        .then(successResponse => {
          if(successResponse.data.code === 200){
            this.skulist=successResponse.data.skulist
            this.pageCount =1
            this.recordcount =1
            this.$router.push({
              path:'/index',
              name:'Appindex',
              query:{
                skulist :this.skulist,
                keyword:this.keyword,
                pageCount: this.pageCount,
                recordCount: this.recordCount
              }
            })
          }
        })
    }
  }
}
</script>

