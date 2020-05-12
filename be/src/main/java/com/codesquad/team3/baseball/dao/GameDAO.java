package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.dto.HitterDTO;
import com.codesquad.team3.baseball.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GameDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getPitcherName(Integer teamId) {
        String sql = "SELECT name FROM player WHERE team = :team_id AND is_pitcher = true";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    public HitterDTO getHitter(Integer gameId, Integer teamId, boolean isTop) {
        //현재 공격 타순 가져오기
        Integer nowHitterBattingOrder = findBattingOrderWithGameId(gameId, isTop);
        String sql = "SELECT p.name, p.batting_order, p.average, h.at_bat, h.hit, h.outs " +
                "FROM player p JOIN hitter_record h " +
                "ON p.id = h.player " +
                "WHERE team = ? " +
                "AND batting_order = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {teamId, nowHitterBattingOrder}, ((rs, rowNum) -> {
            HitterDTO hitterDTO = new HitterDTO(rs.getString("name"),
                                                rs.getInt("batting_order"),
                                                rs.getInt("at_bat"),
                                                rs.getInt("hit"),
                                                rs.getInt("outs"),
                                                rs.getFloat("average"));
            return hitterDTO;
        }));
    }

    public void addHalfInning(Integer gameId, boolean isTop, int inning) {
        String sql = "INSERT INTO half_inning (is_top, inning, game) VALUES (:is_top, :inning, :game_id)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("is_top", isTop)
                                                                .addValue("inning", inning)
                                                                .addValue("game_id", gameId);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public void addTeamRecord(Integer gameId) {
        List<Integer> teams = findTeamIdWithGameId(gameId);
        for (Integer team:teams) {
            addTeamPitcherRecordRecords(gameId, team);
            addTeamHitterRecordRecords(gameId, team);
        }
    }

    public boolean checkHome(Integer gameId, Integer teamId) {
        String sql = "SELECT is_home FROM team_game WHERE game = :game_id AND team = :team_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId).addValue("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Boolean.class);
    }

    public Integer findBattingOrderWithGameId(Integer gameId, boolean isTop) {
        String sql;
        if(isTop) {
            sql = "SELECT away_batting_order FROM game WHERE id = :game_id";
        } else {
            sql = "SELECT home_batting_order FROM game WHERE id = :game_id";
        }
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    private void addTeamPitcherRecordRecords(Integer gameId, Integer teamId) {
        Integer pitcherId = findPitcherId(teamId);
        String sql = "INSERT INTO pitcher_record (team_game_team, team_game_game, player) VALUES (:team_id, :game_id, :player_id)";
        SqlParameterSource parameterSource = new MapSqlParameterSource("team_id", teamId)
                                                                .addValue("game_id", gameId)
                                                                .addValue("player_id", pitcherId);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    private Integer findPitcherId(Integer teamId) {
        String sql = "SELECT id FROM player WHERE team = :team_id AND is_pitcher = true";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    private List<Integer> findTeamIdWithGameId(Integer gameId) {
        String sql = "SELECT team FROM team_game WHERE game = :game_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId);
        return namedParameterJdbcTemplate.queryForList(sql,parameterSource, Integer.class);
    }

    private void addTeamHitterRecordRecords(Integer gameId, Integer teamId) {
        List<Integer> hitterIds = findHitterIds(teamId);
        String sql = "INSERT INTO hitter_record (team_game_team, team_game_game, player) VALUES (:team_id, :game_id, :player_id)";
        SqlParameterSource parameterSource;

        for (Integer id: hitterIds) {
            parameterSource = new MapSqlParameterSource("team_id", teamId).addValue("game_id", gameId).addValue("player_id", id);
            namedParameterJdbcTemplate.update(sql, parameterSource);
        }
    }

    private List<Integer> findHitterIds(Integer teamId) {
        String sql = "SELECT id FROM player WHERE team = :team_id AND is_pitcher = false";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.query(sql, namedParameters,((rs, rowNum) -> {
            return rs.getInt("id");
        }));
    }

    public Integer findHalfInningId(Integer gameId, int inning, boolean isTop) {
        String sql = "SELECT id FROM half_inning WHERE game = :game_id AND inning = :inning AND is_top = :is_top";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId).addValue("inning", inning).addValue("is_top", isTop);
        return namedParameterJdbcTemplate.queryForObject(sql,parameterSource,Integer.class);
    }

}
