package ru.lanit.mo.web.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    private House house;

    public User()
    {
    }
}
