package com.bean;

import java.util.ArrayList;
import java.util.List;


public class Subject {
	
	private int subject_id;
	private String subject_name;
	private String subject_group;
	private Batch_type batch_type;
	private int level_in_group;
	private int duration_in_days;
	private int assignment_duration;
	private List<Trainer> trainers=new ArrayList<Trainer>();

	public Subject() {
		super();
	}

	public Subject( String subject_name, String subject_group,
			Batch_type batch_type, int level_in_group,
			int duration_in_days, int assignment_duration) {
		super();
		this.subject_name = subject_name;
		this.subject_group = subject_group;
		this.batch_type = batch_type;
		this.level_in_group = level_in_group;
		this.duration_in_days = duration_in_days;
		this.assignment_duration = assignment_duration;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getSubject_group() {
		return subject_group;
	}

	public void setSubject_group(String subject_group) {
		this.subject_group = subject_group;
	}

	public Batch_type getBatch_type() {
		return batch_type;
	}

	public void setBatch_type(Batch_type batch_type) {
		this.batch_type = batch_type;
	}

	public int getLevel_in_group() {
		return level_in_group;
	}

	public void setLevel_in_group(int level_in_group) {
		this.level_in_group = level_in_group;
	}

	public int getDuration_in_days() {
		return duration_in_days;
	}

	public void setDuration_in_days(int duration_in_days) {
		this.duration_in_days = duration_in_days;
	}

	public int getAssignment_duration() {
		return assignment_duration;
	}

	public void setAssignment_duration(int assignment_duration) {
		this.assignment_duration = assignment_duration;
	}

	public List<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainer(List<Trainer> trainer) {
		this.trainers = trainer;
	}

	@Override
	public String toString() {
		return "Subject [subject_id=" + subject_id + ", subject_name="
				+ subject_name + ", subject_group=" + subject_group
				+ ", batch_type=" + batch_type + ", level_in_group="
				+ level_in_group + ", duration_in_days=" + duration_in_days
				+ ", assignment_duration=" + assignment_duration + ", trainer="
				+ trainers.size() + "]";
	}

}
