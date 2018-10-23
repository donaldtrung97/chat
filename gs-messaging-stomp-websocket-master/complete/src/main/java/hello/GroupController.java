package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
		
		@Autowired
		GroupDAO groupDAO;
		@RequestMapping(value = "/groups")
		public List<Group> list() {
			return groupDAO.list();
		}
}
