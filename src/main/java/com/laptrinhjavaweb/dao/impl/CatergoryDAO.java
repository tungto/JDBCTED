package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatergoryDAO implements ICategoryDAO {


    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/estatejdbc82019";
            String user = "root";
            String pw ="makunu101";
            Connection conn = DriverManager.getConnection(url, user, pw);

            return conn;
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;

        }
    }


    @Override
    public List<CategoryModel> findAll() {
        String sql = "Select * from EMPLOYEE";
        List<CategoryModel> results = new ArrayList<>();
        // open connection
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Connected to the database!");
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    CategoryModel categoryModel = new CategoryModel();
                    categoryModel.setCode(resultSet.getString("code"));
                    categoryModel.setName(resultSet.getString("name"));
                    results.add(categoryModel);
                }
                if(connection != null){
                    connection.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
                return results;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            System.out.println("Failed to make connection!");
            return null;
        }

    }
}
