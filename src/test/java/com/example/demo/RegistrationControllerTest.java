package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Mail;
import com.example.demo.model.User;
import com.example.demo.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationControllerTest.class)

public class RegistrationControllerTest {
	
		@Autowired
		private MockMvc mvc;

	  @Autowired private ObjectMapper objectMapper;

	  @MockBean private MailService mailService;
	  
	  @Autowired
	    private RegistrationControllerTest controller;

	  private Mail mail;
	  private User user;
	  
	  @Before
	  public void setup() {
	    JacksonTester.initFields(this, objectMapper);
	    mail = new Mail();
	    user = new User();
	  }
	  
	  @Test
		public void testEmail(){
		  mailService.sendEmail(user);
		}
	  
	  @Test
	    public void controllerInitializedCorrectly() {
	        assertThat(controller).isNotNull();
	    }
	  
	  @Test
	  public void getAllmails() throws Exception
	  {
	    mvc.perform( MockMvcRequestBuilders
	        .get("/mails")
	        .accept(MediaType.APPLICATION_JSON))
	        .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.mails").exists())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.mails[*].mailId").isNotEmpty());
	  }
	   
	  @Test
	  public void getEmployeeByIdAPI() throws Exception
	  {
	    mvc.perform( MockMvcRequestBuilders
	        .get("/mails/{id}", 1)
	        .accept(MediaType.APPLICATION_JSON))
	        .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.mailId").value(1));
	  }
	  
	  

}
