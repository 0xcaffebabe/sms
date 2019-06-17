package wang.ismy.manage1.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import wang.ismy.manage1.dao.StudentRepository;
import wang.ismy.manage1.dto.LoginDTO;
import wang.ismy.manage1.dto.StudentDTO;
import wang.ismy.manage1.entity.Student;
import wang.ismy.manage1.vo.StudentVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    SessionService sessionService;

    public void login(String username,String password){

        var s = studentRepository.findByUsername(username);
        if (s == null){
            throw new RuntimeException("学生不存在");
        }

        if (!s.getPassword().equals(password)){
            throw new RuntimeException("密码不正确");
        }

    }

    public void addStudent(StudentDTO studentDTO){
        if (!adminService.isLogin()){
            throw new RuntimeException("管理员才能添加学生");
        }

        if (studentRepository.findByUsername(studentDTO.getUsername()) != null){
            throw new RuntimeException("学生用户名已存在");
        }

        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);

        studentRepository.save(student);
    }

    public List<StudentVO> selectAll(){
        if (!adminService.isLogin()){
            throw new RuntimeException("只有管理员才能进行");
        }
        return studentRepository.findAll().stream().map(StudentVO::convert).collect(Collectors.toList());
    }

    public void updateStudent(Integer id,StudentDTO dto){
        if (!adminService.isLogin()){
            throw new RuntimeException("管理员才能添加学生");
        }

        if (studentRepository.findById(id).isEmpty()){
            throw new RuntimeException("学生不存在");
        }

        Student student = new Student();
        student.setId(id);
        BeanUtils.copyProperties(dto,student);
        studentRepository.save(student);
    }


    public void deleteStudent(Integer id) {
        if (studentRepository.findById(id).isEmpty()){
            throw new RuntimeException("学生不存在");
        }

        studentRepository.deleteById(id);
    }

    public Student findById(Integer id){
        return studentRepository.findById(id).get();
    }

    public boolean isLogin() {
        var obj = sessionService.getFromSession("user");
        if (obj == null){
            return false;
        }

        return "学生".equals(((LoginDTO)obj).getType());
    }

    public String getCurrentStudentUsername(){
        if (isLogin()){
            return ((LoginDTO)sessionService.getFromSession("user")).getUsername();
        }
        throw new RuntimeException("未登录");
    }

    public Integer getCurrentStudentId(){
        return studentRepository.findByUsername(getCurrentStudentUsername()).getId();
    }
}
