package com.Logiweb.LoginApp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE")
public class StateMaster {

	private Integer countryId;
	private Integer stateId;
	private String stateName;

	@Id
	@Column(name = "STATE_ID", nullable = false)
	public Integer getStateId() {
		return stateId;
	}
	
	@Column(name = "COUNTRY_ID", nullable = false)
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	@Column(name = "STATE_NAME", nullable = false)
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
