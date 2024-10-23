package ru.lanit.mo.web.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.HouseDTO;
import ru.lanit.mo.web.models.House;
import ru.lanit.mo.web.utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HouseDAO {

    public List<HouseDTO> getAllHouses() {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<House> query = session.createQuery("from House", House.class);
        List<HouseDTO> houseDTOS = query.stream().map(house -> modelMapper.map(house, HouseDTO.class)).collect(Collectors.toList());
        //tx.commit();
        session.close();
        return houseDTOS;
    }

    public void addHouse(HouseDTO houseDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(modelMapper.map(houseDTO, House.class));
        tx.commit();
        session.close();
    }

    public void deleteHouse(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        House house = session.get(House.class, id);
        session.delete(house);
        tx.commit();
        session.close();
    }
}
