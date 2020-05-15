package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.domain.*;
import com.codesquad.team3.baseball.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InGameDAO {

    private static final Logger log = LoggerFactory.getLogger(InGameDAO.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public InGameDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public TeamGame findTeamGameByGameIdAndTeamId(Integer gameId, Integer teamId) {
        Game game = findGameById(gameId);
        game.setHalfInnings(findHalfInningsByGameId(gameId));
        // 추후 user select도 추가
        return new TeamGame(teamId, game, null, checkHome(gameId, teamId));
    }

    public AtBat findLastAtBatByHalfInning(Integer halfInningId) {
        String sql = "SELECT id, hitter, strikes, balls" +
                " FROM at_bat" +
                " WHERE half_inning = :half_inning" +
                " ORDER BY id DESC" +
                " LIMIT 1";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("half_inning", halfInningId),
                (rs, rowNum) -> new AtBat(
                        rs.getInt("id"),
                        halfInningId,
                        rs.getInt("hitter"),
                        rs.getInt("strikes"),
                        rs.getInt("balls")));
    }

    public AtBat findAtBatById(Integer atBatId) {
        String sql = "SELECT id, half_inning, hitter, strikes, balls" +
                " FROM at_bat" +
                " WHERE id = :atBatId";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("atBatId", atBatId),
                (rs, rowNum) -> new AtBat(
                        rs.getInt("id"),
                        rs.getInt("half_inning"),
                        rs.getInt("hitter"),
                        rs.getInt("strikes"),
                        rs.getInt("balls")));
    }

    public Integer findOppositeTeamIdByGameIdAndTeamId(Integer gameId, Integer teamId) {
        String sql = "SELECT team" +
                " FROM team_game" +
                " WHERE game = :gameId" +
                " AND NOT team = :teamId";
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("gameId", gameId);
        parameters.put("teamId", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, parameters, Integer.class);
    }

    // 새롭게 추가된 하프이닝을 가져올 때
    public HalfInning findLastHalfInning(Integer gameId) {
        String sql = "SELECT id, score, is_top, inning," +
                " first_base, second_base, third_base, home_base," +
                " outs, strikes, balls" +
                " FROM half_inning " +
                " WHERE game = :gameId" +
                " ORDER BY id DESC" +
                " LIMIT 1";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("gameId", gameId),
                (rs, rowNum) -> new HalfInning.Builder(rs.getInt("id"))
                        .score(rs.getInt("score"))
                        .outCount(rs.getInt("outs"))
                        .isTop(rs.getBoolean("is_top"))
                        .inning(rs.getInt("inning"))
                        .firstBase(rs.getBoolean("first_base"))
                        .secondBase(rs.getBoolean("second_base"))
                        .thirdBase(rs.getBoolean("third_base"))
                        .homeBase(rs.getBoolean("home_base"))
                        .build());
    }

    // 게임 객체 가져오기
    public Game findGameById(Integer gameId) {
        String sql = "SELECT id, home_batting_order AS home, away_batting_order AS away, is_over" +
                " FROM game" +
                " WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", gameId),
                (rs, rowNum) -> new Game(
                        rs.getInt("id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getBoolean("is_over")));
    }

    public Player findHitterByOrder(Integer teamId, int hittingOrder) {
        String sql = "SELECT id, name, is_pitcher, average, batting_order, team, at_bat, hit, outs" +
                " FROM player p" +
                " LEFT OUTER JOIN hitter_record r ON p.id = r.player" +
                " WHERE team = :team" +
                " AND batting_order = :hittingOrder";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource()
                        .addValue("team", teamId)
                        .addValue("hittingOrder", hittingOrder),
                (rs, rowNum) -> new Player.Builder(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .isPitcher(rs.getBoolean("is_pitcher"))
                        .average(rs.getFloat("average"))
                        .battingOrder(rs.getInt("batting_order"))
                        .team(rs.getInt("team"))
                        .atBats(rs.getInt("at_bat"))
                        .hitCount(rs.getInt("hit"))
                        .outCount(rs.getInt("outs"))
                        .build());
    }

    public Player findPitcherByTeamId(Integer defendTeamId) {
        String sql = "SELECT p.id, p.name, p.is_pitcher, p.average, p.batting_order, p.team, r.pitch" +
                " FROM player p" +
                " LEFT OUTER JOIN pitcher_record r ON p.id = r.player" +
                " WHERE team = :defendTeamId" +
                " AND is_pitcher = true";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("defendTeamId", defendTeamId),
                (rs, rowNum) -> new Player.Builder(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .isPitcher(rs.getBoolean("is_pitcher"))
                        .average(rs.getFloat("average"))
                        .battingOrder(rs.getInt("batting_order"))
                        .team(rs.getInt("team"))
                        .pitches(rs.getInt("pitch"))
                        .build());
    }

    // 공격 api에서 사용
    // 가장 최근 게임 로그의 생성 시간과 비교
    public GameLog findLastGameLog(Integer gameId) throws EmptyResultDataAccessException {
        String sql = "SELECT gl.result AS result, gl.create_time AS create_time, gl.at_bat AS at_bat FROM game_log gl" +
                " LEFT OUTER JOIN at_bat at ON gl.at_bat = at.id" +
                " LEFT OUTER JOIN half_inning hi ON at.half_inning = hi.id" +
                " WHERE hi.game = :gameId" +
                " ORDER BY gl.id DESC" +
                " LIMIT 1;";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("gameId", gameId),
                (rs, rowNum) -> new GameLog(
                        Result.valueOf(rs.getString("result")),
                        rs.getTimestamp("create_time").toLocalDateTime(),
                        rs.getInt("at_bat")));
    }

    public AtBat findLastAtBat(Integer gameId) {
        String sql = "SELECT ab.id AS at_bat_id, ab.half_inning, ab.hitter, ab.strikes, ab.balls" +
                " FROM at_bat ab" +
                " LEFT OUTER JOIN half_inning hi ON ab.half_inning = hi.id" +
                " WHERE hi.game = :gameId" +
                " ORDER BY ab.id DESC" +
                " LIMIT 1";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("gameId", gameId),
                (rs, rowNum) -> new AtBat(
                        rs.getInt("at_bat_id"),
                        rs.getInt("half_inning"),
                        rs.getInt("hitter"),
                        rs.getInt("strikes"),
                        rs.getInt("balls")));
    }

    public boolean isManyAtBatsInHalfInning(Integer atBatId) {
        String sql = "SELECT COUNT(half_inning) > 1" +
                " FROM at_bat ab" +
                " WHERE ab.half_inning = (SELECT half_inning" +
                " FROM at_bat WHERE id = :atBatId)";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("atBatId", atBatId), Boolean.class);
    }

    public void addGameLog(GameLog log) {
        String sql = "INSERT INTO game_log (result, create_time, pitcher, at_bat)" +
                " VALUES (:result, :create_time, :pitcher, :at_bat)";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("result", log.getResult().name())
                .addValue("create_time", log.getCreateTime())
                .addValue("pitcher", log.getPitcher())
                .addValue("at_bat", log.getAtBat()));
    }

    public void addHalfInning(Integer gameId, boolean isTop, int inning) {
        String sql = "INSERT INTO half_inning (is_top, inning, game)" +
                " VALUES (:is_top, :inning, :game_id)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("is_top", isTop)
                .addValue("inning", inning)
                .addValue("game_id", gameId);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public void addAtBat(HalfInning halfInning, Player hitter) {
        String sql = "INSERT INTO at_bat (half_inning, hitter)" +
                " VALUES (:half_inning, :hitter)";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("half_inning", halfInning.getId())
                .addValue("hitter", hitter.getId()));
    }

    public void updateAtBat(AtBat atBat) {
        String sql = "UPDATE at_bat SET" +
                " strikes = :strikes," +
                " balls = :ball" +
                " WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("strikes", atBat.getStrikeCount())
                .addValue("ball", atBat.getBallCount())
                .addValue("id", atBat.getId()));
    }

    public void updateHitterRecord(Integer hitterId, Integer gameId, Integer teamId, Result result, AtBat atBat) {
        String sql = null;
        if (result == Result.HIT || atBat.is4Balls()) {
            sql = "UPDATE hitter_record SET hit = hit + 1, at_bat = at_bat + 1 WHERE team_game_team = :team AND team_game_game = :game AND player = :hitter";
        } else if (result == Result.OUT || atBat.is3Strikes()) {
            sql = "UPDATE hitter_record SET outs = outs + 1, at_bat = at_bat + 1 WHERE team_game_team = :team AND team_game_game = :game AND player = :hitter";
        }
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("team", teamId)
                .addValue("game", gameId)
                .addValue("hitter", hitterId));
    }

    public void updatePitcherRecord(Integer pitcherId, Integer gameId, Integer teamId) {
        String sql = "UPDATE pitcher_record SET pitch = pitch + 1" +
                " WHERE team_game_team = :team AND team_game_game = :game AND player = :pitcher";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("team", teamId)
                .addValue("game", gameId)
                .addValue("pitcher", pitcherId));
    }

    public void updateHalfInning(HalfInning halfInning) {
        String sql = "UPDATE half_inning SET" +
                " score = :score," +
                " first_base = :first," +
                " second_base = :second," +
                " third_base = :third," +
                " home_base = :home," +
                " outs = :outs" +
                " WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("score", halfInning.getScore())
                .addValue("first", halfInning.isFirstBase())
                .addValue("second", halfInning.isSecondBase())
                .addValue("third", halfInning.isThirdBase())
                .addValue("home", halfInning.isHomeBase())
                .addValue("outs", halfInning.getOutCount())
                .addValue("id", halfInning.getId()));
    }

    public void updateGame(Game game) {
        String sql = "UPDATE game SET" +
                " home_batting_order = :home_order," +
                " away_batting_order = :away_order" +
                " WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("home_order", game.getHomeBattingOrder())
                .addValue("away_order", game.getAwayBattingOrder())
                .addValue("id", game.getId()));
    }

    private List<HalfInning> findHalfInningsByGameId(Integer gameId) {
        String sql = "SELECT id," +
                " score," +
                " outs," +
                " is_top," +
                " inning," +
                " first_base," +
                " second_base," +
                " third_base," +
                " home_base" +
                " FROM half_inning" +
                " WHERE game = :gameId";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("gameId", gameId),
                (rs, rowNum) -> new HalfInning.Builder(rs.getInt("id"))
                        .score(rs.getInt("score"))
                        .outCount(rs.getInt("outs"))
                        .isTop(rs.getBoolean("is_top"))
                        .inning(rs.getInt("inning"))
                        .firstBase(rs.getBoolean("first_base"))
                        .secondBase(rs.getBoolean("second_base"))
                        .thirdBase(rs.getBoolean("third_base"))
                        .homeBase(rs.getBoolean("home_base"))
                        .build());
    }

    // GameDAO에서도 중복되는 메소드.
    // 세션에 isHome 저장하게 되면 사라져도 될 듯
    private boolean checkHome(Integer gameId, Integer teamId) {
        String sql = "SELECT is_home FROM team_game WHERE game = :game_id AND team = :team_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("game_id", gameId).addValue("team_id", teamId);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Boolean.class);
    }
}
