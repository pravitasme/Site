package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.entity.StatisticDTO;

import java.util.HashSet;
import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private final CarService carService;

    @Autowired
    private final PersonService personService;

    public StatisticService(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    StatisticDTO statistic = new StatisticDTO(0L, 0L, 0L);
    HashSet<String> vendors = new HashSet<>();

    public StatisticDTO getStatistic() {
        List<CarDTO> cars = carService.getAllCars();
        statistic.setCarcount((long) cars.size());
        statistic.setPersoncount((long) personService.getAllPersons().size());
        for (CarDTO car : cars) {
            vendors.add(car.getModel().split("-")[0].toUpperCase());
        }
        statistic.setUniquevendorcount((long) vendors.size());
        return statistic;
    }

    public void clearStatistic() {
        statistic.setPersoncount(0L);
        statistic.setCarcount(0L);
        statistic.setUniquevendorcount(0L);
        vendors = new HashSet<>();
    }
}
