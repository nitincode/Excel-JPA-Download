package com.Logiweb.LoginApp.Controller;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Logiweb.LoginApp.Entities.UserAccount;
import com.Logiweb.LoginApp.ServiceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api/v2")
public class LoginRestController {

	@Autowired
	UserServiceImpl userServiceImpl;

	
	@GetMapping("/listJPAQuery")
	public List<?> listJPAQuery() {
		return userServiceImpl.listJPAQuery();
	}   //getCty
	
	
	@GetMapping("/downloadExcel")
	public ResponseEntity<?>downloadExcel() throws IOException {
	
		//byte[]  reportData	=userServiceImpl.downloadExcelData();
		
//		HttpHeaders  httpHeaders = new  HttpHeaders(); 
//		httpHeaders.set("charset", "utf-8");
//		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//		if(reportData!= null)
//		{
//			httpHeaders.setContentLength(reportData.length);
//			
//			StringBuilder builder= new StringBuilder();
//			
//			builder.append("reportName");
//			String filename = "tutorials.xls";
//			//httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=+Builder.toString()");
//			//httpHeaders.set("filename",Builder.toString());
//		
//		    //   httpHeaders.(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//
//		// return new ResponseEntity<>(reportData,httpHeaders,HttpStatus.OK);
//		       
//		       return ResponseEntity.ok()
//		    	        .header(httpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//		    	        .body(reportData);
//		    	  
//		}
//		else {
//			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
//
//		}
		
	
		String filename = "tutorials.xlsx";
	    InputStreamResource file = new InputStreamResource(userServiceImpl.downloadExcelData());
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(file);
	  }
		
		
		
	
	
	
	@GetMapping("/getCity")
	public List<?> getCity() {
		return userServiceImpl.getCty(1);
	}
	
	
	
	@PostMapping("/saveUser")
	public boolean createContactDetails(@RequestBody UserAccount userAccount) {
		userAccount.setAccstatus("inActive");
		userAccount.setUserPwd(this.generateStrongPassword());
		boolean a = userServiceImpl.saveUser(userAccount);
		return a;
	}
	
	
	
	@GetMapping("/counties")
	public Map<Integer, String> getAllCounties() {
		return userServiceImpl.getCountries();
	}

	@GetMapping("/counties/{countryId}")
	public Map<Integer, String> findByCountryId(@PathVariable Integer countryId) {
		return userServiceImpl.getStates(countryId);
	}

	@GetMapping("/state/{stateId}")
	public Map<Integer, String> findByStateId(@PathVariable Integer stateId) {
		return userServiceImpl.getStates(stateId);
	}

	@GetMapping("/isEmailUnique/{emailId}")
	public boolean isEmailUnique(@PathVariable String emailId) {
		return userServiceImpl.isEmailUnique(emailId);
	}

	public String generateStrongPassword() {

	 String DIGIT = "0123456789";
 
		// at least 2 digits
		StringBuilder result = new StringBuilder(20);
		String strDigit = generateRandomString(DIGIT, 2);
		result.append(strDigit);

		String password = result.toString();
		return password;

	}

	private static SecureRandom random = new SecureRandom();

	private static String generateRandomString(String input, int size) {
		if (input == null || input.length() <= 0)
			throw new IllegalArgumentException("Invalid input.");
		if (size < 1)
			throw new IllegalArgumentException("Invalid size.");

		StringBuilder result = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			// produce a random order
			int index = random.nextInt(input.length());
			result.append(input.charAt(index));
		}
		return result.toString();
	}

}
