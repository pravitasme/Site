package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.mo.web.entity.User;
import ru.lanit.mo.web.repository.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService
{
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        List<User> users = userDAO.getAllUsers();
        userDAO.closeConnection();
        return users;
    }

    public void addUser(User user) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        userDAO.addUser(user);
        userDAO.closeConnection();
    }

    public User getUserByID(int id) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        User user = userDAO.getUserByID(id);
        userDAO.closeConnection();
        return user;
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        userDAO.updateUser(user);
        userDAO.closeConnection();
    }

    public void deleteUser(int id) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        userDAO.deleteUser(id);
        userDAO.closeConnection();
    }
}
