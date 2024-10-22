package ru.lanit.mo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lanit.mo.web.entity.HouseDTO;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.service.HouseService;
import ru.lanit.mo.web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HouseService houseService;

    @RequestMapping("/viewUsers")
    public String viewUsers(Model model) {
        List<UserDTO> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "viewUsers";
    }

    @RequestMapping("/userAdd")
    public String showForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "userAdd";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.addUser(userDTO);
        return "redirect:/viewUsers";
    }

    @RequestMapping(value = "/userEdit/{id}")
    public String edit(@PathVariable int id, Model model) {
        UserDTO userDTO = userService.getUserByID(id);
        List<HouseDTO> houses = houseService.getAllHouses();
        model.addAttribute("user", userDTO);
        model.addAttribute("houses", houses);
        return "userEdit";
    }

    @RequestMapping(value = "/saveEditedUser", method = RequestMethod.POST)
    public String saveEditedUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/viewUsers";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/viewUsers";
    }
}
