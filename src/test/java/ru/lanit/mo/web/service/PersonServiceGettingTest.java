package ru.lanit.mo.web.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.PersonDAO;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceGettingTest {

    static PersonDTO person;

    @BeforeAll
    static void setUp() {
        person = new PersonDTO(1L, "name", new LinkedList<>(), LocalDate.now());
    }

    @Test
    void getPerson_person() {
        PersonDAO mockPersonDAO = Mockito.mock(PersonDAO.class);
        PersonService personService = new PersonService(mockPersonDAO);
        Mockito.when(mockPersonDAO.getPersonById(person.getId())).thenReturn(person);
        assertEquals(personService.getPerson(person.getId()).getId(), person.getId());
        assertEquals(personService.getPerson(person.getId()).getName(), person.getName());
        assertEquals(personService.getPerson(person.getId()).getBirthdate(), person.getBirthdate());
        Mockito.verify(mockPersonDAO, Mockito.times(3)).getPersonById(person.getId());
    }
}
