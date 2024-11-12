package ru.lanit.mo.web.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class StatisticDTO {

    Long personcount = 0L;
    Long carcount = 0L;
    Long uniquevendorcount = 0L;

    public StatisticDTO(Long personcount, Long carcount, Long uniquevendorcount) {
        this.personcount = personcount;
        this.carcount = carcount;
        this.uniquevendorcount = uniquevendorcount;
    }

    public Long getPersoncount() {
        return personcount;
    }

    public void setPersoncount(Long personcount) {
        this.personcount = personcount;
    }

    public Long getCarcount() {
        return carcount;
    }

    public void setCarcount(Long carcount) {
        this.carcount = carcount;
    }

    public Long getUniquevendorcount() {
        return uniquevendorcount;
    }

    public void setUniquevendorcount(Long uniquevendorcount) {
        this.uniquevendorcount = uniquevendorcount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDTO that = (StatisticDTO) o;
        return Objects.equals(personcount, that.personcount) && Objects.equals(carcount, that.carcount) && Objects.equals(uniquevendorcount, that.uniquevendorcount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personcount, carcount, uniquevendorcount);
    }
}
