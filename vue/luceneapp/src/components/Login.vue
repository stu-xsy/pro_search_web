<template>
  <div>
    <div class="box">
      <div class="searchBox">
        <input type="text" v-model="keyword" placeholder="请输入关键词" class="searchInput" style="padding-left: 14px">
        <input type="button"  @click="_search" value="搜索" class="searchButton">
      </div>
    </div>
  </div>

</template>
<style>
.box{
  margin: 0 auto;
  padding-top: 20px;
  height: 50px;
  width: 100%;
}
.searchBox{
  margin: 0 auto;
  width: 60%;
  position: relative;
}
.searchInput{
  display: inline-block;
  width: 85%;
  height: 38px;
  border: 1px solid #cccccc;
  float: left;
  box-sizing: border-box;
  -moz-box-sizing:border-box; /* Firefox */
  -webkit-box-sizing:border-box; /* Safari */
  border-bottom-left-radius: 5px;
  border-top-left-radius: 5px;
}
.searchButton{
  display: inline-block;
  width: 15%;
  height: 38px;
  line-height: 40px;
  float: left;
  background-color: #00a0e9;
  font-size: 16px;
  cursor: pointer;
  border-bottom-right-radius: 5px;
  border-top-right-radius: 5px;
  border: none;
  color: #fff;
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
            console.log(successResponse.data)
            this.pageCount =successResponse.data.pageCount
            this.skulist =successResponse.data.skulist
            this.recordcount =successResponse.data.recordcount
            console.log(this.recordcount)
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
    }
  }
}
</script>

