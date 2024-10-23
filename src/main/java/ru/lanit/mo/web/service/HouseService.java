package ru.lanit.mo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.mo.web.entity.HouseDTO;
import ru.lanit.mo.web.models.House;
import ru.lanit.mo.web.repository.HouseDAO;

import java.util.List;

@Service
public class HouseService {

    private final HouseDAO houseDAO;

    @Autowired
    public HouseService(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Transactional
    public List<HouseDTO> getAllHouses() {
        return houseDAO.getAllHouses();
    }

    @Transactional
    public void addHouse(HouseDTO houseDTO) {
        houseDAO.addHouse(houseDTO);
    }

    @Transactional
    public void deleteHouse(int id) {
        houseDAO.deleteHouse(id);
    }
}
