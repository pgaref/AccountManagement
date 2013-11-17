package db.integration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HistoryMapper implements RowMapper<History>{

	public History mapRow(ResultSet rs, int rowNum) throws SQLException {

		History tmp = new History();
		tmp.setID(rs.getInt("ID"));
		tmp.setDelDate(rs.getTimestamp("off_date"));
		tmp.setDomain(rs.getString("domain"));
		tmp.setEmail(rs.getString("email"));
		tmp.setComments(rs.getString("deleting_comments"));
		return tmp;
	}
}
