
package com.example.LibreriaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Async
    public void enviarEmail(String destinatario, String asunto, String contenido){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(destinatario);
//        mailMessage.setFrom("noreply@libreria-web.com");
//        mailMessage.setFrom("alexis_molina789@hotmail.com");
        mailMessage.setSubject(asunto);
        mailMessage.setText(contenido);
        javaMailSender.send(mailMessage);
    }
    
}
