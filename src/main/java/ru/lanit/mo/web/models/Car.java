package ru.lanit.mo.web.models;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    private long id;
    @Column(name = "model")
    private String model;
    @Column(name = "horsepower")
    private int horsepower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerid")
    private Person owner;

    public Car() {}

    public Car(String model, int horsepower, Person owner) {
        this.model = model;
        this.horsepower = horsepower;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
