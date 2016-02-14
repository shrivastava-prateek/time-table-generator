package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int batch_id;
	private String batch_name;
	private Batch_type batch_type;
	private String batch_city;
	private String batch_venue;
	private int no_of_cand;
	private Date start_date;
	@OneToMany(cascade={CascadeType.ALL,CascadeType.REMOVE},mappedBy="batch",fetch=FetchType.EAGER)
	private List<Session> sessions= new ArrayList<Session>();

	public Batch() {
		super();
	}

	public Batch(String batch_name, Batch_type batch_type, String batch_city,
			String batch_venue, int no_of_cand, Date start_date) {
		super();
		this.batch_name = batch_name;
		this.batch_type = batch_type;
		this.batch_city = batch_city;
		this.batch_venue = batch_venue;
		this.no_of_cand = no_of_cand;
		this.start_date = start_date;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public String getBatch_name() {
		return batch_name;
	}

	public void setBatch_name(String batch_name) {
		this.batch_name = batch_name;
	}

	public Batch_type getBatch_type() {
		return batch_type;
	}

	public void setBatch_type(Batch_type batch_type) {
		this.batch_type = batch_type;
	}

	public String getBatch_city() {
		return batch_city;
	}

	public void setBatch_city(String batch_city) {
		this.batch_city = batch_city;
	}

	public String getBatch_venue() {
		return batch_venue;
	}

	public void setBatch_venue(String batch_venue) {
		this.batch_venue = batch_venue;
	}

	public int getNo_of_cand() {
		return no_of_cand;
	}

	public void setNo_of_cand(int no_of_cand) {
		this.no_of_cand = no_of_cand;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "Batch [batch_id=" + batch_id + ", batch_name=" + batch_name
				+ ", batch_type=" + batch_type + ", batch_city=" + batch_city
				+ ", batch_venue=" + batch_venue + ", no_of_cand=" + no_of_cand
				+ ", start_date=" + start_date + ", No. of sessions=" + sessions.size() + "]";
	}
	
}
