package ru.lanit.mo.web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.mo.web.entity.StatisticDTO;
import ru.lanit.mo.web.service.StatisticService;

@RestController
public class StatisticController {

    @Autowired
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStatistics() {
        StatisticDTO statistics = statisticService.getStatistic();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
