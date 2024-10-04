package ru.lanit.mo.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class tabulate extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String patronymic = req.getParameter("patronymic");

        /*try
        {
            insertIntoUserTable(firstName, lastName, patronymic);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }*/

        res.sendRedirect("/selectMouse");
    }

    private static void insertIntoUserTable(String firstName, String lastName, String patronymic) throws SQLException
    {
        Connection dbConnection = null;
        Statement statement = null;

        String insertIntoUserTable = "insert into users(firstname, lastname, patronymic) VALUES (" + "'" + firstName + "'" + "," + "'" + lastName + "'" + "," + "'" + patronymic + "'" + ")";

        try
        {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            statement.execute(insertIntoUserTable);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (statement != null)
            {
                statement.close();
            }
            if (dbConnection != null)
            {
                dbConnection.close();
            }
        }
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