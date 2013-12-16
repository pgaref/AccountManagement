package db.integration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HistoryMapper implements RowMapper<History>{

	public History mapRow(ResultSet rs, int rowNum) throws SQLException {

		History tmp = new History();
		tmp.setId(rs.getInt("ID"));
		tmp.setName(rs.getString("name"));
		tmp.setDelDate(rs.getDate("off_date"));
		tmp.setDomain(rs.getString("domain"));
		tmp.setEmail(rs.getString("email"));
		tmp.setMobile(rs.getString("mobile"));
		tmp.setDelComments(rs.getString("deleting_comments"));
		return tmp;
	}
}
