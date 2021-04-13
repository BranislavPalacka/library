package eu.branislavpalacka.library.DB.mappper;

import eu.branislavpalacka.library.domain.Serie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerieRowMapper implements RowMapper<Serie> {

    @Override
    public Serie mapRow(ResultSet resultSet, int i) throws SQLException {
        Serie serie = new Serie();
        serie.setId(resultSet.getInt("id"));
        serie.setName(resultSet.getString("name"));

        return serie;
    }
}
