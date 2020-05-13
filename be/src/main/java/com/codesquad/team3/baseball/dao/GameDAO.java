package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.dto.HitterDTO;
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

    public String getPitcherName(Integer gameId, boolean isTop) {
        Integer teamId = findDefenceTeamIdWithGameId(gameId, isTop);

        String sql = "SELECT name FROM player WHERE team = :team_id AND is_pitcher = true";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    public HitterDTO getHitter(Integer gameId, boolean isTop) {
        //현재 공격 타순 가져오기
        Integer teamId = findAttackTeamIdWithGameId(gameId, isTop);
        Integer nowHitterBattingOrder = findBattingOrderWithGameId(gameId, isTop);
        String sql = "SELECT p.name, p.batting_order, h.at_bat, h.hit, h.outs " +
                "FROM player p JOIN hitter_record h " +
                "ON p.id = h.player " +
                "WHERE team = ? " +
                "AND batting_order = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {teamId, nowHitterBattingOrder}, ((rs, rowNum) -> {
            return new HitterDTO.Builder()
                    .name(rs.getString("name"))
                    .order(rs.getInt("batting_order"))
                    .build();
        }));
    }

    public void addHalfInning(Integer gameId, boolean isTop, int inning) {
        String sql = "INSERT INTO half_inning (is_top, inning, game) VALUES (:is_top, :inning, :game_id)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("is_top", isTop)
                                                                .addValue("inning", inning)
                                                                .addValue("game_id", gameId);
        namedParameterJdbcTemplate.update(sql, namedParameters);
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

    public void addTeamPitcherRecordRecords(Integer gameId, Integer teamId) {
        Integer pitcherId = findPitcherId(teamId);
        String sql = "INSERT INTO pitcher_record (team_game_team, team_game_game, player) VALUES (:team_id, :game_id, :player_id)";
        SqlParameterSource parameterSource = new MapSqlParameterSource("team_id", teamId)
                                                                .addValue("game_id", gameId)
                                                                .addValue("player_id", pitcherId);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public List<Integer> findDefenceTeamIdWithGameId(Integer gameId) {
        String sql = "SELECT team FROM team_game WHERE game = :game_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId);
        return namedParameterJdbcTemplate.queryForList(sql,parameterSource, Integer.class);
    }

    public void addTeamHitterRecordRecords(Integer gameId, Integer teamId) {
        List<Integer> hitterIds =  findHitterIds(teamId);
        String sql = "INSERT INTO hitter_record (team_game_team, team_game_game, player) VALUES (:team_id, :game_id, :player_id)";
        SqlParameterSource parameterSource;

        for (Integer id: hitterIds) {
            parameterSource = new MapSqlParameterSource("team_id", teamId).addValue("game_id", gameId).addValue("player_id", id);
            namedParameterJdbcTemplate.update(sql, parameterSource);
        }
    }

    public String findTeamNameWithGameId(Integer gameId, boolean isHome) {
        String sql = "SELECT t.name " +
                "FROM team_game g " +
                "JOIN team t " +
                "ON g.team = t.id " +
                "WHERE game = :game_id " +
                "AND is_home = :is_home";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId)
                                                                .addValue("is_home", isHome);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    public int getHalfInningCount(Integer gameId) {
        String sql = "SELECT COUNT(*) FROM half_inning WHERE game = :game_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    private List<Integer> findHitterIds(Integer teamId) {
        String sql = "SELECT id FROM player WHERE team = :team_id AND is_pitcher = false";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.query(sql, namedParameters,((rs, rowNum) -> {
            return rs.getInt("id");
        }));
    }

    private Integer findPitcherId(Integer teamId) {
        String sql = "SELECT id FROM player WHERE team = :team_id AND is_pitcher = true";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public Integer findDefenceTeamIdWithGameId(Integer gameId, boolean isTop) {
        String sql;
        if(isTop) {
            sql = "SELECT team FROM team_game WHERE game = :game_id AND is_home = true";
        } else {
            sql = "SELECT team FROM team_game WHERE game = :game_id AND is_home = false";
        }
        SqlParameterSource parameterSource =new MapSqlParameterSource("game_id", gameId);
        return namedParameterJdbcTemplate.queryForObject(sql,parameterSource, Integer.class);
    }

    public Integer findAttackTeamIdWithGameId(Integer gameId, boolean isTop) {
        String sql;
        if(isTop) {
            sql = "SELECT team FROM team_game WHERE game = :game_id AND is_home = false";
        } else {
            sql = "SELECT team FROM team_game WHERE game = :game_id AND is_home = true";
        }
        SqlParameterSource parameterSource =new MapSqlParameterSource("game_id", gameId);
        return namedParameterJdbcTemplate.queryForObject(sql,parameterSource, Integer.class);
    }
}
