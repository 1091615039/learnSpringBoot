package com.tianmaying.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class DBHelper {

    public static String driver = "org.h2.Driver";
    public static String connectionString = "jdbc:h2:mem:tianmaying_blog;DB_CLOSE_DELAY=-1";
    public static String username = "sa";
    public static String password = "";
    
    static {
        ClassLoader classLoader = DBHelper.class.getClassLoader();
        try {
            File schema = new File(classLoader.getResource("database/schema.sql").getFile());
            DBHelper.executeUpdate(FileUtils.readFileToString(schema));
            File data = new File(classLoader.getResource("database/data.sql").getFile());
            DBHelper.executeUpdate(FileUtils.readFileToString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int queryCount(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            clear(null, statement, connection);
        }
    }

    public static void executeUpdate(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clear(null, statement, connection);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> queryList(String sql, RowMapper rowMapper, Class<T> clazz) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            List<T> list = new ArrayList<T>();
            connection = DBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T object = (T) rowMapper.map(resultSet);
                list.add(object);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            clear(null, statement, connection);
        }
    }

    public static Object queryObject(String sql, RowMapper rowMapper) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return rowMapper.map(resultSet);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            clear(null, statement, connection);
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(connectionString, username, password);
    }

    private static void clear(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ignored) {

        }
    }
    
    public static long create(String sql) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();  
            if (resultSet.next()) {  
               return resultSet.getLong(1);  
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            clear(resultSet, statement, connection);
        }
    }
    
    public static int update(String sql) {

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            clear(null, statement, connection);
        }
    }
}
