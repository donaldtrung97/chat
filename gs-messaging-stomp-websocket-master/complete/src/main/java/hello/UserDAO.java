package hello;

import java.util.List;

public interface UserDAO {
	
	public String insert(User user);
	
	public void delete(String name);
	
	public List<User> list();
	
	public User get(String username);
	
	public User update(User user);
	
	public void createTableIfNotExist();
}
