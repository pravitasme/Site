package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.repository.PersonDAO;
import java.time.LocalDate;
import java.util.List;
import javax.validation.ValidationException;

@Service
public class PersonService {

    @Autowired
    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Transactional
    public void addPerson(PersonDTO personDTO) {
        personDAO.addPerson(personDTO);
    }

    @Transactional
    public PersonDTO getPerson(Long id) {
        return personDAO.getPersonById(id);
    }

    public void validatePerson(PersonDTO personDTO) throws ValidationException {
        LocalDate now = LocalDate.now();
        if (personDTO.getId() == null) {
            throw new ValidationException("Person ID is null");
        }
        if (personDTO.getName() == null) {
            throw new ValidationException("Person name is null");
        }
        if (personDTO.getBirthdate() == null) {
            throw new ValidationException("Person birthdate is null");
        }
        if (personDTO.getBirthdate().isAfter(now)) {
            throw new ValidationException("Person birthdate is after now");
        }
        if (personDAO.getPersonById(personDTO.getId()) != null) {
            throw new ValidationException("Person already exists");
        }
    }

    @Transactional
    public List<PersonDTO> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Transactional
    public void clear() {
        personDAO.clear();
    }
}
