package ru.lanit.mo.web.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.entity.StatisticDTO;
import ru.lanit.mo.web.repository.CarDAO;
import ru.lanit.mo.web.repository.PersonDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticServiceGettingTest {

    static PersonDTO person;
    static CarDTO car;

    private static StatisticService statisticService;
    private static CarService carService;
    private static PersonService personService;

    @BeforeAll
    static void setUp() {
        CarDAO carDAO = new CarDAO();
        PersonDAO personDAO = new PersonDAO();
        personService = new PersonService(personDAO);
        carService = new CarService(carDAO, personService);
        statisticService = new StatisticService(carService, personService);

        car = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
        List<CarDTO> cars = new ArrayList<>();
        cars.add(car);
        person = new PersonDTO(-1L, "name", cars, LocalDate.now().minusYears(20));
    }

    @Test
    void goodEntity_goodStatistic() {
        personService.addPerson(person);
        carService.addCar(car);
        StatisticDTO statistic = statisticService.getStatistic();
        assertEquals(statistic.getCarcount(), 1);
        assertEquals(statistic.getPersoncount(), 1);
        assertEquals(statistic.getUniquevendorcount(), 1);
        personService.clear();
        carService.clear();
        statisticService.clearStatistic();
    }
}
