package service;

import java.sql.SQLException;
import java.util.List;

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

    public Long ckeckself(String cookie,CommentDto dto) throws ClassNotFoundException, SQLException {
    	
    	return dao.checkself(cookie,dto);
    }


	public Long create(CommentDto dto) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		  int num = dao.create(dto);
		  dao.readNum(dto);
         System.out.println("create comment Success");
			return 1L;
	}


	public int delete(CommentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.delete(dto);
		System.out.println("comment delete success");
		return 1;
	}


	public void cdelete(CommentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}


	public List<CommentDto> read(CommentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		List<CommentDto> list = dao.read(dto);
		
		return list;
	}
	
	public List<CommentDto> read2(CommentDto dto,Long num) throws SQLException {
		// TODO Auto-generated method stub
		
		List<CommentDto> list = dao.read2(dto,num);
		
		return list;
	}


	public int upComment(CommentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.update(dto);
		System.out.println("comment update success");
		return 1;
	}


	
    
    
	

}
