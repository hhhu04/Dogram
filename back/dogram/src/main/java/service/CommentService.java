package service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.dao.CommentDao;
import model.dao.CommunityDao;
import model.dto.CommentDto;
import service.servicei.CommentServiceI;

@Component
@Repository
public class CommentService implements CommentServiceI{
	
	@Autowired
    CommentDao dao;
	

    public CommentService(CommentDao commentDao) {
        this.dao = commentDao;
    }


    public int ckeckCookie(String cookie,CommentDto dto) throws ClassNotFoundException, SQLException {
    	
    	return dao.checkCookie(cookie,dto);
    }



	public int create(CommentDto dto) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 dao.create(dto);
         System.out.println("create comment Success");
			return 1;
	}


	public int delete(CommentDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}


	
    
    
	

}
