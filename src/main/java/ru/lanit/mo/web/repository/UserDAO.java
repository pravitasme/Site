package ru.lanit.mo.web.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.lanit.mo.web.entity.User;

import java.sql.*;
import java.util.List;

@Repository
public class UserDAO
{
    static JdbcTemplate jdbcTemplate;

    public void setTemplate(JdbcTemplate template)
    {
        jdbcTemplate = template;
    }

    public static List<User> getAllUsers() throws SQLException, ClassNotFoundException
    {
        String selectFromUser = "select * from users";

        return jdbcTemplate.query(selectFromUser, new RowMapper<User>()
        {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setPatronymic(rs.getString("patronymic"));
                return user;
            }
        });
    }

    public static int addUser(User user) throws SQLException, ClassNotFoundException
    {
        String addUser = "insert into users(firstname, lastname, patronymic) VALUES (" + "'" + user.getFirstname() + "'" + "," + "'" + user.getLastname() + "'" + "," + "'" + user.getPatronymic() + "'" + ")";

        return jdbcTemplate.update(addUser);
    }

    public User getUserByID(int id) throws SQLException, ClassNotFoundException
    {
        String getUserByID = "select * from users where id = " + id;

        return jdbcTemplate.queryForObject(getUserByID, User.class);
    }

    public static int updateUser(User user) throws SQLException, ClassNotFoundException
    {
        String updateUser = "update users set firstname = " + "'" + user.getFirstname() + "'" + ", " + "lastname = " + "'" + user.getLastname() + "'" + ", " + "patronymic = " + "'" + user.getPatronymic() + "'" + " where id = " + user.getId();

        return jdbcTemplate.update(updateUser);
    }

    public static int deleteUser(int id) throws SQLException, ClassNotFoundException
    {
        String deleteUser = "delete from users where id = " + id;

        return jdbcTemplate.update(deleteUser);
    }
}
