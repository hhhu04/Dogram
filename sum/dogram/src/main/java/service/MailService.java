package service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import configuration.MailConfig;
import model.dao.MailDao;
import model.dto.MailDto;
import service.servicei.MailServiceImpl;

@Component
@Repository
public class MailService implements MailServiceImpl {


    @Autowired
    MailDao dao;

    public MailService(MailDao mailDao) {
        this.dao = mailDao;
    }
	
    @Autowired
	JavaMailSender mailSender;

	    public int mailSend(MailDto dto) throws ClassNotFoundException, SQLException {
	    	
	    	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	MailConfig mail = ctx.getBean("mail2",MailConfig.class);
	    	
	        int num = 0;
	        System.out.println(dto.getEmail());
	        
	       
			num = dao.checkUser(dto);
			
	        
	        if(num == 1) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(dto.getEmail());
	        message.setReplyTo("회신불가");
	        message.setSentDate(Date.valueOf(LocalDate.now()));
	        message.setFrom(dto.getFrom());
	        message.setSubject(dto.getSubject());
	        message.setText(dto.getContent());
	        mail.getMailSender().send(message);
	        
	        	return num;
	        }
	        else return 0;
	    }



		@Override
		public void sendEmail(MailDto mail) {
			// TODO Auto-generated method stub
			
		}

	 
 
//    @Override
//    public void sendEmail(MailDto mail) {
// 
//        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
//            @Override
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//                
//                helper.setFrom(mail.getFrom()); // recipient
//                helper.setTo(mail.getEmail()); //sender
//                helper.setSubject(mail.getSubject()); // mail title
//                helper.setText(mail.getContent(), true); // mail content
//            }
//        };
// 
//        mailSender.send(preparator);
//    }
//    
//    
//    
//    public int mailSend(String email) throws ClassNotFoundException, SQLException {
//		  int num = dao.checkUser(email);
//	     	if(num == 1) {
//		        
//		        try {
//	
//		             mail.setFrom(FROM_ADDRESS);
//		             mail.setEmail("to@gmail.com");
//		             mail.setSubject("This is Email test.");
//		             mail.setContent("Learn how to send email using Spring.");
//		      
//		             AbstractApplicationContext context = new AnnotationConfigApplicationContext(MailConfig.class);
//		             MailService mailService = (MailService) context.getBean("mailService");
//		             mailService.sendEmail(mail);
//		             context.close();
//
//
//		            return 1;
//	
//		        } catch(Exception e) {
//	
//		            System.out.println("UnsupportedEncodingException : " + e.getMessage());
//		            return -1;
//		        }
//	     	}
//			return -2;
//	                
//
//	    }
    
}


