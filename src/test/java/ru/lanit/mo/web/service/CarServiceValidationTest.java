package ru.lanit.mo.web.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.CarDAO;
import ru.lanit.mo.web.repository.PersonDAO;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceValidationTest {

    static CarDTO goodCar;
    static CarDTO nullIdCar;
    static CarDTO nullModelCar;
    static CarDTO notValidModelCar;
    static CarDTO badHorsepowerCar;
    static CarDTO nullOwnerIdCar;
    static CarDTO tooYoungOwnerCar;

    static PersonDTO tooYoungPerson;
    static PersonDTO goodPerson;

    private static CarService carService;
    private static PersonService personService;

    @BeforeAll
    static void setUp() {
        CarDAO carDAO = new CarDAO();
        PersonDAO personDAO = new PersonDAO();
        personService = new PersonService(personDAO);
        carService = new CarService(carDAO, personService);

        goodCar = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
        nullIdCar = new CarDTO(null, "VENDOR-MODEL", 100, -1L);
        nullModelCar = new CarDTO(-1L, null, 100, -1L);
        notValidModelCar = new CarDTO(-1L, "-", 100, -1L);
        badHorsepowerCar = new CarDTO(-1L, "VENDOR-MODEL", -10, -1L);
        nullOwnerIdCar = new CarDTO(-1L, "VENDOR-MODEL", 100, null);
        tooYoungOwnerCar = new CarDTO(-1L, "VENDOR-MODEL", -10, -18L);

        tooYoungPerson = new PersonDTO(-18L, "little", new LinkedList<>(), LocalDate.now().minusYears(1));
        goodPerson = new PersonDTO(-1L, "not little", new LinkedList<>(), LocalDate.now().minusYears(20));
    }

    @Test
    void goodCar_noneException() {
        personService.addPerson(goodPerson);
        assertDoesNotThrow(() -> carService.validateCar(goodCar));
        personService.clear();
    }

    @Test
    void nullIdCar_nullIdException() {
        assertThrows(ValidationException.class, () -> carService.validateCar(nullIdCar));
    }

    @Test
    void nullModelCar_nullModelException() {
        assertThrows(ValidationException.class, () -> carService.validateCar(nullModelCar));
    }

    @Test
    void notValidModelCar_notValidException() {
        assertThrows(ValidationException.class, () -> carService.validateCar(notValidModelCar));
    }

    @Test
    void badHorsepowerCar_badHorsepowerException() {
        assertThrows(ValidationException.class, () -> carService.validateCar(badHorsepowerCar));
    }

    @Test
    void nullOwnerIdCar_nullOwnerIdException() {
        assertThrows(ValidationException.class, () -> carService.validateCar(nullOwnerIdCar));
    }

    @Test
    void tooYoungOwnerCar_tooYoungOwnerException() {
        personService.addPerson(tooYoungPerson);
        assertThrows(ValidationException.class, () -> carService.validateCar(tooYoungOwnerCar));
        personService.clear();
    }
}
