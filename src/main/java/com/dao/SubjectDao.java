package com.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bean.Batch_type;
import com.bean.Subject;
import com.bean.Trainer;
import com.service.CustomResourceLoader;


//@Component(value="subdao")
@Repository("subdao")
public class SubjectDao {
	@Autowired
	private CustomResourceLoader subResourceLoader;
	@Autowired
	private TrainerDao tdao;
	private static boolean flag=false;
	private static boolean flag1=false;
	private static List <String> groups=new ArrayList<String>();
	private static List<Subject> subjects=new ArrayList<Subject>();
	public  List<String> getGroups() {
		return groups;
	}
	public List<Subject> getAllSubjects()
	{
		//System.out.println(flag);
		if(flag==false){
			//System.out.println("------------Reading File---------------");
			flag=true;
			Scanner sc=null;
			String str="";
			/*List<Subject> subjects=new ArrayList<Subject>();*/
			try {

				//ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				//CustomResourceLoader crl=(CustomResourceLoader)context.getBean("subResourceLoader");
				Resource r=subResourceLoader.getResource();
				File file=r.getFile();
				sc=new Scanner(file);
				while (sc.hasNext())
				{
					//System.out.println("------inside while getting all subjects----------");
					str=sc.nextLine();
					str = str.trim();
					String[] strarr = str.split(",");

					//System.out.println(s.length);
					Subject sub = new Subject();
					sub.setSubject_name(strarr[0]);
					sub.setSubject_group(strarr[1]);

					//	System.out.println(strarr[0]);
					String subupp=strarr[2].toUpperCase();
					sub.setBatch_type(Batch_type.valueOf(subupp));
					sub.setLevel_in_group(Integer.parseInt(strarr[3]));
					sub.setDuration_in_days(Integer.parseInt(strarr[4]));
					sub.setAssignment_duration(Integer.parseInt(strarr[5]));
					subjects.add(sub);
					if(groups.contains(strarr[1])){
						continue;
					}
					else {
						groups.add(strarr[1]);
					}
				}
				sc.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return subjects;
	}

	public List<Subject> getCompleteSubjects(){
		//System.out.println(flag1);
		if(flag1==false){
			flag1=true;
			List<Subject> subjects= getAllSubjects();
			//System.out.println("-------inside getcompletesubjects---------"+subjects);
			List<Trainer> trainers=tdao.GetInternalTrainers();
			//System.out.println(trainers);
			for (Trainer trainer : trainers) {
				List<Subject> tsubjects = trainer.getSubjects();
				for (Subject tsubject : tsubjects) {
					for (Subject subject : subjects) {
						if(tsubject.getSubject_name().equalsIgnoreCase(subject.getSubject_name()))
						{
							tsubject=subject;
							subject.getTrainers().add(trainer);
						}
					}
				}
			}
		}
		return subjects;
	}

}
