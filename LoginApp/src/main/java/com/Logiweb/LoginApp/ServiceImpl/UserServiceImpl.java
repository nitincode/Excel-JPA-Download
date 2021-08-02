package com.Logiweb.LoginApp.ServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import javax.xml.crypto.Data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Logiweb.LoginApp.Entities.CountryMaster;
import com.Logiweb.LoginApp.Entities.NativeJPAQuery;
import com.Logiweb.LoginApp.Entities.StateMaster;
import com.Logiweb.LoginApp.Entities.UserAccount;
import com.Logiweb.LoginApp.Repository.ApplicationObject;
import com.Logiweb.LoginApp.Repository.CityMasterRepository;
import com.Logiweb.LoginApp.Repository.CountryMasterRepository;
import com.Logiweb.LoginApp.Repository.StateMasterRepository;
import com.Logiweb.LoginApp.Repository.UserRepository;
import com.Logiweb.LoginApp.Service.UserService;

@Service
public class UserServiceImpl extends ApplicationObject implements UserService {

	@Autowired
	CountryMasterRepository countryMasterRepository;

	@Autowired
	StateMasterRepository stateMasterRepository;

	@Autowired
	CityMasterRepository cityMasterRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<?> listJPAQuery() {
		// TODO Auto-generated method stub

		String sql = "SELECT c.country_id as countryid," + "s.state_id as stateid "
				+ "FROM COUNTRY c INNER JOIN STATE s ON c.country_id=s.country_id";
		return em.createNativeQuery(sql, NativeJPAQuery.class).getResultList();
	}

	@Override
	public ByteArrayInputStream downloadExcelData() throws IOException {
		// TODO Auto-generated method stub

		String sql = "SELECT c.country_id as countryid," + "s.state_id as stateid "
				+ "FROM COUNTRY c INNER JOIN STATE s ON c.country_id=s.country_id";
		List<NativeJPAQuery> al = em.createNativeQuery(sql, NativeJPAQuery.class).getResultList();
		List<String> header = Arrays.asList("Country", "State");
		XSSFWorkbook x = new XSSFWorkbook();
		XSSFSheet sheet = x.createSheet("Any Name");
		WriteDataSheet(al, sheet);
		WriteHeaderLine(sheet, x, header);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  x.write(baos);
		  baos.close();
		  
		return new ByteArrayInputStream(baos.toByteArray());

	}

	private void WriteHeaderLine(XSSFSheet sheet, XSSFWorkbook x, List<String> header) {
		Row rowHeader = sheet.createRow(0);
		int headerSize = header.size();
		for (int i = 0; i < headerSize; i++) {
			rowHeader.createCell(i).setCellValue( header.get(i));

		}
	}

	private void WriteDataSheet(List<NativeJPAQuery> al, XSSFSheet sheet) {
		//
		int rowCount = 1;
		for (NativeJPAQuery data : al) {

			Row row = sheet.createRow(rowCount++);
			Cell cell = row.createCell(0);
			if (data.getCountryid() != null) {
				row.createCell(0).setCellValue(data.getCountryid());
			} else {
				row.createCell(0).setCellValue("");
			}
			
			if (data.getStateid() != null) {
				row.createCell(1).setCellValue(data.getStateid());
			} else {
				row.createCell(1).setCellValue("");
			}

		}

	}

	@Override
	public List<?> getCty(Integer sid) {
		// TODO Auto-generated method stub
		String sql = "select * from STATE s where s.state_id =:param1";
		return em.createNativeQuery(sql, StateMaster.class).setParameter("param1", sid).getResultList();
	}

	@Override
	public Map<Integer, String> getCountries() {

		List<CountryMaster> list = countryMasterRepository.findAll();

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (CountryMaster i : list)
			map.put(i.getCountryId(), i.getCountryName());
		return map;

	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<StateMaster> list = stateMasterRepository.findByCountryId(countryId);

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (StateMaster i : list)
			map.put(i.getStateId(), i.getStateName());
		return map;

	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<StateMaster> list = cityMasterRepository.findByStateId(stateId);

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (StateMaster i : list)
			map.put(i.getStateId(), i.getStateName());
		return map;

	}

	@Override
	public boolean isEmailUnique(String emailId) {
		UserAccount userAcc = userRepository.findByUserEmail(emailId).get();
		if (userAcc.getUserEmail() != null) {
			return true;
		}
		return false;

	}

	@Override
	public boolean saveUser(UserAccount userAcc) {

		UserAccount a = userRepository.save(userAcc);
		if (a != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserAccount findUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginCheck(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

}
