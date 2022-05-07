package cn.itheima.dao;

import cn.itheima.pojo.UserResult;
import cn.itheima.pojo.sku;
import cn.itheima.pojo.uset;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userreDaoimpl implements userreDao{

    public void turntouser(uset User){
        Connection connection= null;
        PreparedStatement preparedstatment =null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql ="insert into uset(keyword,TFIDF) values(?,?)";
        try {
            preparedstatment =connection.prepareStatement(sql);
            preparedstatment.setString(1,User.getKeyword());
            preparedstatment.setDouble(2,User.getTFIDF());
            preparedstatment.executeUpdate();
            // 关闭数据库连接对象
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public List<sku> Gettherefer()
    {
        Connection connection= null;
        PreparedStatement preparedstatment =null;
        List<sku> list =new ArrayList<sku>();
        ResultSet resultset =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int Idstart =(int)(Math.random()*13000);

        String sql ="SELECT * from answerzhi where id BETWEEN (?) AND (?) ";

        try {
            preparedstatment =connection.prepareStatement(sql);
            preparedstatment.setInt(1,Idstart);
            preparedstatment.setInt(2,Idstart+2000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultset =preparedstatment.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while(true)
        {
            try {
                if (!resultset.next()) break;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            sku sku1=new sku();
            try {

                sku1.setId(resultset.getInt("id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {



                sku1.setAnswer(resultset.getString("answer"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            list.add(sku1);

        }
        return list;

    }

    @Override
    public List<uset> Usermap() {
        Connection connection= null;
        PreparedStatement preparedstatment =null;
        ResultSet userResult=null;
        List<uset> usemap=new ArrayList<uset>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql ="SELECT * from uset";
        try {
            preparedstatment =connection.prepareStatement(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userResult = preparedstatment.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if(!userResult.next())break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            uset uset1 = new uset();
            try {

                uset1.setTFIDF(userResult.getDouble("TFIDF"));
                uset1.setKeyword(userResult.getString("keyword"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            usemap.add(uset1);
        }
        return usemap;
    }

}
