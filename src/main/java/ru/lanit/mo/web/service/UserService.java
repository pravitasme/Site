package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.models.User;
import ru.lanit.mo.web.repository.UserDAO;

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

    public List<UserDTO> getAllUsers()
    {
        return userDAO.getAllUsers();
    }

    public List<User> getAllUsers2()
    {
        return userDAO.getAllUsers2();
    }

    public void addUser(UserDTO userDTO)
    {
        userDAO.addUser(userDTO);
    }

    public UserDTO getUserByID(int id)
    {
        return userDAO.getUserByID(id);
    }

    public void updateUser(UserDTO userDTO)
    {
        userDAO.updateUser(userDTO);
    }

    public void deleteUser(int id)
    {
        userDAO.deleteUser(id);
    }
}
