package ru.lanit.mo.web.restController;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.CarDAO;
import ru.lanit.mo.web.repository.PersonDAO;
import ru.lanit.mo.web.service.CarService;
import ru.lanit.mo.web.service.PersonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonWithCarsControllerTest {

    static PersonDTO person;
    static CarDTO car;

    static PersonWithCarsController personWithCarsController;
    static PersonService personService;
    static CarService carService;

    @BeforeAll
    static void setUp() {
        List<CarDTO> cars = new ArrayList<>();
        car = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
        cars.add(car);
        person = new PersonDTO(-1L, "name", cars, LocalDate.now().minusYears(20));
        PersonDAO personDAO = new PersonDAO();
        personService = new PersonService(personDAO);
        personWithCarsController = new PersonWithCarsController(personService);
        CarDAO carDAO = new CarDAO();
        carService = new CarService(carDAO, personService);
    }

    @Test
    void getPersonWithCarsTest() {
        personService.addPerson(person);
        carService.addCar(car);
        ResponseEntity<?> response = new ResponseEntity<>(person, HttpStatus.OK);
        ResponseEntity<?> response1 = personWithCarsController.getPersonWithCars(person.getId());
        assertEquals(response.getStatusCode(), response1.getStatusCode());
        assertEquals(response.getBody(), response1.getBody());
        carService.clear();
        personService.clear();
    }
}
