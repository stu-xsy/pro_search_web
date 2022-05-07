<!--实现多个hide-->
<template>
<div >
    <el-container>
    <el-header>
      <div class="box">
        <div class="searchBox">
          <input type="text" v-model="keyword" placeholder="请输入关键词" class="searchInput" style="padding-left: 14px">
          <input type="button"  @click="_search" value="搜索" class="searchButton">
          <input type="button"  @click="_refer" value="推荐" class="referButton">
        </div>
      </div>
    </el-header>
    <el-main>
      <div id="example" v-for="a in skulist" v-bind:key="a.id">
      <!-- 利用v-if…v-else切换 展开 和 收起 两个画面，template包裹多个元素 -->
      <template v-if="LookifHide(a.id)">
        <!-- 只显示摘要的画面 -->
        <div class="hideBg">
          <p class="summary">{{ a.answer }}</p>
          <div class="showBtn">
            <!-- 绑定点击事件onShow，点击展开全文 -->
            <a href="#" @click.stop.prevent="onShow(a.id)">展开阅读全文&nbsp;
              <!-- 向下的角箭头符号，用css画 -->
              <span class="downArrow"></span>
            </a>
          </div>
        </div>
      </template>
      <template v-else>
        <!-- 显示完整内容的画面 -->
        <div class="showBg">
          <p>{{ a.answer}}</p>
          <div class="hideBtn">
            <!-- 绑定点击事件onHide，点击收起内容 -->
            <a href="#" @click.stop.prevent="onHide(a.id)">收起&nbsp;
              <!-- 向上的角箭头符号 -->
              <span class="upArrow"></span>
            </a>
          </div>
        </div>
      </template>
    </div>
 </el-main>
      <el-footer>
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
      </el-footer>
  </el-container>


</div>


</template>
<style>


.el-header {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 60px;
    padding-top: 0px;
    margin-left:150px ;
    padding-left: 15px;
  }
.el-footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;

}
.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

.el-main {
    background-color: #ffffff;
    color: #333;
    text-align: left;
    line-height: 45px;
    margin-left: 150px;
    padding-left: 15px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }


.box{
  margin: 0 auto;
  padding-top: 0px;
  height: 50px;
  width: 100%;
}
.searchBox{
  margin: 0 auto;
  width: 100%;
  padding-left: 0px;
  position: relative;
}
.searchInput{
  display: inline-block;
  width: 35%;
  height: 38px;
  border: 1px solid #cccccc;
  float: left;
  box-sizing: border-box;
  -moz-box-sizing:border-box;
  -webkit-box-sizing:border-box; /* Safari */
  border-bottom-left-radius: 5px;
  border-top-left-radius: 5px;
  margin-top: 15px;
}
.searchButton {
  display: inline-block;
  width: 7%;
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
  margin-top: 15px;
}
.referButton {
  display: inline-block;
  width: 7%;
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
  margin-top: 15px;
}
/* 摘要背景板 */
.hideBg {
  padding-top: 30px;
  width: 80%;
  background-color: #e9ecef;
  margin-top: 4rem;
  margin-bottom: 4rem;
  padding-bottom: 0;    /* 方便渐变层遮挡 */
  position: relative;    /* 用于子元素定位 */
}
/* 全文背景板，基本与摘要相同 */
.showBg {
  width: 80%;
  background-color: #e9ecef;
  margin-top: 4rem;
  margin-bottom: 4rem;
  padding-top: 30px;
}
/* 摘要内容 */
.summary {
  line-height: 20px;
  overflow: hidden;    /* 隐藏溢出内容 */
  text-overflow: clip;    /* 修剪文本 */
  display: -webkit-box;    /* 弹性布局 */
  -webkit-box-orient: vertical;    /* 从上向下垂直排列子元素 */
  -webkit-line-clamp: 3;    /* 限制文本仅显示前三行 */
}
#example p {
  text-indent: 2em;
}
/* 展开按钮 */
.showBtn {
  width: 100%;    /* 与背景宽度一致 */
  height: 3rem;
  position: absolute;    /* 相对父元素定位 */
  top: 4rem;    /* 刚好遮挡在最后两行 */
  left: 0;
  z-index: 0;    /* 正序堆叠，覆盖在p元素上方 */
  text-align: center;
  background: linear-gradient(rgba(233,236,239,.5), white);    /* 背景色半透明到白色的渐变层 */
  padding-top: 0rem;
}
/* 收起按钮 */
.hideBtn {
  text-align: right;

}
#example a {
  text-decoration: none;    /* 清除链接默认的下划线 */
}
/* 向下角箭头 */
.downArrow {
  display: inline-block;
  width: 8px;    /* 尺寸不超过字号的一半为宜 */
  height: 8px;
  border-right: 1px solid;    /* 画两条相邻边框 */
  border-bottom: 1px solid;
  transform: rotate(45deg);    /* 顺时针旋转45° */
  margin-bottom: 3px;
}
/* 向上角箭头，原理与下箭头相同 */
.upArrow {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-left: 1px solid;
  border-top: 1px solid;
  transform: rotate(45deg);
  margin-top: 3px;
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
      flag:true,
      isHide:[
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
        {
          id:1,
          issHide:true
        },
      ],
      element:{
        id:1,
        answer: ''
      },
      i:0
    }
  },
  created() {
    this.getParameter();
  },
  mounted(){
    this.getParameter();
  },
  methods:{
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
            for(let item of this.isHide)
            {
              item.issHide=true
            }
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
    },
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
            for(let item of this.isHide)
            {
              item.issHide=true
            }
            for(let item of this.skulist)
            {
              this.isHide[this.i].id= item.id
              this.i=this.i+1
            }
            this.i=0
            console.log(this.isHide)

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
            for(let item of this.isHide)
            {
              item.issHide=true
            }
          }
        })
        .catch(failResponse => {
        })
    },
    getParameter(){
      this.flag=true
      const routerParams =this.$route.query.skulist
      const routerParamskey = this.$route.query.keyword
      const routerpagecount =this.$route.query.pageCount
      const routerrecord =this.$route.query.recordCount
      this.skulist=routerParams;
      this.keyword=routerParamskey;
      this.pageCount=routerpagecount;
      this.recordCount=routerrecord;
      this.total=this.recordCount;
      this.i=0
      console.log(1)
      for(let item of this.skulist)
      {
        this.isHide[this.i].id= item.id
        this.i=this.i+1
      }

      this.i=0


    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getGoodsList();
    },

    //监听多少页值改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage;
      this.getGoodsList();
    },
    onShow: function(id){
      for(let item of this.isHide){
        if(item.id=== id){
         item.issHide=false
        }
      }  //点击onShow切换为false，显示为展开画面
    },
    onHide: function(id){
      for(let item of this.isHide){
        if(item.id=== id){
          item.issHide=true
        }
      }    //点击onHide切换为true，显示为折叠画面
    },
    LookifHide: function (id){
      for(let item of this.isHide){
        if(item.id=== id){
          return item.issHide
        }
      }


    }


  }
}
</script>

