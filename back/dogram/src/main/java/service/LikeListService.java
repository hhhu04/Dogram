package service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.dao.CommunityDao;
import model.dao.LikeListDao;
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import service.servicei.LikeListServiceI;

@Repository
@Component
public class LikeListService implements LikeListServiceI{
	
	@Autowired
    LikeListDao dao;

    public LikeListService(LikeListDao communityDao) {
        this.dao = communityDao;
    }
    
    public Long ckeckCookie(String cookie) throws ClassNotFoundException, SQLException {
    	
    	return dao.checkCookie(cookie);
    }

	
    public int create(LikeListDto dto,Long num) throws SQLException, ClassNotFoundException {
    	
        dto.setUserNum(num);
            dao.create(dto);
            
            System.out.println("create Community Success");
			return 1;
    }

}
