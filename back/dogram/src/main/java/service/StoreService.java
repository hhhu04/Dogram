package service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.dao.MailDao;
import model.dao.StoreDao;
import model.dto.StoreDto;
import service.servicei.StoreI;

@Component
@Repository
public class StoreService implements StoreI{

	@Autowired
	StoreDao dao;
	
	
	 public StoreService(StoreDao storeDao) {
	        this.dao = storeDao;
	    }
	 
	 
	 public Long ckeckCookie(String cookie) throws ClassNotFoundException, SQLException {
	    	
	    	return dao.checkCookie(cookie);
	    }
		
	 
	 public int create(StoreDto dto,Long userNum) throws ClassNotFoundException, SQLException {
		 
		 dto.setUserNum(userNum);
		 System.out.println(toString());
         dao.create(dto);
		 
         System.out.println("create Community Success");
		 return 1;
	 }
	 
	 
	 
	
}
