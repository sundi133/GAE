package com.activibe.gae.java.model;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ActivibeUpdates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key status_id;
	@Persistent
	String update_time;
	@Persistent
	String client;
	@Persistent
	String doctors;

	String location;
	String energy_level;
	String mood_level;

	public Key getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Key status_id) {
		this.status_id = status_id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDoctors() {
		return doctors;
	}

	public void setDoctors(String doctors) {
		this.doctors = doctors;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEnergy_level() {
		return energy_level;
	}

	public void setEnergy_level(String energy_level) {
		this.energy_level = energy_level;
	}

	public String getMood_level() {
		return mood_level;
	}

	public void setMood_level(String mood_level) {
		this.mood_level = mood_level;
	}

}
