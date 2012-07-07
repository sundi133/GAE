package com.activibe.gae.java.model;

import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ActivibeDoctors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key doc_id;
	@Persistent
	String date_join;
	@Persistent
	String ssn;
	@Persistent
	String doctor_name;
	@Persistent
	String specialisation;
	String location;
	@Persistent
	Set<Key> clients;

	public Key getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Key doc_id) {
		this.doc_id = doc_id;
	}

	public String getDate_join() {
		return date_join;
	}

	public void setDate_join(String date_join) {
		this.date_join = date_join;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Key> getClients() {
		return clients;
	}

	public void setClients(Set<Key> clients) {
		this.clients = clients;
	}

}
