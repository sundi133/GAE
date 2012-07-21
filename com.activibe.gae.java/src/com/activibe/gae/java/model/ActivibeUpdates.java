package com.activibe.gae.java.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@Entity
public class ActivibeUpdates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key status_id;
	@Persistent
	String update_time;
	@Persistent
	private Date date;

	@Persistent
	String client;
	
	@Persistent
	String doctors;

	String update_location;
	String locationType;
	String energy_level;
	String mood_level;

	@Persistent
	String visualizationKey;

	String latitude;
	String longitude;
	
	public ActivibeUpdates(String userid, String moodlevel, String energylevel,
			String time, String location, String locationType1, String lat, String lon) {
		// TODO Auto-generated constructor stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy - h:m");
		client=userid;
		mood_level=moodlevel;
		energy_level=energylevel;
		update_time=time;
		update_location=location;
		locationType=locationType1;
		latitude=lat;
		longitude=lon;
		try {
			date = dateFormat.parse(update_time);
		} catch (ParseException e) {
			
		} 
		
	}

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
		return update_location.toString();
	}

	public void setLocation(String location) {
		this.update_location = location;
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


	public String getVisualizationKey() {
		return visualizationKey;
	}

	public void setVisualizationKey(String visualizationKey) {
		this.visualizationKey = visualizationKey;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
