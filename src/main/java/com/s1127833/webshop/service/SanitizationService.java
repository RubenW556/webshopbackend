package com.s1127833.webshop.service;


import org.springframework.stereotype.Service;

@Service
public class SanitizationService {

    String allowedCharacters = "[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,.!%1234567890_ ]";

    public String Sanitize(String string){
        string = string.replaceAll(allowedCharacters,"");
        return string;
    }

    public Boolean CheckSanitize(String string){
        return string.matches(allowedCharacters);
    }
}
