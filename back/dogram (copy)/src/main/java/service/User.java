package service;

import java.sql.SQLException;

import model.dao.UserDao;
import model.dto.UserDto;

public class User {

	
	 /*
    static ParkingDAO dao = new DaoFactory().parkingDAO();
    static ParkingDTO dto = new ParkingDTO();
    */

    UserDao dao;

    public User(UserDao userDao) {
        this.dao = userDao;
    }


    public void create(String id, String password, String email) throws SQLException, ClassNotFoundException {

        UserDto dto = new UserDto();

        dto.setId(id);
        dto.setPssword(password);
        dto.setEmail(email);

            dao.create(dto);
                System.out.println("Check In User Success");
            
        
       
    }

    
	
}
