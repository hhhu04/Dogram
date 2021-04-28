package service;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
       String add = dao.checkAddress(dto.getUserNum());
       if(add != null) dao.create(dto, add);
       else dao.create(dto);
            
            System.out.println("create Community Success");
			return 1;
    }
    
    
    

	public int read(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		
		dao.checkAddress(userDto);
		CommunityDto dto = new CommunityDto();
		dao.read(dto);
		
		
		return 0;
	}
	
    
    
    
    
    
    
    
    
    
    
    

}
