package com.Logiweb.LoginApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Logiweb.LoginApp.Entities.CityMaster;
import com.Logiweb.LoginApp.Entities.StateMaster;
public interface CityMasterRepository  extends JpaRepository<CityMaster, Integer> {

	List<StateMaster> findByStateId(Integer stateId);

}
