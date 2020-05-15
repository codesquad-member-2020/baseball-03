package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.dto.ScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.stream.Collectors;

@Repository
public class BoardDAO {

    private InGameDAO inGameDAO;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BoardDAO(DataSource dataSource, InGameDAO inGameDAO) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.inGameDAO = inGameDAO;
    }

    public ScoreDTO getTeamScores(Integer gameId, boolean isHome) {
        String sql;
        if(isHome) {
            sql = "SELECT tg.name, tg.is_home, h.is_top, GROUP_CONCAT(h.score) AS score " +
                    "FROM (SELECT t.name, g.is_home, g.game FROM team_game g JOIN team t ON g.team = t.id WHERE game = :game_id AND is_home = true) AS tg " +
                    "JOIN half_inning h " +
                    "ON tg.game = h.game " +
                    "WHERE h.game = :game_id2 " +
                    "AND is_top = false " +
                    "GROUP BY h.game";
        } else {
            sql = "SELECT tg.name, tg.is_home, h.is_top, GROUP_CONCAT(h.score) AS score " +
                    "FROM (SELECT t.name, g.is_home, g.game FROM team_game g JOIN team t ON g.team = t.id WHERE game = :game_id AND is_home = false) AS tg " +
                    "JOIN half_inning h " +
                    "ON tg.game = h.game " +
                    "WHERE h.game = :game_id2 " +
                    "AND is_top = true " +
                    "GROUP BY h.game";
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId).addValue("game_id2", gameId);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, ((rs, rowNum) -> {
            ScoreDTO scoreDTO = new ScoreDTO(rs.getString("name"),
                    isAttackCheck(rs.getBoolean("is_home"), inGameDAO.findLastHalfInning(gameId).isTop()),
                    Arrays.stream(rs.getString("score").split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
            return scoreDTO;
        }));
    }

    private boolean isAttackCheck(boolean is_home, boolean is_top) {
        return is_home != is_top;
    }
}
