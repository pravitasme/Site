package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.repository.UserDAO;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    public void addUser(UserDTO userDTO) {
        userDAO.addUser(userDTO);
    }

    @Transactional
    public UserDTO getUserByID(int id) {
        return userDAO.getUserByID(id);
    }

    @Transactional
    public void updateUser(UserDTO userDTO) {
        userDAO.updateUser(userDTO);
    }

    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
