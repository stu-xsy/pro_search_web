package cn.itheima.dao;

import cn.itheima.pojo.sku;
import cn.itheima.pojo.uset;

import java.util.List;
import java.util.Map;

public interface userreDao  {
    public void turntouser(uset User);
    public List<sku> Gettherefer();
    public List<uset> Usermap();
}
