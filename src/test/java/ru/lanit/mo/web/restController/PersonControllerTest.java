package ru.lanit.mo.web.restController;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.PersonDAO;
import ru.lanit.mo.web.service.PersonService;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonControllerTest {

    static PersonDTO goodPerson;
    static PersonDTO nullIdPerson;
    static PersonDTO nullNamePerson;
    static PersonDTO nullBirthdatePerson;
    static PersonDTO afterBirthdatePerson;

    private PersonDAO personDAO = new PersonDAO();
    private PersonService personService = new PersonService(personDAO);
    private PersonController personController = new PersonController(personService);

    @BeforeAll
    static void setUp() {
        goodPerson = new PersonDTO(1L, "name", new LinkedList<>(), LocalDate.now());
        nullIdPerson = new PersonDTO(null, "name", new LinkedList<>(), LocalDate.now());
        nullNamePerson = new PersonDTO(1L, null, new LinkedList<>(), LocalDate.now());
        nullBirthdatePerson = new PersonDTO(1L, "name", new LinkedList<>(), null);
        afterBirthdatePerson = new PersonDTO(1L, "name", new LinkedList<>(), LocalDate.now().plusDays(1));
    }

    @Test
    void goodPerson_noneException() {
        ResponseEntity<?> response = personController.addPerson(goodPerson);
        assertEquals(response, ResponseEntity.status(HttpStatus.OK).body(goodPerson));
        personService.clear();
    }

    @Test
    void nullId_nullIdException() {
        ResponseEntity<?> response = personController.addPerson(nullIdPerson);
        assertEquals(response, new ResponseEntity<>("Person ID is null", HttpStatus.BAD_REQUEST));
        personService.clear();
    }

    @Test
    void nullName_nullNameException() {
        ResponseEntity<?> response = personController.addPerson(nullNamePerson);
        assertEquals(response, new ResponseEntity<>("Person name is null", HttpStatus.BAD_REQUEST));
        personService.clear();
    }

    @Test
    void nullBirthdate_nullBirthdateException() {
        ResponseEntity<?> response = personController.addPerson(nullBirthdatePerson);
        assertEquals(response, new ResponseEntity<>("Person birthdate is null", HttpStatus.BAD_REQUEST));
        personService.clear();
    }

    @Test
    void afterBirthdate_afterBirthdateException() {
        ResponseEntity<?> response = personController.addPerson(afterBirthdatePerson);
        assertEquals(response, new ResponseEntity<>("Person birthdate is after now", HttpStatus.BAD_REQUEST));
        personService.clear();
    }

    @Test
    void personAlreadyExists_personAlreadyExistsException() {
        personService.addPerson(goodPerson);
        ResponseEntity<?> response = personController.addPerson(goodPerson);
        personService.clear();
        assertEquals(response, new ResponseEntity<>("Person already exists", HttpStatus.BAD_REQUEST));
    }
}
