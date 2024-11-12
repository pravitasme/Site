package ru.lanit.mo.web.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.PersonDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDAOTest {

    static PersonDTO goodPerson;
    static PersonDTO goodPerson2;
    static PersonDTO nullIdPerson;

    private static final PersonDAO personDAO = new PersonDAO();

    @BeforeAll
    static void SetUp() {
        goodPerson = new PersonDTO(-1L, "goodName", new ArrayList<>(), LocalDate.now().minusYears(20));
        goodPerson2 = new PersonDTO(-2L, "goodName2", new ArrayList<>(), LocalDate.now().minusYears(20));
        nullIdPerson = new PersonDTO(null, "badName", new ArrayList<>(), LocalDate.now().minusYears(20));
    }

    @Test
    void goodPerson_savePerson() {
        personDAO.addPerson(goodPerson);
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getId(), goodPerson.getId());
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getName(), goodPerson.getName());
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getBirthdate(), goodPerson.getBirthdate());
        personDAO.clear();
    }

    @Test
    void goodPerson_findPerson() {
        personDAO.addPerson(goodPerson);
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getId(), goodPerson.getId());
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getName(), goodPerson.getName());
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getBirthdate(), goodPerson.getBirthdate());
        personDAO.clear();
    }

    @Test
    void nullIdPerson_notSavePerson() {
        personDAO.addPerson(nullIdPerson);
        Assertions.assertNull(personDAO.getPersonById(nullIdPerson.getId()));
    }

    @Test
    void goodPerson_clear() {
        personDAO.addPerson(goodPerson);
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getId(), goodPerson.getId());
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getName(), goodPerson.getName());
        assertEquals(personDAO.getPersonById(goodPerson.getId()).getBirthdate(), goodPerson.getBirthdate());
        personDAO.clear();
        Assertions.assertNull(personDAO.getPersonById(goodPerson.getId()));
    }

    @Test
    void goodPersons_listOfPersons() {
        personDAO.addPerson(goodPerson);
        personDAO.addPerson(goodPerson2);
        List<PersonDTO> persons = personDAO.getAllPersons();
        assertEquals(persons.get(0).getId(), goodPerson.getId());
        assertEquals(persons.get(0).getName(), goodPerson.getName());
        assertEquals(persons.get(0).getBirthdate(), goodPerson.getBirthdate());
        assertEquals(persons.get(1).getId(), goodPerson2.getId());
        assertEquals(persons.get(1).getName(), goodPerson2.getName());
        assertEquals(persons.get(1).getBirthdate(), goodPerson2.getBirthdate());
        personDAO.clear();
    }
}
