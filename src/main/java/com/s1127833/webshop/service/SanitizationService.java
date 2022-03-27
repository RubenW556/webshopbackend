package com.s1127833.webshop.service;


import org.springframework.stereotype.Service;

@Service
public class SanitizationService {

    public String Sanitize(String string){
        string = string.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,.!%1234567890_ ]","");
        return string;
    }

    public Boolean CheckSanitize(String string){
        return string.matches("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,.!%1234567890@_ ]");
    }
}
