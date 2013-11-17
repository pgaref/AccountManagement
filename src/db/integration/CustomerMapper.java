package db.integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class CustomerMapper implements RowMapper<Customer> {

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Customer tmp = new Customer();
		tmp.setID(rs.getInt("ID"));
		tmp.setName(rs.getString("name"));
		tmp.setRegDate(rs.getTimestamp("registration_date"));
		tmp.setExpDate(rs.getTimestamp("expiration_date"));
		tmp.setDomain(rs.getString("domain"));
		tmp.setEmail(rs.getString("email"));
		tmp.setMobile(rs.getString("mobile"));
		tmp.setCharge(rs.getInt("charge"));
		tmp.setComments(rs.getString("comments"));
		tmp.setLastUpdate(rs.getTimestamp("last_update"));
		return tmp;
	}
}
