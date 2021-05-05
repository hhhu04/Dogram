package service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import configuration.DateNow;
import configuration.MailConfig;
import model.dao.CommunityDao;
import model.dto.CommunityDto;
import model.dto.UserDto;
import service.servicei.CommunityServiceI;

@Component
@Repository
public class CommunityService  implements CommunityServiceI{
	
	@Autowired
    CommunityDao dao;
	

    public CommunityService(CommunityDao communityDao) {
        this.dao = communityDao;
    }
    
    public Long ckeckCookie(String cookie) throws ClassNotFoundException, SQLException {
    	
    	return dao.checkCookie(cookie);
    }

	
    public int create(CommunityDto dto,Long num) throws SQLException, ClassNotFoundException {
    	
        dto.setUserNum(num);
       dao.create(dto);
            
            System.out.println("create Community Success");
			return 1;
    }
    
    
    

	public List<CommunityDto> read(UserDto userDto,CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		dao.checkAddress(userDto);
		List<CommunityDto> list = dao.read(dto);
		
		
		return list;
	}

	public int update(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		int num = dao.checkUser(dto);
		if(num == 1) {
			dao.update(dto);
			 System.out.println("update Community Success");
				return 1;
		}
		
		return -1;
	}
	
	public int delete(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		int num = dao.checkUser(dto);
		if(num == 1) {
			dao.delete(dto);
			 System.out.println("delete Community Success");
				return 1;
		}
		
		return -1;
	}

	public List<CommunityDto> read(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<CommunityDto> list = dao.read2(dto);
		
		return list;
	}
    
    
    
    
    
    
    
    
    
    
    

}
