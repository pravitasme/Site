package ru.lanit.mo.web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.mo.web.service.CarService;
import ru.lanit.mo.web.service.PersonService;
import ru.lanit.mo.web.service.StatisticService;

@RestController
public class ClearController {

    @Autowired
    private final PersonService personService;

    @Autowired
    private final CarService carService;

    @Autowired
    private final StatisticService statisticService;

    public ClearController(PersonService personService, CarService carService, StatisticService statisticService) {
        this.personService = personService;
        this.carService = carService;
        this.statisticService = statisticService;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear() {
        carService.clear();
        personService.clear();
        statisticService.clearStatistic();
    }
}
