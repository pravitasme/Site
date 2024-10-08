package ru.lanit.mo.web.repository;

import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.UserDTO;

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

    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException
    {
        String selectFromUser = "select * from users";

        List<UserDTO> userDTOS = new ArrayList<UserDTO>();

        ResultSet resultSet = dbConnection.createStatement().executeQuery(selectFromUser);

        while (resultSet.next())
        {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(resultSet.getInt("id"));
            userDTO.setFirstname(resultSet.getString("firstname"));
            userDTO.setLastname(resultSet.getString("lastname"));
            userDTO.setPatronymic(resultSet.getString("patronymic"));
            userDTOS.add(userDTO);
        }

        resultSet.close();

        return userDTOS;
    }

    public void addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException
    {
        String addUser = "insert into users(firstname, lastname, patronymic) VALUES (" + "'" + userDTO.getFirstname() + "'" + "," + "'" + userDTO.getLastname() + "'" + "," + "'" + userDTO.getPatronymic() + "'" + ")";

        dbConnection.createStatement().execute(addUser);
    }

    public UserDTO getUserByID(int id) throws SQLException, ClassNotFoundException
    {
        String getUserByID = "select * from users where users.id = " + id;

        ResultSet resultSet = dbConnection.createStatement().executeQuery(getUserByID);

        UserDTO userDTO = new UserDTO();

        while (resultSet.next())
        {
            userDTO.setId(resultSet.getInt("id"));
            userDTO.setFirstname(resultSet.getString("firstname"));
            userDTO.setLastname(resultSet.getString("lastname"));
            userDTO.setPatronymic(resultSet.getString("patronymic"));
        }

        resultSet.close();

        return userDTO;
    }

    public void updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException
    {
        String updateUser = "update users set firstname = " + "'" + userDTO.getFirstname() + "'" + ", " + "lastname = " + "'" + userDTO.getLastname() + "'" + ", " + "patronymic = " + "'" + userDTO.getPatronymic() + "'" + " where id = " + userDTO.getId();

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
