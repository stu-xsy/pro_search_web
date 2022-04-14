package cn.itheima.controller;


import cn.itheima.pojo.ResultModel;
import cn.itheima.service.searchservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class searchcontroller {
    @Autowired
    private searchservice searchservice;



    @RequestMapping
    @CrossOrigin
    public String query(String queryString, Integer page, Model model)throws Exception{
        ResultModel resultModel=searchservice.query(queryString,page);

        //查询条件回显到页面
        model.addAttribute("queryString",queryString);
        model.addAttribute("page",page);
        return "";

    }
}
