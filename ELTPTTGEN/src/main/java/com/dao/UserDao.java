package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bean.User;


//@Component(value="udao")
@Repository("udao")
public class UserDao
{
	@Autowired
	private SessionFactory sf;  
	
	public List<User> getAllUsers()
	{
		Session s=sf.openSession();
		Query query= s.createQuery("from User");
		List<User> usr_lst=query.list();
		//Query query=s.createQuery("from ")
		s.close();
		return usr_lst;
		
	}
	
	public void addUser(User u){
		Session s=sf.openSession();
		Transaction tx= s.beginTransaction();
		s.save(u);
		tx.commit();
		s.close();
	}
	
	public void deleteUser(User u){
		Session s=sf.openSession();
		Transaction tx= s.beginTransaction();
		s.load(User.class,u.getUsername());
		s.delete(u);
		tx.commit();
		s.close();
	}

}
