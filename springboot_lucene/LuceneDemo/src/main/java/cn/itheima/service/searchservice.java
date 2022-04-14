package cn.itheima.service;

import cn.itheima.pojo.ResultModel;
import org.springframework.ui.Model;

public interface searchservice {
    public ResultModel query(String queryString, Integer page) throws Exception;
}
