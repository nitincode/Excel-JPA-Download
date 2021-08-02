package com.Logiweb.LoginApp.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Logiweb.LoginApp.Entities.UserAccount;
import com.Logiweb.LoginApp.Repository.UserRepository;

@Service
public interface UserService {

	public Map<Integer,String> getCountries();
	public Map<Integer,String> getStates(Integer countryId);
	public Map<Integer,String> getCities(Integer stateId);
	public boolean isEmailUnique(String emailId);
	public boolean saveUser(UserAccount userAcc);
	public boolean isTempPwdValid(String email,  String tempPwd);

	//public boolean unlockAccount(UnlockAccount unlockAcc);
	public UserAccount findUserByEmail(String emailId);

	public String loginCheck(String email, String pwd);

	public List <?>listJPAQuery();
	List<?> getCty(Integer sid);

	public ByteArrayInputStream downloadExcelData() throws IOException;
  	  	
}
