package com.data.social.service;

public interface SecurityService {
    public void autologin(String u, String p);
    String findloggedUsername();
}
