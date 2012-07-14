package com.activibe.gae.java.dao;


import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.activibe.gae.java.Opcodes;
import com.activibe.gae.java.model.ActivibeClients;


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

}
