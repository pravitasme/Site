package ru.lanit.mo.web.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "houses")
public class House
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;
    @Column(name = "color")
    private String color;

    //orphanRemoval - удаление бездомных
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    public House()
    {

    }

    public House(String address, String color)
    {
        this.address = address;
        this.color = color;
        users = new ArrayList<User>();
    }

    public void addUser(User user)
    {
        user.setHouse(this);
        users.add(user);
    }

    public void removeUser(User user)
    {
        users.remove(user);
    }

    public int getId()
    {
        return id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "models.House{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", color=" + color +
                '}';
    }
}
