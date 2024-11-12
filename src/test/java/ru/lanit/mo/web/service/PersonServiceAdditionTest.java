package ru.lanit.mo.web.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.PersonDAO;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceAdditionTest {

    static PersonDTO person;

    private static PersonService personService;

    @BeforeAll
    static void setUp() {
        PersonDAO personDAO = new PersonDAO();
        personService = new PersonService(personDAO);

        person = new PersonDTO(1L, "name", new LinkedList<>(), LocalDate.now());
    }

    @Test
    void testAddPerson() {
        personService.addPerson(person);
        assertEquals(personService.getPerson(person.getId()).getId(), person.getId());
        assertEquals(personService.getPerson(person.getId()).getName(), person.getName());
        assertEquals(personService.getPerson(person.getId()).getBirthdate(), person.getBirthdate());
        personService.clear();
    }
}
