package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.domain.User;
import com.codesquad.team3.baseball.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public User getUser(String email) {
        String sql = "SELECT * FROM user WHERE email = :email";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        try{
            return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, ((rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                return user;
            }));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException();
        }

    }
}
