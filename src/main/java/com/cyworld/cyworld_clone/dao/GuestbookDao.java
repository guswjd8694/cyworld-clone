package com.cyworld.cyworld_clone.dao;


import com.cyworld.cyworld_clone.dto.GuestbookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestbookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertGuestbookEntry(GuestbookDto guestbookDto) {
        System.out.println("guestbook.getUserId() = " + guestbookDto.getUserId());  // userId 값 찍어보기

        String sql = "insert into guestbook (writer, content, user_id) values(?,?,?)";
        jdbcTemplate.update(sql, guestbookDto.getWriter(), guestbookDto.getContent(), guestbookDto.getUserId());
    }

    public List<GuestbookDto> findAllGuestbookEntries() {
        String sql = "select * from guestbook order by id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GuestbookDto.class));
    }

    public List<GuestbookDto> findEntriesByPage(int page, int limit) {
        String sql = "select * from guestbook order by id desc limit ? offset ?";
        int offset = (page -1) * limit;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GuestbookDto.class), limit, offset);
    }

    public int totalCount(){
        String sql = "select count(*) from guestbook";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
