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
public class UserDAO
{
    public List<UserDTO> getAllUsers()
    {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession(); //here
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User");
        List users = query.list();
        tx.commit();
        session.close();
        return (List<UserDTO>) users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public void addUser(UserDTO userDTO)
    {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(modelMapper.map(userDTO, User.class));
        tx.commit();
        session.close();
    }

    public UserDTO getUserByID(int id)
    {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        tx.commit();
        session.close();
        return modelMapper.map(user, UserDTO.class);
    }

    public void updateUser(UserDTO userDTO)
    {
        ModelMapper modelMapper = new ModelMapper();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(modelMapper.map(userDTO, User.class));
        tx.commit();
        session.close();
    }

    public void deleteUser(int id)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        tx.commit();
        session.close();
    }
}
