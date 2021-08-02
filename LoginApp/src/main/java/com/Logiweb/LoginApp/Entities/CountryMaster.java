package com.Logiweb.LoginApp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class CountryMaster {

	private Integer countryId;
	private String countryName;

	@Id
	@Column(name = "COUNTRY_ID", nullable = false)
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "COUNTRY_NAME", nullable = false)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
