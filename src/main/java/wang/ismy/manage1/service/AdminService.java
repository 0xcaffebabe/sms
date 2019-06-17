package wang.ismy.manage1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;
import wang.ismy.manage1.dao.AdminRepository;
import wang.ismy.manage1.dao.StudentRepository;
import wang.ismy.manage1.dao.SubjectRepository;
import wang.ismy.manage1.dao.TeacherRepository;
import wang.ismy.manage1.dto.LoginDTO;
import wang.ismy.manage1.dto.SystemInfoDTO;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;


    public void login(String username,String password){
        var admin = adminRepository.findByUsername(username);
        if (admin == null){
            throw new RuntimeException("管理员不存在");
        }

        if (!admin.getPassword().equals(password)){
            throw new RuntimeException("密码不正确");
        }
    }

    public boolean isLogin(){
        var obj = sessionService.getFromSession("user");
        if (obj == null){
            return false;
        }

        return "管理员".equals(((LoginDTO)obj).getType());

    }

    public SystemInfoDTO getSystemInfo(){
        if (isLogin()){
            SystemInfoDTO dto = new SystemInfoDTO();
            dto.setStudent(studentRepository.count());
            dto.setTeacher(teacherRepository.count());
            dto.setSubject(subjectRepository.count());
            return dto;
        }
        throw new RuntimeException("不是管理员");
    }
}
