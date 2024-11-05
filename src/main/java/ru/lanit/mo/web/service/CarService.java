package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.repository.CarDAO;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class CarService {

    @Autowired
    private final CarDAO carDAO;

    @Autowired
    private final PersonService personService;

    public CarService(CarDAO carDAO, PersonService personService) {
        this.carDAO = carDAO;
        this.personService = personService;
    }

    @Transactional
    public void addCar(CarDTO carDTO) {
        carDAO.addCar(carDTO);
    }

    public void validateCar(CarDTO carDTO) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+-[A-Za-z0-9-]+$");
        if (carDTO.getId() == null) {
            throw new ValidationException("Car ID is null");
        }
        if (carDTO.getModel() == null) {
            throw new ValidationException("Car model is null");
        }
        if (!Pattern.matches(pattern.toString(), carDTO.getModel())) {
            throw new ValidationException("Car model is not valid");
        }
        if (carDTO.getHorsepower() <= 0) {
            throw new ValidationException("Horse power is less than 0 or equal to 0");
        }
        if (carDTO.getOwnerId() == null) {
            throw new ValidationException("Owner ID is null");
        }
        LocalDate birthdate = personService.getPerson(carDTO.getOwnerId()).getBirthdate();
        if (Period.between(birthdate, LocalDate.now()).getYears() < 18) {
            throw new ValidationException("Owner is too young");
        }
        if (carDAO.getCarById(carDTO.getId()) != null) {
            throw new ValidationException("Car already exists");
        }
    }

    @Transactional
    public List<CarDTO> getAllCars() {
        return carDAO.getAllCars();
    }

    @Transactional
    public void clear() {
        carDAO.clear();
    }
}
