package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bean.Session;
//@Component(value="sdao")
@Repository("sdao")
public class SessionDao
{
@Autowired
	private SessionFactory sf;  
	
	public List<Session> getAllSessions()
	{
		org.hibernate.Session s=sf.openSession();
		Query query= s.createQuery("from Session");
		List<Session> s_lst=query.list();
		//Query query=s.createQuery("from ")
		s.close();
		return s_lst;

	}
	
	public List<Session> getSessionsByDate(Date date)
	{
		org.hibernate.Session s=sf.openSession();
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		String d= sdf.format(date);
		try {
			date = sdf.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Query query= s.createQuery("from Session S where S.startDate <=:date and S.endDate >=:date");
		query.setParameter("date", date);
		List<Session> s_lst=query.list();
		s.close();
		//Query query=s.createQuery("from ")
		return s_lst;

	}

}
