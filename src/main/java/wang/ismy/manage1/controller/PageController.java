package wang.ismy.manage1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wang.ismy.manage1.dto.LoginDTO;
import wang.ismy.manage1.service.AdminService;
import wang.ismy.manage1.service.SessionService;
import wang.ismy.manage1.service.StudentService;
import wang.ismy.manage1.service.TeacherService;

@Controller
public class PageController {

    @Autowired
    SessionService sessionService;

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/home")
    public String home(){
        if (adminService.isLogin()){
            return "admin_home";
        }else if (teacherService.isLogin()){
            return "teacher_home";
        }else if (studentService.isLogin()){
            return "student_home";
        }
        return "/";
    }
}
