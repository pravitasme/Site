package ru.lanit.mo.web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/JSON", produces = {"application/json"} )
    public @ResponseBody List<UserDTO> getJSON() {
        return userService.getAllUsers();
    }
}
