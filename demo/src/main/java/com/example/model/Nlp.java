package com.example.model;

public class Nlp {
	private int[] sentiments;
	
	public Nlp(int[] sentiments) {
		super();
		this.sentiments = sentiments;
	}
	
	public int[] getSentiments() {
		return sentiments;
	}

	public void setSentiments(int[] sentiments) {
		this.sentiments = sentiments;
	}

}
