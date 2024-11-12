package ru.lanit.mo.web.restController;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.entity.StatisticDTO;
import ru.lanit.mo.web.repository.CarDAO;
import ru.lanit.mo.web.repository.PersonDAO;
import ru.lanit.mo.web.service.CarService;
import ru.lanit.mo.web.service.PersonService;
import ru.lanit.mo.web.service.StatisticService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticControllerTest {

    static StatisticDTO statistic;
    static PersonDTO person;
    static CarDTO car;

    static StatisticController statisticController;
    static PersonDAO personDAO = new PersonDAO();
    static PersonService personService = new PersonService(personDAO);
    static CarDAO carDAO = new CarDAO();
    static CarService carService = new CarService(carDAO, personService);

    @BeforeAll
    static void setUp() {
        StatisticService statisticService = new StatisticService(carService, personService);
        statisticController = new StatisticController(statisticService);

        statistic = new StatisticDTO(1L, 1L, 1L);
        person = new PersonDTO(-1L, "name", new ArrayList<>(), LocalDate.now().minusYears(20));
        car = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
    }

    @Test
    void statisticTest() {
        personService.addPerson(person);
        carService.addCar(car);
        ResponseEntity<?> response = new ResponseEntity<>(statistic, HttpStatus.OK);
        assertEquals(response.getStatusCode(), statisticController.getStatistics().getStatusCode());
        assertEquals(response.getBody(), statisticController.getStatistics().getBody());
        carService.clear();
        personService.clear();
    }
}
