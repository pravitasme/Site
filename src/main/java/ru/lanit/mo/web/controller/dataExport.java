package ru.lanit.mo.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dataExport extends HttpServlet
{
    protected void doGet(HttpServlet req, HttpServletResponse res) throws ServletException, IOException
    {

    }

    private static ResultSet selectFromUserTable()
    {
        Connection dbConnection = null;
        ResultSet resultSet = null;

        return resultSet;
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
