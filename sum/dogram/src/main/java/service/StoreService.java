package service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import model.dao.MailDao;
import model.dao.StoreDao;
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import model.dto.StoreDto;
import model.dto.UserDto;
import model.dto.WantListDto;
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


	public int addWant(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.getTitle(dto);
		dao.addWant(dto);
		System.out.println("add wnat success");
		return 1;
	}


	public Long checkWant(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return dao.delCheck(dto);
	}


	public int deleteWant(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.delet(dto);
		System.out.println("delete like Success");
		return 1;
	}


	public int update(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.update(dto);
		
		
		return 1;
	}


	public int delete(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.delete(dto);
		System.out.println("delete success");
		return 1;
	}


	public List<WantListDto> read(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<WantListDto> list=dao.myRead(dto);
		return list;
	}


	public List<StoreDto> read(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<StoreDto> list = dao.read2(dto);
		System.out.println("store list success");
		
		return list;
	}
	 
	public String upload(MultipartFile file, ModelAndView mv, Model model) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		String img;
		
		if(!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File("/home/cat/eclipse-web/dogram/src/main/webapp/resources/img", file.getOriginalFilename()));
			model.addAttribute("msg", "File uploaded successfully.");
			img = "img/"+file.getOriginalFilename();
			System.out.println("99");
		}else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
			img = "-1";
			System.out.println("4");
		}
		
		System.out.println("4");
		return img;
	}
	 
	
}
