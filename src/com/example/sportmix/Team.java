package com.example.sportmix;

public class Team {
	String name;
	long id;
	String sportname;
	public String getSportname() {
		return sportname;
	}
	public void setSportname(String sportname) {
		this.sportname = sportname;
	}
	Team(String n,String sname)
	{
		name=n;
		sportname=sname;
	}
	Team()
	{
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
