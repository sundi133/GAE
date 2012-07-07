package com.activibe.gae.java.dao;

import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.activibe.gae.java.model.ActivibeStatus;
import com.activibe.gae.java.model.Cliques;
import com.activibe.gae.java.model.JobObjects;
import com.activibe.gae.java.model.Todo;

public enum Dao {
	INSTANCE;

	public List<Todo> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Todo m");
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void add(String userId, String summery, String description,
			String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Todo todo = new Todo(userId, summery, description, url);
			em.persist(todo);
			em.close();
		}
	}

	public List<Todo> getTodos(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Todo t where t.author = :userId");
		q.setParameter("userId", userId);
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Todo todo = em.find(Todo.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

	public void add(String key, String url) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from JobObjects t where t.key = :key");
		q.setParameter("key", key);
		List<JobObjects> data = q.getResultList();
		if (data.size() >= 1) {
			JobObjects o = data.get(0);
			String val = o.getUrl() + "," + url;
			o.setUrl(val);
			em.persist(o);
			em.close();

		} else {
			synchronized (this) {
				em = EMFService.get().createEntityManager();
				JobObjects o = new JobObjects(key, url);
				em.persist(o);
				em.close();
			}
		}

	}

	public static HashMap<String, String> getJobsMap() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from JobObjects t");
		HashMap<String, String> hm = new HashMap<String, String>();

		List<JobObjects> jobs = q.getResultList();

		for (int i = 0; i < jobs.size(); i++) {
			String[] val = jobs.get(i).toString().split(",");
			for (int j = 0; j < val.length; j++) {
				hm.put(jobs.get(i).getKey(), val[j]);
			}
		}
		return hm;
	}

	public void add(String title, String desc, String category,
			String location, String userid, String username) {

		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Cliques c = new Cliques(title, desc, category, location, username,
					userid);
			em.persist(c);
			em.close();
		}

	}

	public static List<Cliques> getCliques() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Cliques m");
		List<Cliques> clqs = q.getResultList();
		return clqs;
	}

	public void addactivibe(String activibe, String userid, String pressure,
			String blood, String time) {
		// TODO Auto-generated method stub
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			ActivibeStatus c = new ActivibeStatus(userid, pressure, blood, time);
			em.persist(c);
			em.close();
		}

	}

	public String[] getActivibePressure(String userid) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em
				.createQuery("select t from ActivibeStatus t where t.userid = :userid");
		q.setParameter("userid", userid);

		List<ActivibeStatus> resp = q.getResultList();
		String pressure = "";
		for (int i = 0; i < resp.size(); i++) {
			if (i == resp.size() - 1) {
				pressure += resp.get(i).getPressure();
			} else {
				pressure += resp.get(i).getPressure() + ",";
			}

		}
		return pressure.split(",");
	}

	public int addClients() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		// DatastoreService datastore =
		// DatastoreServiceFactory.getDatastoreService()
		// Transaction txn = datastore.beginTransaction();

		return 1;
	}
}