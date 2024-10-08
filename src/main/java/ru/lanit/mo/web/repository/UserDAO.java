package ru.lanit.mo.web.repository;

import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO
{
    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "1324";

    static Connection dbConnection = null;

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException
    {
        String selectFromUser = "select * from users";

        List<User> users = new ArrayList<User>();

        ResultSet resultSet = dbConnection.createStatement().executeQuery(selectFromUser);

        while (resultSet.next())
        {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setPatronymic(resultSet.getString("patronymic"));
            users.add(user);
        }

        resultSet.close();

        return users;
    }

    public void addUser(User user) throws SQLException, ClassNotFoundException
    {
        String addUser = "insert into users(firstname, lastname, patronymic) VALUES (" + "'" + user.getFirstname() + "'" + "," + "'" + user.getLastname() + "'" + "," + "'" + user.getPatronymic() + "'" + ")";

        dbConnection.createStatement().execute(addUser);
    }

    public User getUserByID(int id) throws SQLException, ClassNotFoundException
    {
        String getUserByID = "select * from users where users.id = " + id;

        ResultSet resultSet = dbConnection.createStatement().executeQuery(getUserByID);

        User user = new User();

        while (resultSet.next())
        {
            user.setId(resultSet.getInt("id"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setPatronymic(resultSet.getString("patronymic"));
        }

        resultSet.close();

        return user;
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException
    {
        String updateUser = "update users set firstname = " + "'" + user.getFirstname() + "'" + ", " + "lastname = " + "'" + user.getLastname() + "'" + ", " + "patronymic = " + "'" + user.getPatronymic() + "'" + " where id = " + user.getId();

        dbConnection.createStatement().execute(updateUser);
    }

    public void deleteUser(int id) throws SQLException, ClassNotFoundException
    {
        String deleteUser = "delete from users where id = " + id;

        dbConnection.createStatement().execute(deleteUser);
    }

    public void getDBConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);

        dbConnection = DriverManager.getConnection(url, user, password);
    }

    public void closeConnection() throws SQLException
    {
        dbConnection.close();
    }
}
