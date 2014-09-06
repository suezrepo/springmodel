package com.ihm.admin.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ihm.admin.dao.PersonDAO;
import com.ihm.admin.model.Person;

public class SpringHibernateMain {

	public static void main(String[] args) {

		//Spring with Hibernate 3
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		//Spring with Hibernate 4
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");

		PersonDAO personDAO = context.getBean(PersonDAO.class);
		
		Person person = new Person();
		person.setName("Susee"); person.setCountry("India");
		
		personDAO.addPerson(person);
		
		System.out.println("Person::"+person);
		
		List<Person> list = personDAO.listPersons();
		
		for(Person p : list){
			System.out.println("Person List::"+p);
		}
		
		context.close();
		
	}

}
