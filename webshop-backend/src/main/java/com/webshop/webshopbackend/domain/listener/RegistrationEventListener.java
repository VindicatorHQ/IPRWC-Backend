package com.webshop.webshopbackend.domain.listener;

import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.entity.User;
import com.webshop.webshopbackend.domain.event.OnRegistrationCompleteEvent;
import com.webshop.webshopbackend.service.NotificationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationEventListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserDAO userDAO;
    private final NotificationService notificationService;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void onApplicationEvent(@NonNull OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String userName = user.getName();
        String token = UUID.randomUUID().toString();
        userDAO.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/confirm?token=" + token;

        Context context = new Context();
        context.setVariable("confirmationUrl", confirmationUrl);
        context.setVariable("name", userName);
        String htmlContent = templateEngine.process("registrationConfirmationEmail", context);

        notificationService.sendEmail(recipientAddress, subject, htmlContent);
    }
}
