package com.springtest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springtest.model.User;

public class JdbcUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	JdbcUserDAO(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<User> list() {
		return jdbcTemplate.query("select * from users", new UserRowMapper());
	}

	public void insert(User user) {
		jdbcTemplate.update(
				"insert into users(id,name,emailaddress) values(?,?,?)",
				user.getId(), user.getName(), user.getEmailaddress());
	}

	public void update(User user) {
		jdbcTemplate.update(
				"update users set name = ? , emailaddress = ? where id = ? ",
				user.getName(), user.getEmailaddress(), user.getId());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from users where id = ? ", id);
	}

	public User findById(int id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?",
				new Object[] { id }, new UserRowMapper());
	}

	private static final class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getInt("id"), rs.getString("name"),
					rs.getString("emailaddress"));
		}
	}
}
