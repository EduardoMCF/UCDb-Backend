package com.psoft.UCDb.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rateId;
	private double rate;
	
	Rate(){
		
	}
	
	Rate(Double rate, User user) {
		this.rate = rate;
	}
	
	public Double getRate() {
		return this.rate;
	}
}
