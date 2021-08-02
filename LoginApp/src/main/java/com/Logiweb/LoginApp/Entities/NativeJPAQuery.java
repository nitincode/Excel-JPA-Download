package com.Logiweb.LoginApp.Entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class NativeJPAQuery {

	@Id
	private Integer countryid;
	private Integer stateid;
	public Integer getCountryid() {
		return countryid;
	}
	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}
	public Integer getStateid() {
		return stateid;
	}
	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}
	
	
	
	
	
	
}
