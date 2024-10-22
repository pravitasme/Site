package ru.lanit.mo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.lanit.mo.web.entity.HouseDTO;
import ru.lanit.mo.web.service.HouseService;

import java.util.List;

@Controller
public class HouseController {

    @Autowired
    HouseService houseService;

    @RequestMapping("/viewHouses")
    public String viewHouses(Model model) {
        List<HouseDTO> list = houseService.getAllHouses();
        model.addAttribute("houses", list);
        return "viewHouses";
    }

    @RequestMapping("/houseAdd")
    public String showForm(Model model) {
        model.addAttribute("house", new HouseDTO());
        return "houseAdd";
    }

    @RequestMapping( value = "/saveHouse", method = RequestMethod.POST)
    public String saveHouse(@ModelAttribute("house") HouseDTO houseDTO) {
        houseService.addHouse(houseDTO);
        return "redirect:/viewHouses";
    }

    @RequestMapping(value = "/deleteHouse/{id}")
    public String deleteHouse(@PathVariable("id") int id) {
        houseService.deleteHouse(id);
        return "redirect:/viewHouses";
    }
}
