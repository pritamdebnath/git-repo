package com.springtest.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springtest.dao.UserDAO;
import com.springtest.model.User;

public class AppMain {

	public static void main(String[] args) {

		final ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-context.xml");
		final UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
		
		System.out.println("All data before anything...");
		printAllUsers(userDAO);
		
		System.out.println("After inserting my data...");
		User user=new User(4,"pritam","pritamdebnath9@gmail.com");
		userDAO.insert(user);
		printAllUsers(userDAO);
		
		System.out.println("After updataing joel's mail...");
		User userTobeUpdated=userDAO.findById(3);
		userTobeUpdated.setEmailaddress("updated@yahoo.com");
		userDAO.update(userTobeUpdated);
		printAllUsers(userDAO);
		
		System.out.println("After deleting mkyong...");
		userDAO.delete(1);
		printAllUsers(userDAO);
		
	}

	private static void printAllUsers(final UserDAO userDAO) {
		final List<User> userList = userDAO.list();
		for (User user : userList) {
			System.out.println("----" + user.getId() + "----" + user.getName()
					+ "----" + user.getEmailaddress());
		}
	}

}
