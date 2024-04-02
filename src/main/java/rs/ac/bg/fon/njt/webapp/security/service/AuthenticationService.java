/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.service;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMailMessage;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.domain.User;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.UserRepository;
import rs.ac.bg.fon.njt.webapp.domain.enums.Role;
//import rs.ac.bg.fon.njt.webapp.dto.RegLinkCheckDto;
import rs.ac.bg.fon.njt.webapp.dto.RegPageLinkDto;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationRequest;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationResponse;
import rs.ac.bg.fon.njt.webapp.security.communication.RegisterRequest;

/**
 *
 * @author aleks
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private MailSender mailSender;

    private SimpleMailMessage templateMessage;

    private String randomString;
    private String email;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public String getJwtAndSendEmail(RegPageLinkDto dto) {
        String token = jwtService.generateSimpleExpirationToken(dto.getRandomString());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dto.getEmail());
        this.randomString = dto.getRandomString();
        this.email = dto.getEmail();
        String link = dto.getLink() + "?token=" + token + "&email=" + dto.getEmail();
//        String link = dto.getLink() + "?token=" + token;
        System.out.println("-------------==================---------------================-------------");
        System.out.println(link);
        String mailTxt = "Postovani, " + "\n"
                + "klikom na sledeci link dobijate stranicu za geristraciju. link je validan 3 minuta.\n"
                + link
                + "\nAko niste zeleli da napravite nalog na stranici za evidenciju zaposlenih, ignorisite ovaj mejl.\nHvala";
        mailMessage.setText(mailTxt);
        mailMessage.setFrom("MS_0qqejK@trial-zr6ke4nerqm4on12.mlsender.net");
        mailMessage.setSubject("Registracija");
        System.out.println(mailTxt);
        
        mailSender = new JavaMailSenderImpl();
        JavaMailSenderImpl implSender = (JavaMailSenderImpl) mailSender;
        
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.from", "MS_0qqejK@trial-zr6ke4nerqm4on12.mlsender.net");
        implSender.setJavaMailProperties(properties);
        implSender.setPort(587);
        implSender.setHost("smtp.mailersend.net");
        implSender.setUsername("MS_0qqejK@trial-zr6ke4nerqm4on12.mlsender.net");
        implSender.setPassword("F6jokTwySK8kyV3z");
        
        mailSender.send(mailMessage);
        return token;
    }

    public boolean checkLink(String token,String email) {
        return jwtService.isSimpleExpirationTokenValid(token, randomString)&&email.equals(this.email);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        @Valid
        User user = new User(request.getFirstname(),
                request.getLastname(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER);

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new InvalidDataException("username is taken");
        }

        user = userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty() || request.getUsername().isBlank()) {
            throw new InvalidDataException("invalid username");
        }
        Optional<User> optional = userRepository.findByUsername(request.getUsername());
        if (optional.isEmpty()) {
            throw new InvalidDataException("username ne postoji");
        }
        authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword()
                        )
                );
        User user = optional.get();
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

}
