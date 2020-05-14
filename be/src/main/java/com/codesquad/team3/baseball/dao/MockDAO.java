package com.codesquad.team3.baseball.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class MockDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MockDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void resetGame(Integer gameId) {
        String sql = "DELETE FROM half_inning WHERE game = :gameId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("gameId", gameId);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        sql = "DELETE FROM hitter_record WHERE team_game_game = :gameId";
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        sql = "DELETE FROM pitcher_record WHERE team_game_game = :gameId";
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        sql ="UPDATE game SET home_batting_order = 1, away_batting_order = 1, is_over = false WHERE id = 1";
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
