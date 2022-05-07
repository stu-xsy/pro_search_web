package cn.itheima.service;

import cn.itheima.dao.userreDao;
import cn.itheima.dao.userreDaoimpl;
import cn.itheima.jieba.keyword.Keyword;
import cn.itheima.jieba.keyword.TFIDFAnalyzer;
import cn.itheima.pojo.ResultModel;
import cn.itheima.pojo.sku;
import cn.itheima.pojo.uset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class referserviceimpl implements  referservice{
    TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
    userreDao userredao = new userreDaoimpl();
    int topN=7;
    List<uset> usermap = new ArrayList<uset>();
  @Override
    public ResultModel queryre(String queryString, Integer page)
  {
      List<sku> referget=userredao.Gettherefer();
      List<sku> choose=new ArrayList<sku>();
      usermap=userredao.Usermap();

      ResultModel resultModel =new ResultModel();

      int count=0;
      for(sku skuchile:referget){
          double TFtotal=0;


          try {
              List<Keyword> keys= tfidfAnalyzer.analyze(skuchile.getAnswer(),topN);
              for(Keyword key:keys){
                  for(uset user:usermap){
                      if(user.getKeyword().equals(key.getName())){
                          TFtotal += user.getTFIDF()*key.getTfidfvalue();

                      }
                  }
              }

              if(TFtotal>0.1 && skuchile.getAnswer().length()>50){
                  choose.add(skuchile);
                  count++;
              }
              if(count>10)
              {
                  break;
              }
          }catch (Exception e){
              e.printStackTrace();
              continue;
          }


      }

      resultModel.setCode(200);

      resultModel.setCurPage(1);
      resultModel.setSkulist(choose);

      return resultModel;

  }

}
