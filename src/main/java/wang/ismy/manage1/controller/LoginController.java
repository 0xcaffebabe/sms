package wang.ismy.manage1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.manage1.dto.LoginDTO;
import wang.ismy.manage1.dto.Result;
import wang.ismy.manage1.service.AdminService;
import wang.ismy.manage1.service.SessionService;
import wang.ismy.manage1.service.StudentService;
import wang.ismy.manage1.service.TeacherService;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    SessionService sessionService;

    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginDTO dto) {
        System.out.println(dto);
        switch (dto.getType()) {
            case "管理员":
                adminService.login(dto.getUsername(), dto.getPassword());
                break;
            case "教师":
                teacherService.login(dto.getUsername(),dto.getPassword());
                break;
            case "学生":
                studentService.login(dto.getUsername(),dto.getPassword());
                break;
            default:
                throw new RuntimeException("未知账户类型");

        }

        sessionService.putInSession("user", dto);
        Result result = new Result();
        result.setMsg("登陆成功");
        result.setSuccess(true);
        return result;
    }
}
