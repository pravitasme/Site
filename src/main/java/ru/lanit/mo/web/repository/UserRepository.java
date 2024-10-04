package ru.lanit.mo.web.repository;

import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository
{
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "1324";

    Connection dbConnection = null;

    private static Connection GetDBConnection() throws SQLException, ClassNotFoundException
    {
        Connection dbConnection = null;
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(url, user, password);
        return dbConnection;
    }

    private static void CloseConnection(Connection dbConnection) throws SQLException
    {
        dbConnection.close();
    }

    private static List<User> GetAllUsers()
    {
        List<User> users = new ArrayList<User>();

        return users;
    }
}
