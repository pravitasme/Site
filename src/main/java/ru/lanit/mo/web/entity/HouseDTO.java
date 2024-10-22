package ru.lanit.mo.web.entity;

import java.util.List;

public class HouseDTO
{
    private int house_id;
    private String address;
    private String color;
    private List<UserDTO> users;

    public HouseDTO()
    {

    }

    public int getHouse_id()
    {
        return house_id;
    }

    public void setHouse_id(int house_id)
    {
        this.house_id = house_id;
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

    public List<UserDTO> getUsers()
    {
        return users;
    }

    public void setUsers(List<UserDTO> users)
    {
        this.users = users;
    }
}
