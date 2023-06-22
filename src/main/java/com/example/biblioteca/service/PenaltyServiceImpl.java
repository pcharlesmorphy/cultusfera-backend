package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.pojo.Mail;
import com.example.biblioteca.repository.IPenaltyRepository;

import com.example.biblioteca.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PenaltyServiceImpl implements IPenaltyService {

    @Autowired
    private IPenaltyRepository penaltyRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    EmailServiceImpl emailService;

    @Override
    @Transactional
    public Penalty save(Penalty penalty) {
        User user = userRepo.findById(penalty.getUser().getId()).get();
        user.setSuspended(true);
        userRepo.save(user);
        createPenaltyNotificationMail(penalty);
        return penaltyRepo.save(penalty);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Penalty> findByUser(User user) {
        return penaltyRepo.findAllByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Penalty> findAllPenalties(){
        return penaltyRepo.findAll();
    }

    @Scheduled(cron="0 0 8 * * *")
    public void checkPenaltiesExpirationDate(){
        //Verifica el final de las sanciones de los usuarios cada día a las 8

        List<Penalty> penalties = findAllPenalties();
        LocalDate todayDate = LocalDate.now();

        for (Penalty p:penalties){
            if(p.getEndDate().equals(todayDate)){
                User user = p.getUser();
                user.setSuspended(false);
                userRepo.save(user);
                createPenaltyExpirationNotificationMail(p);
            }
        }

    }

    public void createPenaltyNotificationMail (Penalty penalty) {

        String userMail = penalty.getUser().getEmail();
        String subject = "Aviso: Inicio de sanción";
        String message = createPenaltyMailMessage(penalty);
        Mail mail = new Mail (userMail,subject,message);
        emailService.sendUserExpirationWarningMail(mail);
    }

    public String createPenaltyMailMessage (Penalty penalty){
        String username = penalty.getUser().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola ").append(username)
                .append("\n\n\n")
                .append("Has sido sancionado a causa de: ").append(penalty.getReason().getReason()).append(".").append("\n")
                .append("Tu sanción comienza hoy día ").append(penalty.getStartDate()).append(" y finalizará el día ")
                .append(penalty.getEndDate()).append(".").append("\n\n\n")
                .append("Un saludo cordial del equipo de CultuSfera");
        return stringBuilder.toString();
    }

    public void createPenaltyExpirationNotificationMail (Penalty penalty) {

        String userMail = penalty.getUser().getEmail();
        String subject = "Aviso: Fin de sanción";
        String message = createPenaltyExpirationMailMessage(penalty);
        Mail mail = new Mail (userMail,subject,message);
        emailService.sendUserExpirationWarningMail(mail);
    }

    public String createPenaltyExpirationMailMessage (Penalty penalty){
        String username = penalty.getUser().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola ").append(username)
                .append("\n\n\n")
                .append("Tu sanción iniciada el día ").append(penalty.getStartDate()).append("a causa de: ")
                .append(penalty.getReason().getReason()).append(" ha finalizado. Ya puedes operar con normalidad.").append("\n\n\n")
                .append("Un saludo cordial del equipo de CultuSfera");
        return stringBuilder.toString();
    }
}

