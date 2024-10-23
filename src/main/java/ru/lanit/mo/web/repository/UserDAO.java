package ru.lanit.mo.web.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.UserDTO;
import ru.lanit.mo.web.models.User;
import ru.lanit.mo.web.utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAO {

    public List<UserDTO> getAllUsers() {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User", User.class);
        List<UserDTO> userDTOS = query.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        //tx.commit();
        session.close();
        return userDTOS;
    }

    public void addUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(modelMapper.map(userDTO, User.class));
        tx.commit();
        session.close();
    }

    public UserDTO getUserByID(int id) {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where id = :id", User.class);
        query.setParameter("id", id);
        UserDTO userDTO = modelMapper.map(query.getSingleResult(), UserDTO.class);
        //tx.commit();
        session.close();
        return userDTO;
    }

    public void updateUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(modelMapper.map(userDTO, User.class));
        tx.commit();
        session.close();
    }

    public void deleteUser(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        tx.commit();
        session.close();
    }
}
