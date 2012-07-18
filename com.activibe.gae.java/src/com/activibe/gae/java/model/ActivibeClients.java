package com.activibe.gae.java.model;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ActivibeClients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key client_activibe_id;

	@Persistent
	String user_firstame;
	String user_lastame;
	String username;
	String email;
	String date_joined;

	@Persistent
	Set<Key> updates ;

	@Persistent
	Set<Key> doctors;
	String curr_doctor_id;
	String location;
	String password;
	String doctorID_Requested_Information;

	public ActivibeClients(String username2, String password2, String email2) {
		// TODO Auto-generated constructor stub
		username=username2;
		password=password2;
		email=email2;
		updates = new HashSet<Key>();
		doctors = new HashSet<Key>();
	}

	public Key getClient_activibe_id() {
		return client_activibe_id;
	}

	public void setClient_activibe_id(Key client_activibe_id) {
		this.client_activibe_id = client_activibe_id;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_firstame() {
		return user_firstame;
	}

	public void setUser_firstame(String user_firstame) {
		this.user_firstame = user_firstame;
	}

	public String getUser_lastame() {
		return user_lastame;
	}

	public void setUser_lastame(String user_lastame) {
		this.user_lastame = user_lastame;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate_joined() {
		return date_joined;
	}

	public void setDate_joined(String date_joined) {
		this.date_joined = date_joined;
	}

	public Set<Key> getUpdates() {
		return updates;
	}

	public void setUpdates(Set<Key> updates) {
		this.updates = updates;
	}
	
	public void addUpdates(Key update) {
		if(this.updates==null){
			this.updates = new HashSet<Key>();
			this.updates.add(update);
			return;
		}
		this.updates.add(update);
	}
	
	public Set<Key> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Key> doctors) {
		this.doctors = doctors;
	}
	
	public void addDoctors(Key doctor) {
		if(this.doctors==null){
			this.doctors = new HashSet<Key>();
		}
		this.doctors.add(doctor);
	}

	public String getCurr_doctor_id() {
		return curr_doctor_id;
	}

	public void setCurr_doctor_id(String curr_doctor_id) {
		this.curr_doctor_id = curr_doctor_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDoctorID_Requested_Information() {
		return doctorID_Requested_Information;
	}

	public void setDoctorID_Requested_Information(
			String doctorID_Requested_Information) {
		this.doctorID_Requested_Information = doctorID_Requested_Information;
	}

}
