package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Mail;
import com.example.demo.model.User;
import com.example.demo.service.MailService;


@RestController
public class RegistrationController {

	@Autowired
	private MailService notificationService;

	@Autowired
	private User user;
	
	@RequestMapping("send-mail")
	public String send() {

		user.setEmailAddress("ashokraj.kasthuri@gmail.com");
		user.setEmailSubject("Testing Mail API");
		user.setEmailBody("Hurray ! You have done that...");
		try {
			notificationService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
	
	@GetMapping("/mails")
    public List getAllMails() {
        return notificationService.getAllMails();
    }

    @GetMapping("/mails/{id}")
    public Mail getMail(@PathVariable String mailId){
        return notificationService.getMail(mailId);
    }

    @PostMapping("/mails")
    public void addMail(@RequestBody Mail mail) {
    	notificationService.addMail(mail);
    }

    @PutMapping("/mails/{id}")
    public void updateMail(@PathVariable String mailId, @RequestBody Mail mail) {
    	notificationService.updateMail(mailId, mail);
    }

    @DeleteMapping("/mails/{id}")
    public void deleteMail(@PathVariable String mailId) {
    	notificationService.deleteMail(mailId);
    }

	
}
