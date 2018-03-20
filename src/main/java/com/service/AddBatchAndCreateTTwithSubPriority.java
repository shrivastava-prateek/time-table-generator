package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bean.Batch;
import com.bean.Batch_type;
import com.bean.Blocked_Date;
import com.bean.Session;
import com.bean.Session_type;
import com.bean.Subject;
import com.bean.Trainer;
import com.dao.BatchDao;
import com.dao.Holidays;
import com.dao.SubjectDao;


//@Component(value="tt")
@Service("tt")
public class AddBatchAndCreateTTwithSubPriority  {
	//private static ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
	@Autowired
	private BatchDao bdao;
	@Autowired
	private SubjectDao subdao;
	@Autowired
	private Holidays holiday;
	public void addBatch(Batch b) {
		//BatchDao bdao = (BatchDao) context.getBean("bdao");
		createTT(b);
		bdao.addBatch(b);
		/*EmailService email=(EmailService)context.getBean("email");
		email.sendEmails(b);*/

	}

	public void createTT(Batch batch) 
	{
		Batch_type batch_type = batch.getBatch_type();
		//SubjectDao sdao=new SubjectDao();
		List<Subject> subjects = subdao.getCompleteSubjects();
		//System.out.println("----------inside create TT--------"+subjects);
		List<Subject> btypesubject = new ArrayList<Subject>(); // subjects
		// according to
		// batch type
		for (Subject subject : subjects) 
		{
			if (subject.getBatch_type().equals(batch_type))
			{
				btypesubject.add(subject);
				//System.out.println(subject);
			}
		}
		List<String> groups =new ArrayList<String>();
		List<String> groups1=subdao.getGroups();
		groups.addAll(groups1);
		//getting
		//list of groups
		/*for (String string : groups) 
		{
			System.out.println(string);
		}*/

		Random r = new Random();
		int index;
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(batch.getStart_date());
		List<Date> holidays=holiday.getHolidays();// list of holidays 
		// from where session has to be shifted

		Calendar endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());//start date and end date are same here
		int size = groups.size(); // no of groups(each group is having no. of Subjects)
		//that have to be assigned to a batch
		while (size > 0) 
		{
			//System.out.println("22222222222222222222222222222222222222222222222");
			//System.out.println(size);
			size = groups.size();
			index = r.nextInt(size);
			String group=groups.get(index);// taking a random group of subjects
			List<Subject> gsubjects=new ArrayList<Subject>();
			//System.out.println("----------------------------"	+ size + "  index->   "+index+"---------------------------");
			for (Subject subject : btypesubject)//getting all the subjects of a particular group 
			{
				if(group.equalsIgnoreCase(subject.getSubject_group()))
				{
					gsubjects.add(subject);
					//System.out.println("Group : "+subject.getSubject_group()+" Name : "+subject.getSubject_name());
				}
			}

			//sorting subjects in a particular group according to their priority
			Collections.sort(gsubjects, new Comparator<Subject>() {

				public int compare(Subject o1, Subject o2) {
					if(o1.getLevel_in_group()>o2.getLevel_in_group()){
						return 1;
					}
					else{
						if(o1.getLevel_in_group()<o2.getLevel_in_group()){
							return -1;
						}
					}
					return 0;
				}
			});
			// Sorted Subjects of groups obtained


			/*for (Subject subject : gsubjects) {
				System.out.println(subject);
			}*/

			for (Subject gsubject : gsubjects)// now assigning each subject to a batch according to logic written below
			{ 






				List<Trainer> subtrainers = gsubject.getTrainers();//getting all the associated trainers of this subject
				//System.out.println(subtrainers);
				SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");//to compare dates according to date, month and year
				// not according to milliseconds
				for (Date holiday : holidays) {//checking for holiday
					if(sdf.format(holiday).equals(sdf.format(startDate.getTime()))){
						startDate.add(Calendar.DATE, 1);// if the start date is a holiday then shift the start date by 1
						endDate.add(Calendar.DATE, 1);// same with the end date , initially both are equal
						//System.out.println("Start DAte Incremented");
					}
				}
				// now calculating end date according to the duration
				// of the subject
				endDate.add(Calendar.DATE, gsubject.getDuration_in_days()-1);


				for (Date holiday : holidays) 
				{
					// checking for holiday
					if(sdf.format(holiday).equals(sdf.format(endDate.getTime()))
							||(holiday.after(startDate.getTime()) && holiday.before(endDate.getTime())))
					{
						endDate.add(Calendar.DATE, 1);//same shifting of end date by 1
						//System.out.println("END DAte Incremented");
					}
				}
				boolean flag = false;// flag to check whether trainer is assigned to a subject or not 
				OUTER: for (Trainer trainer : subtrainers)
				{
					if(flag==true)
					{
						break;
					}
					//System.out.println("Trainer name : "+trainer.getTrainer_name()+" Subject name : "+gsubject.getSubject_name());
					List<Blocked_Date> blocked_dates = trainer.getBlocked_Dates();
					// list of blocked dates
//System.out.println("Trainer's name : "+trainer.getTrainer_name()+" Blocked dates : "+blocked_dates);
					for (Blocked_Date blocked_Date : blocked_dates)// checking the availability of the trainer
					{
						Date bsd = blocked_Date.getStart_date();
						Date bed = blocked_Date.getEnd_date();
						if ((bsd.after(startDate.getTime()) && bsd.before(endDate.getTime()))
								|| (bed.after(startDate.getTime()) && bed.before(endDate.getTime())) 
								|| (sdf.format(bsd).equals(sdf.format(startDate.getTime())))
								|| (sdf.format(bsd).equals(sdf.format(endDate.getTime())))
								|| (sdf.format(bed).equals(sdf.format(endDate.getTime())))
								|| (sdf.format(bed).equals(sdf.format(startDate.getTime()))))
						{
							//System.out.println("Trainer's name : "+trainer.getTrainer_name()+" is blocked");
							continue OUTER;
						} else 
						{
							continue;
						}
					}

					// If a trainer object reaches here i.e no blocked dates

					flag = true;
					//System.out.println(trainer.getTrainer_name());
					// add blocked dates to trainer
					trainer.getBlocked_Dates().add(new Blocked_Date(startDate.getTime(), endDate.getTime()));

					// add subject to the session

					Session_type session_type;
					if (trainer.getCity().equalsIgnoreCase(batch.getBatch_city())) {
						session_type = Session_type.PHYSICAL;
					} else {
						session_type = Session_type.WEBEX;

					}
					Session session = new Session(gsubject.getSubject_name(),
							trainer.getTrainer_name(), startDate.getTime(),
							endDate.getTime(), session_type);
					session.setBatch(batch);
					// add this session to list of sessions in batch
					batch.getSessions().add(session);

					// preparing for next sub

					//System.out.println("44444444444444444444444444444444444444444444");
					endDate.add(Calendar.DATE, 1);
					startDate.setTime(endDate.getTime());

				}// INTERNAL TRAINER ALLOTED


				if (flag == false && size > 0)//if internal trainer is not available
				{
					//add external trainer for gsubject
					// DUMMY
					/*Session_type session_type;
					if ("Nagpur".equalsIgnoreCase(batch.getBatch_city())) {
						session_type = Session_type.PHYSICAL;
					} else {
						session_type = Session_type.WEBEX;

					}*/
					Session session = new Session(gsubject.getSubject_name(),
							"External", startDate.getTime(),
							endDate.getTime(), Session_type.PHYSICAL);
					session.setBatch(batch);
					// add this session to list of sessions in batch
					batch.getSessions().add(session);

					// preparing for next sub

					//System.out.println("44444444444444444444444444444444444444444444");
					endDate.add(Calendar.DATE, 1);
					startDate.setTime(endDate.getTime());
				}

				//Assignments as a self learning day session
				if(gsubject.getAssignment_duration()>0){// if there is an assignment related to the subject
					for (Date holiday : holidays) {//again checking the start date of an assignement for the holiday
						if(sdf.format(holiday).equals(sdf.format(startDate.getTime()))){
							startDate.add(Calendar.DATE, 1);
							endDate.add(Calendar.DATE, 1);
							//System.out.println("Start DAte Incremented");

						}
					}
					endDate.add(Calendar.DATE, gsubject.getAssignment_duration()-1);


					for (Date holiday : holidays) 
					{
						// checking for holiday
						if(sdf.format(holiday).equals(sdf.format(endDate.getTime()))
								||(holiday.after(startDate.getTime()) && holiday.before(endDate.getTime())))
						{
							endDate.add(Calendar.DATE, 1);//same shifting of end date by 1
							//System.out.println("END DAte Incremented");
						}
					}


					/*for (Date holiday : holidays) {// checking the end date of an assignment for the holiday
						if(sdf.format(holiday).equals(sdf.format(endDate.getTime()))){
							endDate.add(Calendar.DATE, 1);
							//System.out.println("END DAte Incremented");
						}
					}*/
					Session s=new Session(gsubject.getSubject_name()+" Assignment", "Self", startDate.getTime(), endDate.getTime(),Session_type.PHYSICAL);
					s.setBatch(batch);
					batch.getSessions().add(s);
					//preparing for next subject
					endDate.add(Calendar.DATE, 1);
					startDate.setTime(endDate.getTime());
				}





			}

			//System.out.println("555555555555555555555555555555555555555555555555555555");
			groups.remove(index);
			size = groups.size();
		}
	}


}


