package configuration;

import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import model.DBHandler;
import model.dao.UserDao;
import model.dto.UserDto;
import service.UserService;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class Config {

    @Value("${db.username}")
    private String username;

    @Value("${db.url}")
    private String url;

    @Value("${db.password}")
    private String password;

    private String driver = "com.mysql.jdbc.Driver";

    @Bean
    public DBHandler dbHandler() {
        return new DBHandler();
    }

 
    @Bean
    public UserDao userDao() {
        return new UserDao(dbHandler());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
//        DriverManager
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }


    @Bean
    public UserService user() {
        return new UserService(userDao());
    }


}
