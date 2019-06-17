package wang.ismy.manage1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wang.ismy.manage1.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/student")
    public String student(){
        if (adminService.isLogin()){
            return "admin_student";
        }
        throw new RuntimeException("未登录");
    }

    @RequestMapping("/teacher")
    public String teacher(){
        if (adminService.isLogin()){
            return "admin_teacher";
        }
        throw new RuntimeException("未登录");
    }

    @RequestMapping("/subject")
    public String subject(){
        if (adminService.isLogin()){
            return "admin_subject";
        }
        throw new RuntimeException("未登录");
    }

}
