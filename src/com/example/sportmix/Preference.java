package com.example.sportmix;

public class Preference {
	long id;
	String team;
	Preference(String t)
	{
		team=t;
	}
	Preference()
	{
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
}
