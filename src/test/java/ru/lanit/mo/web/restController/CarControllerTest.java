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
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarControllerTest {

    static CarDTO goodCar;
    static CarDTO nullIdCar;
    static CarDTO nullModelCar;
    static CarDTO notValidModelCar;
    static CarDTO badHorsepowerCar;
    static CarDTO nullOwnerIdCar;
    static CarDTO tooYoungOwnerCar;

    static PersonDTO tooYoungPerson;
    static PersonDTO goodPerson;

    private final PersonDAO personDAO = new PersonDAO();
    private final PersonService personService = new PersonService(personDAO);
    private final CarDAO carDAO = new CarDAO();
    private final CarService carService = new CarService(carDAO, personService);
    private final CarController carController = new CarController(carService);

    @BeforeAll
    static void setUp() {
        goodCar = new CarDTO(-1L, "VENDOR-MODEL", 100, -1L);
        nullIdCar = new CarDTO(null, "VENDOR-MODEL", 100, -1L);
        nullModelCar = new CarDTO(-1L, null, 100, -1L);
        notValidModelCar = new CarDTO(-1L, "-", 100, -1L);
        badHorsepowerCar = new CarDTO(-1L, "VENDOR-MODEL", -10, -1L);
        nullOwnerIdCar = new CarDTO(-1L, "VENDOR-MODEL", 100, null);
        tooYoungOwnerCar = new CarDTO(-1L, "VENDOR-MODEL", 100, -18L);

        tooYoungPerson = new PersonDTO(-18L, "little", new LinkedList<>(), LocalDate.now().minusYears(1));
        goodPerson = new PersonDTO(-1L, "not little", new LinkedList<>(), LocalDate.now().minusYears(20));
    }

    @Test
    void goodCar_noneException() {
        personService.addPerson(goodPerson);
        ResponseEntity<?> response = carController.addCar(goodCar);
        assertEquals(response, ResponseEntity.status(HttpStatus.OK).body(goodCar));
        carService.clear();
        personService.clear();
    }

    @Test
    void nullIdCar_nullIdException() {
        ResponseEntity<?> response = carController.addCar(nullIdCar);
        assertEquals(response, new ResponseEntity<>("Car ID is null", HttpStatus.BAD_REQUEST));
        carService.clear();
    }

    @Test
    void nullModelCar_nullModelException() {
        ResponseEntity<?> response = carController.addCar(nullModelCar);
        assertEquals(response, new ResponseEntity<>("Car model is null", HttpStatus.BAD_REQUEST));
        carService.clear();
    }

    @Test
    void notValidModelCar_notValidException() {
        ResponseEntity<?> response = carController.addCar(notValidModelCar);
        assertEquals(response, new ResponseEntity<>("Car model is not valid", HttpStatus.BAD_REQUEST));
        carService.clear();
    }

    @Test
    void badHorsepowerCar_badHorsepowerException() {
        ResponseEntity<?> response = carController.addCar(badHorsepowerCar);
        assertEquals(response, new ResponseEntity<>("Horse power is less than 0 or equal to 0", HttpStatus.BAD_REQUEST));
        carService.clear();
    }

    @Test
    void nullOwnerIdCar_nullOwnerIdException() {
        ResponseEntity<?> response = carController.addCar(nullOwnerIdCar);
        assertEquals(response, new ResponseEntity<>("Owner ID is null", HttpStatus.BAD_REQUEST));
        carService.clear();
    }

    @Test
    void tooYoungOwnerCar_tooYoungOwnerException() {
        personService.addPerson(tooYoungPerson);
        ResponseEntity<?> response = carController.addCar(tooYoungOwnerCar);
        personService.clear();
        assertEquals(response, new ResponseEntity<>("Owner is too young", HttpStatus.BAD_REQUEST));
    }

    @Test
    void alreadyExistsCar_alreadyExistsException() {
        personService.addPerson(goodPerson);
        carService.addCar(goodCar);
        ResponseEntity<?> response = carController.addCar(goodCar);
        personService.clear();
        carService.clear();
        assertEquals(response, new ResponseEntity<>("Car already exists", HttpStatus.BAD_REQUEST));
    }
}
