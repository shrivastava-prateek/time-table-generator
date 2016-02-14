package com.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bean.Batch;
import com.bean.Session;
import com.bean.Trainer;
import com.dao.TrainerDao;

public class EmailService {
	
	    private JavaMailSender mailSend;
	    
	public JavaMailSender getMailSend() {
			return mailSend;
		}

		public void setMailSend(JavaMailSender mailSend) {
			this.mailSend = mailSend;
		}

	public void sendEmails(Batch batch){
		List<Trainer> trainers=new TrainerDao().GetInternalTrainers();
		List<Session> sessions=batch.getSessions();
		for (Session session : sessions) {
			for (Trainer trainer : trainers) {
				if(trainer.getTrainer_name().equalsIgnoreCase(session.getTrainererName())){
					//System.out.println(trainer.getTrainer_name()+" email : "+trainer.getEmail());
				    SimpleMailMessage mail=createMail(session,trainer,batch);
				   mailSend.send(mail);
				   break;
				}
			}
		}
	}
	
	public SimpleMailMessage createMail(Session session,Trainer trainer,Batch batch){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(trainer.getEmail());
		message.setSubject("Training Schedule");
		String text= "Hello "+trainer.getTrainer_name()+",\n"
		+"This mail is regarding the training schedule for Batch : "+batch.getBatch_name()+batch.getBatch_city()+"\n"
		+"Venue : "+batch.getBatch_venue()+" ."+"\n"
		+"You will be conducting a "+session.getSession_type()+" session type,\n"
		+"Subject : "+session.getSubjectName() +" .\n"
		+"From : "+sdf.format(session.getStartDate())+" To : "+sdf.format(session.getEndDate())+" .\n"
		+"Thank you\n";
		message.setText(text);
		System.out.println(trainer.getEmail());
		return message;
	}
	
}
