package com.activibe.gae.java.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Text;

/**
 * Model class which will store the Todo Items
 * 
 * @author Sundi
 * 
 */

@Entity
public class JobObjects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String key;
	private Text url;

	public JobObjects(String key1, String url1) {
		this.key = key1;
		this.url = new Text(url1);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url.toString();
	}

	public void setUrl(String url) {
		this.url = new Text(url);
	}

}
