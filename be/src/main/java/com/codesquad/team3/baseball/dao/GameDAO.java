package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GameDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public String getPitcher(Integer teamId) {
        String sql = "SELECT name FROM player WHERE team = :team_id AND is_pitcher = true";
        SqlParameterSource namedParameters = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

//TODO: 현재 타자 가져오는 로직 추가
    public PlayerDTO getHitter(Integer gameId, Integer teamId) {

        return null;

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

    public void addAtBatHitter(Integer halfInningId, Integer gameId, boolean isTop) {
        //현재 게임의 공격 팀 id 가져오기
        Integer teamId;
        if(isTop) {
            teamId = findAttackTeam(gameId, false);
        } else {
            teamId = findAttackTeam(gameId, true);
        }

        Integer playerId = findPlayerIdWithBattingOrder(teamId, 1);
        String sql = "INSERT INTO at_bat (half_inning, hitter) VALUES (:half_inning, :hitter)";
        SqlParameterSource parameterSource = new MapSqlParameterSource("half_inning", halfInningId).addValue("hitter", playerId);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    private Integer findAttackTeam(Integer gameId, boolean isHome) {
        String sql = "SELECT team FROM team_game WHERE game = :game_id AND is_home = :is_home";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId).addValue("is_home", isHome);
        return namedParameterJdbcTemplate.queryForObject(sql,parameterSource,Integer.class);
    }

    private Integer findPlayerIdWithBattingOrder(Integer teamId, int batting_order) {
        String sql = "SELECT id FROM player WHERE team = :team_id AND batting_order = :order";
        SqlParameterSource parameterSource = new MapSqlParameterSource("team_id", teamId).addValue("order", batting_order);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    public Integer findHalfInningId(Integer gameId, int inning, boolean isTop) {
        String sql = "SELECT id FROM half_inning WHERE game = :game_id AND inning = :inning AND is_top = :is_top";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId).addValue("inning", inning).addValue("is_top", isTop);
        return namedParameterJdbcTemplate.queryForObject(sql,parameterSource,Integer.class);
    }

}
