package com.psoft.UCDb.rest.model;

public class Subject {
	private String name;
	private int id;
	private int number_Of_Comments;
	private int number_Of_Likes;
	private int number_Of_Deslikes;
	private int number_Of_Rates;
	private double rate;

	public Subject() {

	}

	public Subject(String name, int d) {
		this.name = name;
		this.id = id;
		this.number_Of_Comments = 0;
		this.number_Of_Deslikes = 0;
		this.number_Of_Likes = 0;
		this.rate = 0;
		this.number_Of_Rates = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getNumber_Of_Comments() {
		return this.number_Of_Comments;
	}

	public int getId() {
		return this.id;
	}

	public int getNumber_Of_Likes() {
		return this.number_Of_Likes;
	}

	public int getNumber_Of_Deslikes() {
		return this.number_Of_Deslikes;
	}

	public double getRate() {
		return this.rate;
	}

	public void setNumber_Of_Comments(int comments) {
		this.number_Of_Comments += 1;
	}

	public void setNumber_Of_Likes(int likes) {
		this.number_Of_Likes += 1;
	}

	public void setNumber_Of_Deslikes(int deslikes) {
		this.number_Of_Deslikes += 1;
	}

	public void setRate(double rate) {
		this.rate = rate; // vamos precisar armazanar a quantidade de pessoas que votaram pra poder fazer
							// a media
	}
	private double calculate_Rate() {
		return (Double) null;
		
	}
}
