package hello;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Entity;

@Entity
@Table( name = "HN_USER", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User{
	
	
	private String username;
	private String password;
	private String email;
	private String age;
	private String groupId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public User(String username, String password, String email, String age, String groupId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.groupId = groupId;
	}
	public User() {
		super();
	}
	
	
	
	
}
