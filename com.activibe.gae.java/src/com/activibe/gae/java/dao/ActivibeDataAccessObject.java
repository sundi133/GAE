package com.activibe.gae.java.dao;


import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.activibe.gae.java.ActivibeRequestHandler;
import com.activibe.gae.java.Opcodes;
import com.activibe.gae.java.Status;
import com.activibe.gae.java.model.ActivibeClients;
import com.activibe.gae.java.model.ActivibeUpdates;
import com.google.appengine.api.datastore.Key;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import sun.org.mozilla.javascript.internal.WrapFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
 

public enum ActivibeDataAccessObject {
	
	INSTANCE;
	// succes created, send 200 opcode for success.
	public int createActivibeUser(String username, String password,
			String email) {
		// TODO Auto-generated method stub
		if(checkUser(username,email))
			return Opcodes.USER_ALREADY_EXISTS;
		else{
			synchronized(this) {
				EntityManager em = EMFService.get().createEntityManager();
				ActivibeClients acl = new ActivibeClients(username,password,email);
				em.persist(acl);
				em.close();
				return Opcodes.SUCCESS;
			}
		}

	}

	private boolean checkUser(String username, String email) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		//select t from JobObjects t where t.key = :key
		Query q = em
		.createQuery("select t from ActivibeClients t where t.username = :username");
		q.setParameter("username", username);
		List<ActivibeClients> acl = q.getResultList();
		if(acl.size()==1){
			if(acl.get(0).getEmail().contentEquals(email)){
				return true;
			}

		}
		return false;
	}

	//todo, erroro code 1001 for user not registered, 200 for succesful
	public int verifiActivibeUser(String username, String password) {
		// TODO Auto-generated method stub

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
		.createQuery("select t from ActivibeClients t where t.username = :username");
		q.setParameter("username", username.trim());
		List<ActivibeClients> acl = q.getResultList();
		if(acl.size()==1){
			if(acl.get(0).getPassword().contentEquals(password)){
				return Opcodes.SUCCESS;
			}

		}
		return Opcodes.USER_DONOT_EXISTS;

	}

	public int verifiActivibeUserID(String username) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
		.createQuery("select t from ActivibeClients t where t.username = :username");
		q.setParameter("username", username.trim());
		List<ActivibeClients> acl = q.getResultList();
		if(acl.size()==1){
				return Opcodes.SUCCESS;
		
		}
		return Opcodes.USER_DONOT_EXISTS;

	}

	public int createActivibeUserStatus(String userid, String moodlevel,
			String energylevel, String time, String location, String locationType, String lat, String lon) {
		// TODO Auto-generated method stub
		EntityManager emq = EMFService.get().createEntityManager();
		Query q = emq
		.createQuery("select t from ActivibeClients t where t.username = :username");
		q.setParameter("username", userid.trim());
		List<ActivibeClients> acl = q.getResultList();
		if(acl.size()==1){
				addUpdate(userid,moodlevel,energylevel,time,location,locationType,lat,lon);
				return Opcodes.SUCCESS;
		}
		
		return Opcodes.USER_DONOT_EXISTS;

	}


	private void addUpdate(String userid, String moodlevel, String energylevel,
			String time, String location, String locationType, String lat, String lon) {
		// TODO Auto-generated method stub

		synchronized(this) {
		EntityManager em = EMFService.get().createEntityManager();
		ActivibeUpdates aclu = new ActivibeUpdates(userid, moodlevel,energylevel,time,location,locationType,lat,lon);
		em.persist(aclu);
		em.close();
		}
		
	}

	public String getActivibeUserStatus(String userid)  {
		// TODO Auto-generated method stub
		EntityManager emq = EMFService.get().createEntityManager();
		Query q = emq.createQuery("select t from ActivibeUpdates t where t.client = :username order by date asc");
		q.setParameter("username", userid);
		List<ActivibeUpdates> acl = q.getResultList();
	
		Gson gson = new Gson();
	
		List<Map<Integer, Status>> userstatus = new ArrayList<Map<Integer,Status>>();
		Map<Integer, Status> smap = new HashMap<Integer, Status>();
		for (int i = 0; i < acl.size(); i++) {
			Status status = new Status();
			status.setEnergy(acl.get(i).getEnergy_level());
			status.setFeel(acl.get(i).getMood_level());
			status.setTime(acl.get(i).getUpdate_time());
			status.setLocation(acl.get(i).getLocation());
			status.setLocationType(acl.get(i).getLocationType());
			status.setLat(acl.get(i).getLatitude());
			status.setLon(acl.get(i).getLongitude());
			smap.put(i+1, status);
		}
		userstatus.add(smap);
		return gson.toJson(userstatus);
	}

	public String verifiActivibeUserIDGetEmail(String username) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
		.createQuery("select t from ActivibeClients t where t.username = :username");
		q.setParameter("username", username.trim());
		List<ActivibeClients> acl = q.getResultList();
		if(acl.size()==1){
				return acl.get(0).getEmail();
		
		}
		return "None";

	}

	public void updateDataForVisualization(String userid,
			String emailtosharewith, String random) {
		// TODO Auto-generated method stub
		synchronized (this) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
		.createQuery("select t from ActivibeUpdates t where t.client = :username");
		q.setParameter("username", userid);
		List<ActivibeUpdates> acl = q.getResultList();
		
		if(acl.size()>0){
			
			for (int i = 0; i < acl.size(); i++) {
				//em.getTransaction().begin();
				acl.get(i).setVisualizationKey(random);
				acl.get(i).setDoctors(emailtosharewith);
				em.persist(acl.get(i));
				//em.getTransaction().commit();
			}
			em.close();
		}
		
		}
	}
	
	public List<ActivibeUpdates> getUpdatesForReport(String key) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
		.createQuery("select t from ActivibeUpdates t where t.visualizationKey = :key");
		q.setParameter("key", key);
		List<ActivibeUpdates> acl = q.getResultList();
		return acl;

	}


}
