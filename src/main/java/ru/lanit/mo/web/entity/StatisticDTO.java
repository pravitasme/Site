package ru.lanit.mo.web.entity;

public class StatisticDTO {

    Long personcount = 0L;
    Long carcount = 0L;
    Long uniquevendorcount = 0L;

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
}
