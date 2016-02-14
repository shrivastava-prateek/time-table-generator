package com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.dao.UserDao;


//@Component(value="checkLogin")
@Service("checkLogin")
public class CheckLogin 
{
	@Autowired
	private UserDao udao;
	
	public User checkLogin (String un,String pw) 
	{
		
		List<User> users = udao.getAllUsers();
        User var=null;
		for(User u:users){
			if(un.equalsIgnoreCase(u.getUsername())&&pw.equalsIgnoreCase(u.getPassword()))
			{
				var=u;
				
			}
		  }
		
			return var;
	}
	
	
	public void create_user (User u) 
	{
		
		udao.addUser(u);
		
	}


}
