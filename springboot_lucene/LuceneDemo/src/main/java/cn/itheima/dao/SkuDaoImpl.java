package cn.itheima.dao;

import cn.itheima.pojo.sku;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkuDaoImpl implements SkuDao{
    public List<sku> querySkuList(){
        Connection connection= null;
        PreparedStatement preparedstatment =null;
        ResultSet resultset =null;
        List<sku> list =new ArrayList<sku>();





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
            String sql ="SELECT * from answerzhi";
            try {
                preparedstatment =connection.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultset =preparedstatment.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            while(true){
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


}
