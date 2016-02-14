package com.dao;

import org.hibernate.cfg.Configuration;

public class SessionFactory {
	static org.hibernate.SessionFactory sf2;
 static {
		Configuration cfg=new Configuration().configure();
		System.out.println(cfg);
		org.hibernate.SessionFactory sf=cfg.buildSessionFactory();
		System.out.println(sf);
		sf.close();
		Configuration cfg2=new Configuration().configure("/com/hibernate2.cfg.xml");
		/*new SchemaExport(cfg2).drop(false, false);*/
		System.out.println(cfg2);
	    sf2=cfg2.buildSessionFactory();
		System.out.println(sf2);
	}
	public org.hibernate.Session openSession(){
		org.hibernate.Session s=sf2.openSession();
		return s;
	}
	public void close(){
		sf2.close();
	}

}
