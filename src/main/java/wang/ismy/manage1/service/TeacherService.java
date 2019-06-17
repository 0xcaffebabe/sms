package wang.ismy.manage1.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import wang.ismy.manage1.dao.StudentRepository;
import wang.ismy.manage1.dao.TeacherRepository;
import wang.ismy.manage1.dto.LoginDTO;
import wang.ismy.manage1.dto.StudentDTO;
import wang.ismy.manage1.dto.TeacherDTO;
import wang.ismy.manage1.entity.Student;
import wang.ismy.manage1.entity.Teacher;
import wang.ismy.manage1.vo.StudentVO;
import wang.ismy.manage1.vo.TeacherVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    SessionService sessionService;

    public void login(String username, String password) {
        var teacher = teacherRepository.findByUsername(username);

        if (teacher == null){
            throw new RuntimeException("教师不存在");
        }

        if (!teacher.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
    }

    public Teacher findById(Integer id){
        return teacherRepository.findById(id).get();
    }

    public void addTeacher(TeacherDTO teacherDTO) {
        if (!adminService.isLogin()) {
            throw new RuntimeException("管理员才能添加教师");
        }

        if (teacherRepository.findByUsername(teacherDTO.getUsername()) != null) {
            throw new RuntimeException("教师用户名已存在");
        }

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);

        teacherRepository.save(teacher);
    }

    public List<TeacherVO> selectAll() {
        if (!adminService.isLogin()) {
            throw new RuntimeException("只有管理员才能进行");
        }
        return teacherRepository.findAll().stream().map(TeacherVO::convert).collect(Collectors.toList());
    }

    public void updateStudent(Integer id, TeacherDTO dto) {
        if (!adminService.isLogin()) {
            throw new RuntimeException("管理员才能添加教师");
        }

        if (teacherRepository.findById(id).isEmpty()) {
            throw new RuntimeException("教师不存在");
        }

        Teacher teacher = new Teacher();
        teacher.setId(id);
        BeanUtils.copyProperties(dto, teacher);
        teacherRepository.save(teacher);
    }


    public void deleteStudent(Integer id) {
        if (teacherRepository.findById(id).isEmpty()) {
            throw new RuntimeException("教师不存在");
        }

        teacherRepository.deleteById(id);
    }

    public boolean isLogin() {
        var obj = sessionService.getFromSession("user");
        if (obj == null){
            return false;
        }

        return "教师".equals(((LoginDTO)obj).getType());
    }

    public String getCurrentTeacherUsername(){
        if (isLogin()){
            return ((LoginDTO)sessionService.getFromSession("user")).getUsername();
        }
        throw new RuntimeException("未登录");
    }

    public Integer getCurrentTeacherId(){
        return findByUsername(getCurrentTeacherUsername()).getId();
    }

    public Teacher findByUsername(String username){
        return teacherRepository.findByUsername(username);
    }


}

