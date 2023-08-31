package com.foo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.List;

@Entity
public class MyEntity {

	@Id
	private String id;


	private String address;

	List<MyEmbeddedEntity> embeddedEntities;

	// constructor
	public MyEntity() {
	}

	public List<MyEmbeddedEntity> getEmbeddedEntities() {
		return embeddedEntities;
	}

	public void setEmbeddedEntities(List<MyEmbeddedEntity> embeddedEntities) {
		this.embeddedEntities = embeddedEntities;
	}

	public String getId() {
		return id;
	}

	public String setId(String id) {
		return this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
