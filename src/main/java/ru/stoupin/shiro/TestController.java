package ru.stoupin.shiro;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class TestController {
    public  TestController(){

        log.info("TestController created");

    }
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public @ResponseBody String test(){

        return "test";
    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public @ResponseBody String login(){
        Subject currentUser = SecurityUtils.getSubject();
        if ( !currentUser.isAuthenticated() ) {
            //collect user principals and credentials in a gui specific manner
            //such as username/password html form, X509 certificate, OpenID, etc.
            //We'll use the username/password example here since it is the most common.
            //(do you know what movie this is from? ;)
            UsernamePasswordToken token = new UsernamePasswordToken("user", "password");
            //this is all you have to do to support 'remember me' (no config - built in!):
            //token.setRememberMe(true);
            currentUser.login(token);
        }


        return "logined";
    }
}
