package ru.lanit.mo.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class tabulate extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String patronymic = req.getParameter("patronymic");

    }

    private static Connection getDBConnection()
    {
        Connection dbConnection = null;

        try
        {
            Class.forName(org.postgresql.Driver.class.getName());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1324");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}