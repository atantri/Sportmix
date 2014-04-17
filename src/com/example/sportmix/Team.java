package com.example.sportmix;

public class Team {
	String name;
	long id;
	String sportname;
	int wid;
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getSportname() {
		return sportname;
	}
	public void setSportname(String sportname) {
		this.sportname = sportname;
	}
	Team(String n,String sname)
	{
		this.name=n;
		this.sportname=sname;
		wid=1;
	}
	Team(String n,String sname,int wid)
	{
		this.name=n;
		this.sportname=sname;
		this.wid=wid;
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
