package service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.dao.MailDao;
import model.dao.StoreDao;
import model.dto.CommunityDto;
import model.dto.StoreDto;
import model.dto.UserDto;
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


	public List<StoreDto> read(UserDto userDto, StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<StoreDto> list = dao.read(dto);
		System.out.println("store list success");
		
		return list;
	}
	 
	 
	 
	
}
