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




public class GroupDAOImpl implements GroupDAO{
	
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public GroupDAOImpl(DataSource dataSource) {
		
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Group> list() {
		// TODO Auto-generated method stub
		//return jdbcTemplate.query("SELECT * FROM CHAR", new UserRowmapper());
		return jdbcTemplate.query("SELECT * FROM HN_GROUP", new GroupRowmapper());
	}
	
	private class GroupRowmapper implements RowMapper<Group> {
		@Override
		public Group mapRow(ResultSet rs, int i) throws SQLException {
			Group group = new Group();
			group.setName(group.getName());
			group.setId(group.getId());
			return group;
		}
	}

	@Override
	public String insert(Group group) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("INSERT INTO HN_GROUP(name, id) VALUES (?,?)",
				group.getName(), group.getId());
		// System.out.println("Created Record name = " + student.getName());
		return " Created Record name = " + group.getName();
	}

	@Override
	@PostConstruct
	public void createTableIfNotExist() {
		// TODO Auto-generated method stub
		try {
			DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
			ResultSet rs = dbmd.getTables(null, null, "HN_GROUP", null);

			if (rs.next()) {
				System.out.println("table " + rs.getString("TABLE_NAME") + " da exits!");	
				return;
			}
			jdbcTemplate.execute("CREATE TABLE HN_GROUP("
					+ "  taikhoan VARCHAR(1000)," + " matkhau VARCHAR(1000),  " + " email VARCHAR(1000), "
					+ " age VARCHAR(1000), " + " nhom VARCHAR(1000) )");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}
