package ru.lanit.mo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.lanit.mo.web.entity.User;
import ru.lanit.mo.web.repository.UserDAO;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    UserDAO userDAO;

    @RequestMapping("/addUser")
    public String showForm(Model model)
    {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) throws SQLException, ClassNotFoundException
    {
        UserDAO.addUser(user);
        return "redirect:/viewUsers";
    }

    @RequestMapping("/viewUsers")
    public String viewUsers(Model model) throws SQLException, ClassNotFoundException
    {
        List<User> list = UserDAO.getAllUsers();
        model.addAttribute("users", list);
        return "viewUsers";
    }

    @RequestMapping(value = "/editUser/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException, ClassNotFoundException
    {
        User user = userDAO.getUserByID(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping(value = "/editSavedUser", method = RequestMethod.POST)
    public String editSavedUser(@ModelAttribute("user") User user) throws SQLException, ClassNotFoundException
    {
        userDAO.updateUser(user);
        return "redirect:/viewUsers";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) throws SQLException, ClassNotFoundException
    {
        userDAO.deleteUser(id);
        return "redirect:/viewUsers";
    }
}
