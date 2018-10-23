package hello;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Entity;

@Entity
@Table(name = "HN_GROUP", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
public class Group {

		private String name; 
		private String id ;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		
		public Group(String name, String id) {
			super();
			this.name = name;
			this.id = id;
		}
		public Group() {
			super();
		} 
		
		
}
