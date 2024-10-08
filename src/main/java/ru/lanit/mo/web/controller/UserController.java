package ru.lanit.mo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    UserService userService;

    @RequestMapping("/viewUsers")
    public String viewUsers(Model model) throws SQLException, ClassNotFoundException
    {
        List<UserDTO> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "viewUsers";
    }

    @RequestMapping("/userAdd")
    public String showForm(Model model)
    {
        model.addAttribute("user", new UserDTO());
        return "userAdd";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") UserDTO userDTO) throws SQLException, ClassNotFoundException
    {
        userService.addUser(userDTO);
        return "redirect:/viewUsers";
    }

    @RequestMapping(value = "/userEdit/{id}")
    public String edit(@PathVariable int id, Model model) throws SQLException, ClassNotFoundException
    {
        UserDTO userDTO = userService.getUserByID(id);
        model.addAttribute("user", userDTO);
        return "userEdit";
    }

    @RequestMapping(value = "/saveEditedUser", method = RequestMethod.POST)
    public String saveEditedUser(@ModelAttribute("user") UserDTO userDTO) throws SQLException, ClassNotFoundException
    {
        userService.updateUser(userDTO);
        return "redirect:/viewUsers";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) throws SQLException, ClassNotFoundException
    {
        userService.deleteUser(id);
        return "redirect:/viewUsers";
    }
}
