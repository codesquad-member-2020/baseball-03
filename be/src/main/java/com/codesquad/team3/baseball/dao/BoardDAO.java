package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.domain.TeamGame;
import com.codesquad.team3.baseball.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BoardDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public PlayerDTO getPitcher(Integer gameId, Integer teamId) {
        String sql = "SELECT p.name, pr.pitch" +
                " FROM pitcher_record pr" +
                " LEFT OUTER JOIN player p ON pr.player = p.id" +
                " WHERE pr.team_game_team = :teamId" +
                " AND pr.team_game_game = :gameId";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource()
                .addValue("teamId", teamId)
                .addValue("gameId", gameId), (rs, rowNum) -> new PlayerDTO.Builder(rs.getString("name"))
                        .pitchCount(rs.getInt("pitch"))
                        .order(-1)
                        .atBats(-1)
                        .hits(-1)
                        .outs(-1)
                        .avg(-1)
                        .build());
    }

    public List<PlayerDTO> getHitters(Integer gameId, Integer teamId) {
        String sql = "SELECT p.name, p.batting_order, hr.at_bat, hr.hit, hr.outs, p.average" +
                " FROM hitter_record hr" +
                " LEFT OUTER JOIN player p ON hr.player = p.id" +
                " WHERE hr.team_game_team = :teamId" +
                " AND hr.team_game_game = :gameId";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource()
                .addValue("teamId", teamId)
                .addValue("gameId", gameId), (rs, rowNum) -> {
            PlayerDTO.Builder builder = new PlayerDTO.Builder(rs.getString("name"))
                    .order(rs.getInt("batting_order"))
                    .atBats(rs.getInt("at_bat"))
                    .hits(rs.getInt("hit"))
                    .outs(rs.getInt("outs"))
                    .avg(rs.getFloat("average"));
            return builder.build();
        });
    }

    public TeamGame getOppositeTeamGame(Integer gameId, Integer teamId) {
        String sql = "SELECT is_home, team, game, user FROM team_game" +
                " WHERE game = :gameId" +
                " AND team != :teamId";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource()
                .addValue("gameId", gameId)
                .addValue("teamId", teamId), (rs, rowNum) -> new TeamGame(
                        rs.getInt("team"),
                        null,
                        null,
                        rs.getBoolean("is_home")));
    }
}
