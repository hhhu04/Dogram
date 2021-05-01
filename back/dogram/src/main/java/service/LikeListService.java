package service;

import java.sql.SQLException;
import java.util.List;

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

    public Long checkLike(LikeListDto dto) throws SQLException {
    	
    	return dao.delCheck(dto);
    	
    }
	
    public int create(LikeListDto dto) throws SQLException, ClassNotFoundException {
    	
            dao.create(dto);
            System.out.println("create like Success");
			return 1;
    }
    
    public int delete(LikeListDto dto) throws SQLException, ClassNotFoundException {
    	
		dao.delet(dto);
		System.out.println("delete like Success");
		return 1;
		
    }
    
    
    public List<LikeListDto> read(LikeListDto dto) throws SQLException {
    	List<LikeListDto> list = dao.read(dto);
    	
    	return list;
    }
    
    
    
    

}
