package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.User;
import com.example.demo.service.MailService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MailService.class})
public class EmailServiceTest {

    @Autowired
    private MailService emailService;

    private User userTest;


    @Test
    public void testEmail() throws MessagingException, IOException {
    	
    	userTest = prepareMessage(false);
    	
    	Boolean isSent = emailService.sendEmail(userTest);

        assertThat(isSent).isEqualTo(true);

        Assert.assertFalse(userTest == null);



    }
    
    private User prepareMessage(Boolean attachmentIncluded)
    {
        List<String> toList = new ArrayList<>(1);
        toList.add("test.springboo@gmail.com");

        User email = new User();
        email.setEmailSubject("Simple e-mail test");
        email.setEmailAddress("test.springboo@gmail.com");
        email.setEmailBody("This is simple e-mail message.");

        
        return email;
    }

}
