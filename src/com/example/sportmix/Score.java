package com.example.sportmix;

import java.util.ArrayList;

public class Score {
	String team1, team2;
	String score;
	long id;
	Score(String t1,String t2,String s)
	{
		team1=t1;
		team2=t2;
		score=s;
	}
	Score()
	{
		
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
