package com.Logiweb.LoginApp.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Logiweb.LoginApp.Entities.StateMaster;

public interface StateMasterRepository extends JpaRepository<StateMaster, Integer> {

	List<StateMaster> findByCountryId(Integer countryId);

	

}
