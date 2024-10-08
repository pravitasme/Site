package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.repository.UserDAO;

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

    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        List<UserDTO> userDTOS = userDAO.getAllUsers();
        userDAO.closeConnection();
        return userDTOS;
    }

    public void addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        userDAO.addUser(userDTO);
        userDAO.closeConnection();
    }

    public UserDTO getUserByID(int id) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        UserDTO userDTO = userDAO.getUserByID(id);
        userDAO.closeConnection();
        return userDTO;
    }

    public void updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        userDAO.updateUser(userDTO);
        userDAO.closeConnection();
    }

    public void deleteUser(int id) throws SQLException, ClassNotFoundException
    {
        userDAO.getDBConnection();
        userDAO.deleteUser(id);
        userDAO.closeConnection();
    }
}
