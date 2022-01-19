package com.s1127833.webshop.model;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority {
    private String role = "CUSTOMER";

    @Override
    public String getAuthority(){

        return role;
    }

    public void setAuthority(String role){
        this.role = role;
    }
}
