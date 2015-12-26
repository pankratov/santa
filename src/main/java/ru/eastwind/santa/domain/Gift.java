package ru.eastwind.santa.domain;

public class Gift {
	private String name;
	private String label;
	
	public Gift(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLabel(String labelName) {
		this.label = labelName;
	}

	public String getLabel() {
		return this.label;
	}
}
