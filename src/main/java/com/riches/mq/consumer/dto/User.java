package com.riches.mq.consumer.dto;

import java.io.Serializable;

public class User implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8357495049135698133L;
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
