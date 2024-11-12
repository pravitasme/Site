package ru.lanit.mo.web.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.repository.CarDAO;
import ru.lanit.mo.web.repository.PersonDAO;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CarServiceAdditionTest {

    static CarDTO goodCar;

    private static CarService carService;

    @BeforeAll
    static void setUp() {
        CarDAO carDAO = new CarDAO();
        PersonDAO personDAO = new PersonDAO();
        PersonService personService = new PersonService(personDAO);
        carService = new CarService(carDAO, personService);

        goodCar = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
    }

    @Test
    void goodCar_noneException() {
        assertDoesNotThrow(() -> carService.addCar(goodCar));
        carService.clear();
    }
}
