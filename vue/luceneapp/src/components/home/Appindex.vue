
<template>
<div >
  <div >
    <div class="box">
      <div class="searchBox">
        <input type="text" v-model="keyword" placeholder="请输入关键词" class="searchInput" style="padding-left: 14px">
        <input type="button"  @click="_search" value="搜索" class="searchButton">
      </div>
    </div>
    <p v-for="a in skulist" v-bind:key="a.id">{{a.answer}}</p>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="10"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      >
    </el-pagination>
  </div>

</div>


</template>
<style scoped>
/* 需要输入数据
 1.当前页数 currentPage
 2.当前页数的数据skulist
 3.当前页数的数据的个数
 4


 */
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
  -moz-box-sizing:border-box;
  -webkit-box-sizing:border-box; /* Safari */
  border-bottom-left-radius: 5px;
  border-top-left-radius: 5px;
}
.searchButton {
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
  name: "Appindex",
  data(){
    return {

      skulist: {
        id:'',
        answer:''
      },
      pageCount:'',
      recordCount:1,
      currentPage:1,
      queryInfo: {
        query: "",
        //当前页数
        pagenum: 1,
        //每页数据大小
        pagesize: 10,
      },
      keyword:'',
      //总数据量
      total: 1,
      //数据
      flag:true

    }
  },
  created() {
    this.getParameter();
  },
  mounted(){
    this.getParameter();
  },
  methods:{
    _search () {
      this.$axios
        .post('http://localhost:8080/api/login', {
          keyword: this.keyword,
          page:1,
          password:123456
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.flag=false
            this.recordCount =successResponse.data.recordcount
            this.skulist =successResponse.data.skulist
            this.pageCount =successResponse.data.pageCount
            this.total =this.recordCount
            this.currentPage=1
            this.$router.push({
              path:'/index',
              name:'Appindex',
              query:{
                skulist : this.skulist,
                keyword : this.keyword,
                pageCount:this.pageCount,
                recordCount:this.recordCount
              },
            })
          }
        })
        .catch(failResponse => {
        })
    },
    getGoodsList(){
      this.$axios
        .post('http://localhost:8080/api/login', {
          keyword: this.keyword,
          page:this.queryInfo.pagenum,
          password:123456
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {

            this.pageCount =successResponse.data.pageCount
            this.skulist =successResponse.data.skulist

          }
        })
        .catch(failResponse => {
        })
    },
    getParameter(){
      this.flag=true
      console.log(this.$route.query)
      const routerParams =this.$route.query.skulist
      const routerParamskey = this.$route.query.keyword
      const routerpagecount =this.$route.query.pageCount
      const routerrecord =this.$route.query.recordCount
      this.skulist=routerParams;
      this.keyword=routerParamskey;
      this.pageCount=routerpagecount;
      this.recordCount=routerrecord;
      console.log(this.recordCount)
      this.total=this.recordCount;
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getGoodsList();
    },

    //监听多少页值改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage;
      this.getGoodsList();
    }

  }
}
</script>

