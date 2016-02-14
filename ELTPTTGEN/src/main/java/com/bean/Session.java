package com.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int session_id;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn
	private Batch batch;
	private String subjectName;
	private String trainerName;
	private Date startDate;
	private Date endDate;
	private Session_type session_type;

	

	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getSession_id() {
		return session_id;
	}



	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}



	public Batch getBatch() {
		return batch;
	}



	public void setBatch(Batch batch) {
		this.batch = batch;
	}



	public String getSubjectName() {
		return subjectName;
	}



	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}



	public String getTrainererName() {
		return trainerName;
	}



	public void setTrainererName(String trainererName) {
		this.trainerName = trainererName;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public Session_type getSession_type() {
		return session_type;
	}



	public void setSession_type(Session_type session_type) {
		this.session_type = session_type;
	}



	public Session( String subjectName,
			String trainerName, Date startDate, Date endDate,
			Session_type session_type) {
		super();
		
		this.subjectName = subjectName;
		this.trainerName = trainerName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.session_type = session_type;
	}



	@Override
	public String toString() {
		return "Session [session_id=" + session_id + ", batch name=" + batch.getBatch_name()
				+ ", subjectName=" + subjectName + ", trainerName="
				+ trainerName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", session_type=" + session_type + "]";
	}

	

}
