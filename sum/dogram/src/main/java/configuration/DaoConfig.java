package configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import model.DBHandler;
import model.dao.UserDao;

@Configuration
public class DaoConfig {

	@Value("#{config['db.driverClass']}")
    private String driver;

    @Value("#{config['db.url']}")
    private String url;

    @Value("#{config['db.username']}")
    private String username;

    @Value("#{config['db.password']}")
    private String password;

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
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
	
}
