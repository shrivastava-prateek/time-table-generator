package com.dao;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.service.CustomResourceLoader;


//@Component(value="holiday")
@Repository("holiday")
public class Holidays {
	@Autowired
	private CustomResourceLoader holidaysResourceLoader;
	private static List<Date> holidays=new ArrayList<Date>();
	private static boolean flag=false;
	public List<Date> getHolidays(){
		if(flag==false){
			flag=true;
			Scanner sc=null;
			String str="";
			int mon=1;
			try{
				Resource r=holidaysResourceLoader.getResource();
				File file=r.getFile();
				sc=new Scanner(file);

				while (sc.hasNext())
				{
					str=sc.nextLine();
					String datesinmon[]=str.split(",");
					for(String s: datesinmon)
					{
						if(s!=null && !s.isEmpty())
						{
							String x=s+"/"+(mon)+"/"+2016;
							SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
							Date date = null;
							try 
							{
								date = sf.parse(x);
							} catch (ParseException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							holidays.add(date);
						}

					}
					mon++;
				}

			} catch (Exception e) 
			{
				System.out.println("EXCEPTION OCCURED IN TRAINER DAO");
			}
		}
		return holidays;
	}
}
