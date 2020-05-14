package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.dto.GameDTO;
import com.codesquad.team3.baseball.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MatchingDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MatchingDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TeamDTO> selectAllTeams() {
        String sql = "SELECT name, url, color FROM team";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TeamDTO.Builder builder = new TeamDTO.Builder(rs.getString("name"))
                    .url(rs.getString("url"))
                    .color(rs.getString("color"));
            return new TeamDTO(builder);
        });
    }

    public List<GameDTO> selectAllTeamGames() {
        String sql = "SELECT h.game AS game_id," +
                " h.id AS home_id, h.name AS home_name, h.thumbnail_url AS home_thumbnail_url," +
                " a.id AS away_id, a.name AS away_name, a.thumbnail_url AS away_thumbnail_url" +
                " FROM" +
                " (SELECT tg.game AS game, t.id, t.name, t.thumbnail_url" +
                    " FROM team t, team_game tg" +
                    " WHERE t.id = tg.team AND tg.is_home = true) AS h" +
                " LEFT OUTER JOIN" +
                " (SELECT tg.game AS game, t.id, t.name, t.thumbnail_url" +
                    " FROM team t, team_game tg" +
                    " WHERE t.id = tg.team AND tg.is_home = false) AS a" +
                " ON h.game = a.game," +
                " game g" +
                " WHERE g.id = h.game" +
                " AND g.is_over = false";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TeamDTO.Builder home = new TeamDTO.Builder(rs.getString("home_name"))
                    .id(rs.getInt("home_id"))
                    .thumbnailUrl(rs.getString("home_thumbnail_url"));
            TeamDTO.Builder away = new TeamDTO.Builder(rs.getString("away_name"))
                    .id(rs.getInt("away_id"))
                    .thumbnailUrl(rs.getString("away_thumbnail_url"));
            return new GameDTO(rs.getInt("game_id"), home.build(), away.build());
        });
    }

    public Map<String, Object> getMatchDataWithGameIdAndTeamId(Integer gameId, Integer teamId) {
        Map<String,Object> matchData = new HashMap<>();

        String sql = "SELECT user, is_home FROM team_game WHERE game = ? AND team = ?";
        try{
            matchData = jdbcTemplate.queryForMap(sql, new Object[] {gameId, teamId});
        } catch (EmptyResultDataAccessException e) {
            return matchData;
        }
        return matchData;
    }

    public void updateUserIdAtTeamGame(Integer gameId, Integer teamId, Integer userId) {
        String sql = "UPDATE team_game SET user = ? WHERE game = ? AND team = ?";
        jdbcTemplate.update(sql, new Object[]{userId, gameId, teamId});
    }

    public int countSeletedGameWithGameId(Integer gameId) {
        String sql = "SELECT COUNT(user) FROM team_game WHERE game = ? AND user is not null";
        return jdbcTemplate.queryForObject(sql, new Object[]{gameId}, Integer.class);
    }

    public void updateTeamIdAtUser(Integer userId, Integer teamId) {
        String sql = "UPDATE user SET team = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{teamId, userId});
    }
}
