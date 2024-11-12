package ru.lanit.mo.web.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.lanit.mo.web.config.CustLocalDateDeserializer;
import ru.lanit.mo.web.config.CustLocalDateSerializer;

import java.util.List;
import java.time.LocalDate;
import java.util.Objects;

public class PersonDTO {

    private Long id;
    private String name;
    List<CarDTO> cars;

    @JsonDeserialize( using = CustLocalDateDeserializer.class)
    @JsonSerialize( using = CustLocalDateSerializer.class )
    private LocalDate birthdate;

    private PersonDTO() {}

    public PersonDTO(Long id, String name, List<CarDTO> cars, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.cars = cars;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(name, personDTO.name) && Objects.equals(cars, personDTO.cars) && Objects.equals(birthdate, personDTO.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cars, birthdate);
    }
}
