package com.mycompany.app.common.model;

public class ModelA extends Model{

	public ModelA() {
		this("", 0);
	}

	public ModelA(String name, int number) {
		super(name, number);
	}


	@Override
	public String toString() {
		return "ModelA [name=" + getName() + ", number=" + getNumber() + "]";
	}
}
