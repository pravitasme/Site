package ru.lanit.mo.web.restController;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.CarDAO;
import ru.lanit.mo.web.repository.PersonDAO;
import ru.lanit.mo.web.service.CarService;
import ru.lanit.mo.web.service.PersonService;
import ru.lanit.mo.web.service.StatisticService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClearControllerTest {

    static PersonDTO goodPerson;
    static CarDTO goodCar;

    private static ClearController clearController;
    static PersonDAO personDAO = new PersonDAO();
    static PersonService personService = new PersonService(personDAO);
    static CarDAO carDAO = new CarDAO();
    static CarService carService = new CarService(carDAO, personService);

    @BeforeAll
    static void setUp() {
        StatisticService statisticService = new StatisticService(carService, personService);
        clearController = new ClearController(personService, carService, statisticService);

        goodPerson = new PersonDTO(-1L, "name", new ArrayList<>(), LocalDate.now().minusYears(20));
        goodCar = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
    }

    @Test
    void clearTest() {
        List<CarDTO> cars = new ArrayList<>();
        personService.addPerson(goodPerson);
        carService.addCar(goodCar);
        clearController.clear();
        assertNull(personService.getPerson(goodPerson.getId()));
        assertEquals(cars, carService.getAllCars());
    }
}
