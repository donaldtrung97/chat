package hello;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public UserDAOImpl(DataSource dataSource) {
		
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String insert(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("INSERT INTO HN_USER(username, password, email, age, groupId) VALUES (?,?,?,?,?)",
				user.getUsername(), user.getPassword(), user.getEmail(), user.getAge(), user.getGroupId());
		// System.out.println("Created Record name = " + student.getName());
		return " Created Record name = " + user.getUsername();
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> list() {
		return jdbcTemplate.query("SELECT * FROM HN_USER", new UserRowmapper());
	}

	private class UserRowmapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			User user = new User();
			user.setUsername(user.getUsername());
			user.setPassword(user.getPassword());
			user.setEmail(user.getEmail());
			user.setAge(user.getAge());
			user.setGroupId(user.getGroupId());
			return user;
		}
	}

	@Override
	public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostConstruct
	public void createTableIfNotExist() {
		// TODO Auto-generated method stub
		try {
			DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
			ResultSet rs = dbmd.getTables(null, null, "HN_USER", null);

			if (rs.next()) {
				System.out.println("table " + rs.getString("TABLE_NAME") + " da exits!");	
				return;
			}
			jdbcTemplate.execute("CREATE TABLE HN_USER("
					+ "  username VARCHAR(1000)," + " password VARCHAR(1000),  " + " email VARCHAR(1000), "
					+ " age VARCHAR(1000), " + " groupId VARCHAR(1000) )");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
