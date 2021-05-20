package configuration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateNow2 {
	
	@Bean
	public String date() {
		Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyMMddHHmmss");
        return date.format(dt);
	}
	
	

}
