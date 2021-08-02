package com.Logiweb.LoginApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Logiweb.LoginApp.Entities.CountryMaster;

public interface CountryMasterRepository extends JpaRepository<CountryMaster, Integer> {

}
