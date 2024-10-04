package ru.lanit.mo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController
{
    @RequestMapping("/hello")
    public String display(@RequestParam("name") String name, @RequestParam("password") String password, Model model)
    {
        if(password.equals("1324"))
        {
            String message = "Hello " + name + "!";
            model.addAttribute("message", message);
            return "viewpage";
        }
        else
        {
            String message = "Sorry " + name + ". " + "You entered an incorrect password";
            model.addAttribute("message", message);
            return "errorpage";
        }
    }
}
