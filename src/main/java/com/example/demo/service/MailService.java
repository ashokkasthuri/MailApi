package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.MailRepository;
import com.example.demo.model.Mail;
import com.example.demo.model.User;



@Service
public class MailService {

	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Autowired
    private MailRepository mailRepository;

    public List getAllMails() {
        List mails = new ArrayList<>();
        mailRepository.findAll().forEach(mails::add);
        return mails;
    }

    public Mail getMail(String id) {
        return (Mail) mailRepository.findById(id).orElseGet(Mail::new);
    }

    public void addMail(Mail mails) {
    	mailRepository.save(mails);
    }

    public void updateMail(String id, Mail mail) {
    	mailRepository.save(mail);
    }

    public void deleteMail(String id) {
    	mailRepository.deleteById(id);
    }
	
	public Boolean sendEmail(User user) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailAddress());
		mail.setSubject(user.getEmailSubject());
		mail.setText(user.getEmailBody());

		
		javaMailSender.send(mail);
		return true;
	}


}