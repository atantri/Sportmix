package com.example.sportmix;

import java.util.ArrayList;

public class Score {
	String team1, team2;
	int score,score2;
	public Score()
	{
		
	}
	public Score(String t1,String t2,int s1,int s2)
	{
		team1=t1;
		team2=t2;
		score=s1;
		score2=s2;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	long id;
	
	
	

}
