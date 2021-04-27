package configuration;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfig {
	

	
//	
 @Bean
 public JavaMailSender getMailSender() {
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("ShinTest94@gmail.com");
        mailSender.setPassword("q1w2e3r4!1234");
        
        Properties javaMailProperties = new Properties();
        
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
 
        mailSender.setJavaMailProperties(javaMailProperties);
        
        Session session = Session.getDefaultInstance(javaMailProperties);
    	
        MimeMessage msg = new MimeMessage(session);
        
        return mailSender;
    }
	 
	


	
}
