package ru.lanit.mo.web.entity;

import java.awt.*;
import java.util.List;

public class HouseDTO
{
    private int id;
    private String address;
    private String color;
    private List<UserDTO> users;

    public HouseDTO(int id, String address, String color, List<UserDTO> users)
    {
        this.id = id;
        this.address = address;
        this.color = color;
        this.users = users;
    }

    public HouseDTO(String address, String color, List<UserDTO> users)
    {
        this.address = address;
        this.color = color;
        this.users = users;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
