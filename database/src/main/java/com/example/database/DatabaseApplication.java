package com.example.database;


import com.example.database.pojo.Person;
import com.example.database.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {

		SpringApplication.run(DatabaseApplication.class, args);
//		ConfigurableApplicationContext context= SpringApplication.run(DatabaseApplication.class, args);
//		PersonRepository personRepository =context.getBean(PersonRepository.class);
//		Person person=new Person();
//		person.setPersonName("Arun Chaudhary");
//		person.setPersonAge(20);
//
//		//insert one data
////		Person result=personRepository.save(person);
////		System.out.println(result);
//
//		Person person1=new Person();
//		person.setPersonName("Rahul Kumar");
//		person.setPersonAge(22);
//
//
//		ArrayList<Person> mylist=new ArrayList<>();
//		mylist.add(person);
//		mylist.add(person1);
//		//insert two data
////		Iterable<Person> res=personRepository.saveAll(mylist);
////		res.forEach(item->{
////					System.out.println(item);
////				}
////		);
//
//
//		//update a data
//		Optional<Person> data= personRepository.findById(3);
//		Person p=data.get();
//		System.out.println(p);
//		p.setPersonName("Hello World");
//		p.setPersonAge(50);
////		Person updateData=personRepository.save(p);
////		System.out.println(updateData);
//
//      //read data,get data
//		//findById(int id)-return Optional containing data
//		//findAll()-return iterable
////		Iterable<Person> alldata=personRepository.findAll();
////		alldata.forEach(one->{
////			System.out.println(one);
////		});
//
//
//		//Delete row
//		personRepository.deleteById(3);
//		System.out.println("Deleted");
	}

}
