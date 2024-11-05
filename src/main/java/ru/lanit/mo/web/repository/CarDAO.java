package ru.lanit.mo.web.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.CarDTO;
import ru.lanit.mo.web.models.Car;
import ru.lanit.mo.web.utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDAO {

    public void addCar(CarDTO carDTO) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            ModelMapper modelMapper = new ModelMapper();
            session.save(modelMapper.map(carDTO, Car.class));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public List<CarDTO> getAllCars() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<CarDTO> carDTOS = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Query<Car> query = session.createQuery("from Car", Car.class);
            carDTOS = query.stream().map(car -> modelMapper.map(car, CarDTO.class)).collect(Collectors.toList());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return carDTOS;
    }

    public CarDTO getCarById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CarDTO carDTO = null;
        try {
            Query<Car> query = session.createQuery("from Car where id = :id", Car.class);
            query.setParameter("id", id);
            ModelMapper modelMapper = new ModelMapper();
            carDTO = modelMapper.map(query.getSingleResult(), CarDTO.class);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return carDTO;
    }

    public void clear() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("delete from Car").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
