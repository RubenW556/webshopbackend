package com.s1127833.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.s1127833.webshop.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@Entity
public class UserAccount implements UserDetails {


    @Id
    @GeneratedValue
    private long id;

    private String email;

    @Column(unique=true)
    private String username;
    private String password;
    private Role role = Role.ROLE_CUSTOMER;

    @JsonIgnore
    @Override
    public ArrayList<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    public boolean hasRole(Role role){
        return this.role.equals(role);
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
}
