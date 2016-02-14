package com.main;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bean.Batch;
import com.bean.Batch_type;
import com.service.AddBatchAndCreateTTwithSubPriority;
import com.service.EmailService;
import com.dao.BatchDao;
import com.dao.SessionFactory;

public class RelationTest {

	public static void main(String[] args) {
		
		Batch b = new Batch("ELTP JAVA", Batch_type.DEVELOPMENT, "Nagpur", "Atharva", 18, new Date());
		/*Batch b1 = new Batch("ELTP JAV2A", Batch_type.DEVELOPMENT, "Nagpur", "Atharva", 18, new Date());
		Batch b2 = new Batch("ELTP JAV3A", Batch_type.DEVELOPMENT, "Nagpur", "Atharva", 18, new Date());
		Batch b3 = new Batch("ELTP JAV4A", Batch_type.DEVELOPMENT, "Nagpur", "Atharva", 18, new Date());
		Batch b4 = new Batch("ELTP JAV5A", Batch_type.DEVELOPMENT, "Nagpur", "Atharva", 18, new Date());
		Batch b5 = new Batch("ELTP JAV6A", Batch_type.DEVELOPMENT, "Nagpur", "Atharva", 18, new Date());*/
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
	    SessionFactory sf=(SessionFactory)context.getBean("mysessionFactory");
	    AddBatchAndCreateTTwithSubPriority tt=(AddBatchAndCreateTTwithSubPriority)context.getBean("tt");
		tt.createTT(b);
	    /*new AddBatchAndCreateTTwithSubPriority().createTT(b);
		new AddBatchAndCreateTTwithSubPriority().createTT(b1);
		new AddBatchAndCreateTTwithSubPriority().createTT(b2);
		new AddBatchAndCreateTTwithSubPriority().createTT(b3);
		new AddBatchAndCreateTTwithSubPriority().createTT(b4);
		new AddBatchAndCreateTTwithSubPriority().createTT(b5);*/
		
		org.hibernate.Session session;
		session= sf.openSession();
		Transaction tx;
		tx= session.beginTransaction();
		
		session.save(b);
		/*session.save(b1);
		session.save(b2);
		session.save(b3);
		session.save(b4);
		session.save(b5);*/
		tx.commit();
		//Query query = session.createQuery("delete Batch where batch_name = :batch_name1");
		//query.setParameter("batch_name1",b.getBatch_name());
		//int result = query.executeUpdate();
		/*List<Batch> batches=new BatchService().getbatchlist();
	    int id=0;
		for (Batch batch : batches) {
			if(batch.getBatch_name().equals(b.getBatch_name()))
			{
				id=batch.getBatch_id();
				System.out.println("&&&&&&&"+batch);
			}
		}
		tx= session.beginTransaction();
		
		Batch b1 = (Batch) session.load(Batch.class,id);
		System.out.println("*********************************"+b1);
		session.delete(b1);
		tx.commit();*/
		session.close();
		
		
////		EmailService email=(EmailService)context.getBean("email");
/*////		email.sendEmails(b);
		List<Batch> batches=new BatchService().getbatchlist();
		System.out.println(batches);
		System.out.println(batches.get(0).getBatch_type());*/
		BatchDao bdao=(BatchDao)context.getBean("bdao");
		System.out.println(bdao.getAllBatch());
		sf.close();
	}

}
