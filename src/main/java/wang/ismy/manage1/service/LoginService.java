package wang.ismy.manage1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.manage1.dto.LoginDTO;

@Service
public class LoginService {

    @Autowired
    SessionService sessionService;

    public LoginDTO getUser(){
        return (LoginDTO) sessionService.getFromSession("user");
    }
}
