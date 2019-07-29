package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mail;

@Repository
public interface MailRepository extends CrudRepository<Mail, String>  {
}