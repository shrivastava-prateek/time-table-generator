package com.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bean.Blocked_Date;
import com.bean.Subject;
import com.bean.Trainer;
import com.service.CustomResourceLoader;


//@Component(value="tdao")
@Repository("tdao")
public class TrainerDao
{
	@Autowired
	private CustomResourceLoader intResourceLoader,leavesResourceLoader;
	@Autowired
	private SubjectDao subdao;
	//private static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private static List<Trainer> trainers=new ArrayList<Trainer>();
	//private static List<ExtTrainer> Exttrainers=new ArrayList<ExtTrainer>();
	private static boolean flag=false;
	private static boolean flag1=false;
	//private static boolean flag2=false;
	public List<Trainer> GetInternalTrainers(){
		if(flag==false){
			flag=true;
			String str="";
			Scanner sc=null;
//System.out.println("---------inside get internal trainers---------incomplete----");
			try {
				//CustomResourceLoader crl=(CustomResourceLoader)context.getBean("intResourceLoader");
				Resource r=intResourceLoader.getResource();
				File file=r.getFile();	
				sc=new Scanner(file);
				while (sc.hasNext())
				{
					str=sc.nextLine();
					str = str.trim();
					String[] strarr = str.split(",");

					//System.out.println(s.length);
					Trainer t = new Trainer();
					t.setTrainer_id(Integer.parseInt(strarr[0]));
					t.setTrainer_name(strarr[1]);
					t.setCity(strarr[2]);
					t.setEmail(strarr[3]);
					//only trainer data, no subjects, no blocked dates

					List<Subject> subjects = new ArrayList<Subject>();
					List<Subject> listsubject=subdao.getAllSubjects();
					//System.out.println("-----calling get all subjects again from get internal trainer---------" +listsubject);
					for (int i = 4; i <strarr.length; i++) 
					{	
						for(Subject s:listsubject)
						{
							if(s.getSubject_name().equalsIgnoreCase(strarr[i]))
							{
								subjects.add(s);
							}
						}
					}
					t.setSubjects(subjects);
					//trainer data with subjects, subject does not have trainers, no block dates
					trainers.add(t);

				}
				//System.out.println("-------assigned subjects to the trainers---------");
				addBlockedDates(trainers);
				////trainer data with subjects and blocked dates- COMPLETE, subject does not have trainers, 
				//System.out.println("---------blocked dates assigned to the trainer-----complete trainer -----------"+trainers);
				sc.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return trainers;

	}

	private void addBlockedDates(List<Trainer> trainers)
	{
		if(flag1==false){
			flag1=true;
			String str="";
			Scanner sc=null;
			try{
//System.out.println("-------inside adding blocked dates--------");
				//CustomResourceLoader crl=(CustomResourceLoader)context.getBean("leavesResourceLoader");
				Resource r=leavesResourceLoader.getResource();
				File file=r.getFile();
				sc=new Scanner(file);

				while (sc.hasNext())
				{
					str=sc.nextLine();
					String arr[]=str.split("-");
					for(Trainer trainer : trainers)
					{
						if(trainer.getTrainer_id()==Integer.parseInt(arr[0]))
						{
							String blockeddatesstraarr[]=arr[1].split(",");
							for(String blockeddatestr: blockeddatesstraarr)
							{
								String datesarr[]=blockeddatestr.split(":");
								SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
								Date sd =  sf.parse( datesarr[0]);
								Date ed =  sf.parse( datesarr[1]);

								Blocked_Date blocked_Date= new Blocked_Date(sd, ed);
								trainer.getBlocked_Dates().add(blocked_Date);
							}
						}
					}
				}
			} catch (Exception e) 
			{

			}
		}


	}

	/*public List<ExtTrainer> GetExternalTrainers(){
		if(flag2==false){
			flag2=true;
			String str="";
			Scanner sc=null;

			try{

				CustomResourceLoader crl=(CustomResourceLoader)context.getBean("extResourceLoader");
				Resource r=crl.getResource();
				File file=r.getFile();
				sc=new Scanner(file);

				while (sc.hasNext())
				{
					str=sc.nextLine();
					String[] strarr = str.split(",");

					//System.out.println(s.length);
					ExtTrainer t=new ExtTrainer();
					t.setTrainer_id(Integer.parseInt(strarr[0]));
					t.setTrainer_name(strarr[1]);
					t.setCity(strarr[2]);
					t.setEmail(strarr[3]);
					Subject subject=new Subject();
					subject.setSubject_name(strarr[4]);
					t.setSubject(subject);
					Exttrainers.add(t);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Exttrainers;
	}
*/


}


