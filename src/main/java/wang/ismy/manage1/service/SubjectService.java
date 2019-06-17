package wang.ismy.manage1.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.manage1.dao.SubjectRepository;
import wang.ismy.manage1.dto.SubjectDTO;
import wang.ismy.manage1.entity.Subject;
import wang.ismy.manage1.vo.StudentVO;
import wang.ismy.manage1.vo.SubjectVO;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AdminService adminService;

    @Autowired
    StudentSubjectService studentSubjectService;




    public List<SubjectVO> findAll(){

        return selectAll();
    }

    private List<SubjectVO> selectAll(){
        List<SubjectVO> list = new ArrayList<>();
        subjectRepository.findAll()
                .forEach(s->{
                    SubjectVO vo = new SubjectVO();
                    BeanUtils.copyProperties(s,vo);
                    var tacher =teacherService.findById(s.getTeacher());
                    vo.setTeacher(tacher.getTeacherName());

                    // 查询选取课题的学生
                    vo.setStudents(studentSubjectService.findBySubjectId(s.getId()));
                    vo.setTeacherId(s.getTeacher());
                    list.add(vo);
                });
        return list;
    }

    public void update(SubjectDTO subjectDTO) {

        var subject = subjectRepository.findById(subjectDTO.getId());
        if (subject.isEmpty()){
            throw new RuntimeException("课题不存在");
        }
        // 管理员或者该课程的教师才能修改
        if (adminService.isLogin() || subject.get().getTeacher().equals(teacherService.getCurrentTeacherId())){
            var s = subject.get();
            BeanUtils.copyProperties(subjectDTO,s);
            subjectRepository.save(s);
        }else{
            throw new RuntimeException("没有权限修改");
        }



    }

    @Transactional(rollbackOn = RuntimeException.class)
    public void delete(Integer id){
        var subject = subjectRepository.findById(id);
        if (subject.isEmpty()){
            throw new RuntimeException("课题不存在");
        }

        // 管理员或者该课程的教师才能修改
        if (adminService.isLogin() || subject.get().getTeacher().equals(teacherService.getCurrentTeacherId())) {
            // 先删除选修关系表
            studentSubjectService.deleteBySubjectId(id);
            // 后删除subject
            subjectRepository.deleteById(id);
        }
    }

    public void add(SubjectDTO subjectDTO){
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDTO,subject);
        subject.setTeacher(teacherService.getCurrentTeacherId());
        subjectRepository.save(subject);
        if (subject.getId() < 1) {
            throw new RuntimeException("添加课题异常");
        }
    }

    public SubjectVO findCurrentTeacherSubject(){
        if (teacherService.isLogin()){
            var list = selectAll();
            Integer id = teacherService.findByUsername(teacherService.getCurrentTeacherUsername()).getId();
            for (SubjectVO vo : list) {
                if (vo.getTeacherId().equals(id)){
                    return vo;
                }
            }
            return null;
        }
        throw new RuntimeException("未登录");
    }

    public Subject findById(Integer subjectId){
        return subjectRepository.findById(subjectId).get();
    }


}
