package com.foo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity
public class MyEmbeddedEntity
		extends MongoEntity<MyEmbeddedEntity> {

	private String name = "foo";
	public MyEmbeddedEntity() {
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		return this.name = name;
	}

}
