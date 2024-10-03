package ru.lanit.mo.web.controller;

import ru.lanit.mo.web.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dataExport extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        try
        {
            req.setAttribute("exportData", selectFromUserTable());
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/illShowYouThemAll.jsp");
            rd.forward(req, res);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static List<User> selectFromUserTable() throws SQLException, ClassNotFoundException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        String selectFromUser = "select * from users";

        try
        {
            dbConnection = getDBConnection();

            preparedStatement = dbConnection.prepareStatement(selectFromUser);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        List<User> users = new ArrayList<>();

        while (resultSet.next())
        {
            User user = new User();
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setPatronymic(resultSet.getString("patronymic"));
            users.add(user);
        }
        resultSet.close();

        if (preparedStatement != null)
        {
            preparedStatement.close();
        }
        if (dbConnection != null)
        {
            dbConnection.close();
        }

        //List<User> users = user

        return users;
    }

    private static Connection getDBConnection() throws ClassNotFoundException, SQLException
    {
        Connection dbConnection = null;

        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1324");
        return dbConnection;
    }
}
