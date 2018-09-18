package com.accenture.jersey.resource.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Country {

	private String country;
	private String capital;
	private int population;

	public Country() {
	}

	public Country(String country, String capital, int population) {
		super();
		this.country = country;
		this.capital = capital;
		this.population = population;
	}

	public String getCountry() {
		return country;
	}

	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}

	@XmlElement
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getPopulation() {
		return population;
	}

	@XmlElement
	public void setPopulation(int population) {
		this.population = population;
	}



}
