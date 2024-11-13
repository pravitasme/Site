package ru.lanit.mo.web.entity;

import java.util.Objects;

public class CarDTO {

    private Long id;
    private String model;
    private int horsepower;
    private Long ownerId;

    public CarDTO() {}

    public CarDTO(Long id, String model, int horsepower, Long ownerId) {
        this.id = id;
        this.model = model;
        this.horsepower = horsepower;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarDTO carDTO = (CarDTO) o;
        return horsepower == carDTO.horsepower && Objects.equals(id, carDTO.id) && Objects.equals(model, carDTO.model) && Objects.equals(ownerId, carDTO.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, horsepower, ownerId);
    }
}
