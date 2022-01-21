package com.s1127833.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class UserAccount implements UserDetails {


    @Id
    @GeneratedValue
    private long id;

    private String email;

    @Column(unique=true)
    private String username;
    private String password;
    private Authorities authorities = new Authorities();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return authorities.getAuthority();
    }

    public void setRole(String role) {

        this.authorities.setAuthority(role);
    }

    @JsonIgnore
    @Override
    public ArrayList<? extends GrantedAuthority> getAuthorities() {
        ArrayList<Authorities> list = new ArrayList<>();
        list.add(authorities);
        return  list;
    }



    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + authorities.getAuthority() +
                '}';
    }
}
