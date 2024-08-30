package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String simpleQuery = "CREATE TABLE IF NOT EXISTS User (id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), name varchar(50), lastName varchar(50), age int);";
            statement.executeUpdate(simpleQuery);
//            StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + User.class.getSimpleName() + " (");
//            Field[] fields = User.class.getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                Field f = fields[i];
//                query.append(f.getName() + (f.getType().getSimpleName().equals("String") ? " varchar(50)" : " int"));
//                if (f.getAnnotation(Id.class) != null) {
//                    query.append(" NOT NULL AUTO_INCREMENT, PRIMARY KEY (" + f.getName() + ")");
//                }
//                if (i < fields.length - 1) {
//                    query.append(", ");
//                } else {
//                    query.append(");");
//                }
////                System.out.println(f.getName() + " / " + f.getType().getSimpleName() + " / " + f.getAnnotation(Id.class));
//            }
//            statement.executeUpdate(String.valueOf(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String simpleQuery = "DROP TABLE IF EXISTS user";
            statement.executeUpdate(simpleQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "DELETE FROM user WHERE id=" + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM user;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User newUser = new User();
//                int id =
                newUser.setId(resultSet.getLong(1));
//                String name =
                newUser.setName(resultSet.getString(2));
//                String lastName =
                newUser.setLastName(resultSet.getString(3));
//                byte age =
                newUser.setAge(resultSet.getByte(4));
                userList.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "DELETE FROM user";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
