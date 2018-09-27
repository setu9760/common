package com.mycompany.app.common.model;

public class ModelB extends Model {
	
	public ModelB() {
		this("", 0);
	}

	public ModelB(String name, int number) {
		super(name, number);
	}


	@Override
	public String toString() {
		return "ModelB [name=" + getName() + ", number=" + getNumber() + "]";
	}
}
