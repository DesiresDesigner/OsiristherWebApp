package com.osiristher.webapp.provider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by DesiresDesigner on 12/14/15.
 */

//public class MediUser extends User {
public class UserInfo{
    private long id;
    private long lr_id;
    private String screenName;
    private String role;
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public long getLr_id() {
        return lr_id;
    }

    public void setLr_id(long lr_id) {
        this.lr_id = lr_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
