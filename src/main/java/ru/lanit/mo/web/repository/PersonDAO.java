package ru.lanit.mo.web.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.PersonDTO;
import ru.lanit.mo.web.models.Person;
import ru.lanit.mo.web.utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonDAO {

    public void addPerson(PersonDTO personDTO) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            ModelMapper modelMapper = new ModelMapper();
            Person person = modelMapper.map(personDTO, Person.class);
            session.save(modelMapper.map(personDTO, Person.class));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public List<PersonDTO> getAllPersons() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<PersonDTO> personDTOS = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Query<Person> query = session.createQuery("from Person", Person.class);
            personDTOS = query.stream().map(person -> modelMapper.map(person, PersonDTO.class)).collect(Collectors.toList());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return personDTOS;
    }

    public PersonDTO getPersonById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        PersonDTO personDTO = null;
        try {
            Query<Person> query = session.createQuery("from Person where id = :id", Person.class);
            query.setParameter("id", id);
            ModelMapper modelMapper = new ModelMapper();
            personDTO = modelMapper.map(query.getSingleResult(), PersonDTO.class);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return personDTO;
    }

    public void clear() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("delete from Person").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
