package ru.lanit.mo.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class dataExport extends HttpServlet
{
    protected void doGet(HttpServlet req, HttpServletResponse res) throws ServletException, IOException
    {

    }

    private static ResultSet selectFromUserTable() throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        try
        {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement("select * from user");
            resultSet = preparedStatement.executeQuery();
            //https://www.geeksforgeeks.org/java-program-to-retrieve-contents-of-a-table-using-jdbc-connection/
        }
        catch (SQLException e)
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
