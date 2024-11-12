package ru.lanit.mo.web.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.PersonDAO;

import javax.validation.ValidationException;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceValidationTest {

    static PersonDTO goodPerson;
    static PersonDTO nullIdPerson;
    static PersonDTO nullNamePerson;
    static PersonDTO nullBirthdatePerson;
    static PersonDTO afterBirthdatePerson;

    private static PersonService personService;

    @BeforeAll
    static void setUp() {
        PersonDAO personDAO = new PersonDAO();
        personService = new PersonService(personDAO);

        goodPerson = new PersonDTO(1L, "name", new LinkedList<>(), LocalDate.now());
        nullIdPerson = new PersonDTO(null, "name", new LinkedList<>(), LocalDate.now());
        nullNamePerson = new PersonDTO(1L, null, new LinkedList<>(), LocalDate.now());
        nullBirthdatePerson = new PersonDTO(1L, "name", new LinkedList<>(), null);
        afterBirthdatePerson = new PersonDTO(1L, "name", new LinkedList<>(), LocalDate.now().plusDays(1));
    }

    @Test
    void goodPerson_noneException() {
        assertDoesNotThrow(() -> personService.validatePerson(goodPerson));
    }

    @Test
    void nullId_nullIdException() {
        assertThrows(ValidationException.class, () -> {personService.validatePerson(nullIdPerson);});
    }

    @Test
    void nullName_nullNameException() {
        assertThrows(ValidationException.class, () -> {personService.validatePerson(nullNamePerson);});
    }

    @Test
    void nullBirthdate_nullBirthdateException() {
        assertThrows(ValidationException.class, () -> {personService.validatePerson(nullBirthdatePerson);});
    }

    @Test
    void afterBirthdate_afterBirthdateException() {
        assertThrows(ValidationException.class, () -> {personService.validatePerson(afterBirthdatePerson);});
    }

    @Test
    void personAlreadyExists_personAlreadyExistsException() {
        personService.addPerson(goodPerson);
        assertThrows(ValidationException.class, () -> {personService.validatePerson(goodPerson);});
        personService.clear();
    }
}