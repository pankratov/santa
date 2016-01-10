package ru.eastwind.santa.domain;

public class Gift {
	private String name;
	private String label;
	private boolean labeled = false;
	
	public Gift(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLabel(String labelName) {
		this.label = labelName;
		this.labeled = true;
	}

	public String getLabel() {
		return this.label;
	}
	
	public void unlabel() {
		this.label = "";
		this.labeled = false;		
	}
	
	public boolean isLabeled() {
		return this.labeled;
	}
	
	public boolean notLabeled() {
		return !this.labeled;
	}
}
