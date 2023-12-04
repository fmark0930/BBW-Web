package com.bbwweb.service;


import com.bbwweb.Result.Result;
import org.springframework.stereotype.Service;

public interface UserService {
    public Result UserLogin(String code);
}
