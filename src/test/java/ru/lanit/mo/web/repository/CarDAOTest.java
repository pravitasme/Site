package ru.lanit.mo.web.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.PersonDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarDAOTest {

    static CarDTO goodCar;
    static CarDTO goodCar2;
    static CarDTO nullIdCar;
    static PersonDTO person;

    private static final CarDAO carDAO = new CarDAO();
    private static final PersonDAO personDAO = new PersonDAO();

    @BeforeAll
    static void setUp() {
        goodCar = new CarDTO(-1L, "VENDOR-MODEL", 101, -1L);
        goodCar2 = new CarDTO(-2L, "VENDOR-MODEL2", 102, -1L);
        nullIdCar = new CarDTO(null, "VENDOR-MODEL3", 100, -1L);
        List<CarDTO> cars = new ArrayList<>();
        cars.add(goodCar);
        cars.add(goodCar2);
        person = new PersonDTO(-1L, "name", null, LocalDate.now().minusYears(20));
    }

    @Test
    void goodCar_saveCar() {
        personDAO.addPerson(person);
        carDAO.addCar(goodCar);
        assertEquals(carDAO.getCarById(goodCar.getId()).getId(), goodCar.getId());
        assertEquals(carDAO.getCarById(goodCar.getId()).getModel(), goodCar.getModel());
        assertEquals(carDAO.getCarById(goodCar.getId()).getHorsepower(), goodCar.getHorsepower());
        assertEquals(carDAO.getCarById(goodCar.getId()).getOwnerId(), goodCar.getOwnerId());
        carDAO.clear();
        personDAO.clear();
    }

    @Test
    void goodCar_findCar() {
        personDAO.addPerson(person);
        carDAO.addCar(goodCar);
        assertEquals(carDAO.getCarById(goodCar.getId()).getId(), goodCar.getId());
        assertEquals(carDAO.getCarById(goodCar.getId()).getModel(), goodCar.getModel());
        assertEquals(carDAO.getCarById(goodCar.getId()).getHorsepower(), goodCar.getHorsepower());
        assertEquals(carDAO.getCarById(goodCar.getId()).getOwnerId(), goodCar.getOwnerId());
        carDAO.clear();
        personDAO.clear();
    }

    @Test
    void nullIdCar_notSaveCar() {
        carDAO.addCar(nullIdCar);
        Assertions.assertNull(carDAO.getCarById(0L));
    }

    @Test
    void goodCar_clear() {
        personDAO.addPerson(person);
        carDAO.addCar(goodCar);
        assertEquals(carDAO.getCarById(goodCar.getId()).getId(), goodCar.getId());
        assertEquals(carDAO.getCarById(goodCar.getId()).getModel(), goodCar.getModel());
        assertEquals(carDAO.getCarById(goodCar.getId()).getHorsepower(), goodCar.getHorsepower());
        assertEquals(carDAO.getCarById(goodCar.getId()).getOwnerId(), goodCar.getOwnerId());
        carDAO.clear();
        Assertions.assertNull(carDAO.getCarById(goodCar.getId()));
        personDAO.clear();
    }

    @Test
    void goodCars_listOfCars() {
        personDAO.addPerson(person);
        carDAO.addCar(goodCar);
        carDAO.addCar(goodCar2);
        List<CarDTO> cars = carDAO.getAllCars();
        assertEquals(cars.get(0).getId(), goodCar.getId());
        assertEquals(cars.get(0).getModel(), goodCar.getModel());
        assertEquals(cars.get(0).getHorsepower(), goodCar.getHorsepower());
        assertEquals(cars.get(0).getOwnerId(), goodCar.getOwnerId());
        assertEquals(cars.get(1).getId(), goodCar2.getId());
        assertEquals(cars.get(1).getModel(), goodCar2.getModel());
        assertEquals(cars.get(1).getHorsepower(), goodCar2.getHorsepower());
        assertEquals(cars.get(1).getOwnerId(), goodCar2.getOwnerId());
        carDAO.clear();
        personDAO.clear();
    }
}
