package com.osiristher.webapp.provider;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Trainer;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DesiresDesigner on 2/26/16.
 */

@Component
public class OsiristherAuthProvider implements AuthenticationProvider {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TrainerRepo trainerRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException { // return auth token or null
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserInfo user = null;
        try {
            user = new UserManager().getUserInfo(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(user.getRole()));
            user = getInnerId(user);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }

    private UserInfo getInnerId(UserInfo user) {
        if (user.getRole().equals("ROLE_TRAINER")) {
            Trainer trainer = trainerRepo.findByLrid(user.getLr_id());
            if (trainer == null) {
                trainer = new Trainer();
                trainer.setName(user.getScreenName());
                trainer.setLrid(user.getLr_id());
                trainerRepo.save(trainer);
            } else {
                user.setId(trainer.getId());
            }
        } else {
            Student student = studentRepo.findByLrid(user.getLr_id());
            if (student == null) {
                student = new Student();
                student.setName(user.getScreenName());
                student.setLr_id(user.getLr_id());
                studentRepo.save(student);
            } else {
                user.setId(student.getId());
            }
        }
        return user;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
