/**
 * @author FW
 * @date 2023/12/4 16:03
 * @Software: IntelliJ IDEA BBW-Web
 * @version 1.0
 */
package com.bbwweb.controller;

import com.bbwweb.Result.Result;
import com.bbwweb.service.UserService;
import com.bbwweb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result userLogin(@RequestParam(name = "code") String code){
        System.out.println(code);
        Result result =userService.UserLogin(code);
        return result;
    }
}
