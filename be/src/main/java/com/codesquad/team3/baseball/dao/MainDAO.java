package com.codesquad.team3.baseball.dao;

import com.codesquad.team3.baseball.dto.GameDTO;
import com.codesquad.team3.baseball.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MainDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MainDAO(DataSource dataSource) {
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
}
