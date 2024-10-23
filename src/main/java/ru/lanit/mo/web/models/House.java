package ru.lanit.mo.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "houses")
@Proxy(lazy = false)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int house_id;

    @Column(name = "address")
    private String address;
    @Column(name = "color")
    private String color;


    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval - удаление бездомных
    @JsonIgnore
    private List<User> users;

    public House() {}

    public House(int house_id, String address, String color) {
        this.house_id = house_id;
        this.address = address;
        this.color = color;
    }

    public void addUser(User user) {
        user.setHouse(this);
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void setHouse_id(int id) {
        this.house_id = id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonIgnore
    public List<User> getUsers() {
        return users;
    }

    @JsonProperty
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "models.House{" +
                "id=" + house_id +
                ", address='" + address + '\'' +
                ", color=" + color +
                '}';
    }
}
