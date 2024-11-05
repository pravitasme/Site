package ru.lanit.mo.web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.service.PersonService;

@RestController
public class PersonWithCarsController {

    @Autowired
    private PersonService personService;

    public PersonWithCarsController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/personwithcars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPersonWithCars(@RequestParam Long personid) {
        PersonDTO personDTO;
        try {
            personDTO = personService.getPerson(personid);
            if(personDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
}
