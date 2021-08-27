package com.aci.payon.rest.aprosewebservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();

	static {
		users.add(new User("1", "aprose" , new Date()));
		users.add(new User("2", "zulfi" , new Date()));
		users.add(new User("3", "zuhair" , new Date()));
		users.add(new User("4", "zunair" , new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		user.setId(String.valueOf(users.size()+1));
		users.add(user);
		return user;
	}
	
	public User find(String id) {
		for(User user:users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(String id) {
		
		Iterator< User> userItr = users.iterator();
		
		while (userItr.hasNext()) {
			User usr = userItr.next();
			if(usr.getId().equals(id)) {
				userItr.remove();
				return usr;
			}
			
		}
		return null;
	}
}
