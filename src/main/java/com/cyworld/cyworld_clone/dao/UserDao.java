package com.cyworld.cyworld_clone.dao;

import com.cyworld.cyworld_clone.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(UserDto user) {
        String sql = "insert into users(username, password, name, birth_date, email, phone, gender) values(?,?,?,?,?,?,?)";


        return jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                java.sql.Date.valueOf(user.getBirthDayAsLocalDate()),
                user.getEmail(),
                user.getPhone(),
                user.getGender()
        );
    }


    public UserDto findByUsername(String username) {
        String sql = "select * from users where username = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                UserDto user = new UserDto();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getString("birth_date"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserDto findById(int id) {
        String sql = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserDto.class), id);
    }

}
