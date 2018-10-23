package hello;

import java.util.List;

public interface GroupDAO {
	
	public String insert(Group group);
	public List<Group> list();
	public void createTableIfNotExist();
}
