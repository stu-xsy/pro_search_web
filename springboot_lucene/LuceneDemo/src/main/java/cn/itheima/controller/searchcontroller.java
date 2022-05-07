package cn.itheima.controller;


import cn.itheima.pojo.Result;
import cn.itheima.pojo.ResultModel;
import cn.itheima.pojo.User;
import cn.itheima.service.referservice;
import cn.itheima.service.searchservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class searchcontroller {
    @Autowired
    private referservice referservice;
    @CrossOrigin
    @PostMapping(value = "api/search")
    @ResponseBody
    public ResultModel search(@RequestBody User requestUser) throws  Exception{
        // 对 html 标签进行转义，防止 XSS 攻击
        String keyword= requestUser.getKeyword();
        keyword = HtmlUtils.htmlEscape(keyword);
        String page= requestUser.getPage();
        if (!Objects.equals("123456", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");

            ResultModel resultModel = new ResultModel();

            resultModel =referservice.queryre("计算机",Integer.valueOf(page));
            resultModel.setCode(200);
            return resultModel;
        } else {
            ResultModel resultModel = new ResultModel();
            resultModel =referservice.queryre(keyword,Integer.valueOf(page));
            resultModel.setCode(200);
            return resultModel;
        }
    }

}
