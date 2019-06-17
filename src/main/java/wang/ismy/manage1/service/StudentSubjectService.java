package wang.ismy.manage1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.manage1.dao.StudentSubjectRepository;
import wang.ismy.manage1.entity.StudentSubject;
import wang.ismy.manage1.vo.SubjectVO;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSubjectService {

    @Autowired
    StudentSubjectRepository repository;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    public List<String> findBySubjectId(Integer subjectId){

        List<String> ret = new ArrayList<>();
        repository.findBySubjectId(subjectId)
                .forEach(s->{
                    ret.add(studentService.findById(s.getStudentId()).getStudentName());
                });
        return ret;
    }

    public void add(Integer subjectId){
        Integer studentId = studentService.getCurrentStudentId();
        var subject = subjectService.findById(subjectId);

        StudentSubject ss = new StudentSubject();
        ss.setSubjectId(subjectId);
        ss.setStudentId(studentId);
        repository.save(ss);
    }

    public void deleteBySubjectId(Integer id){
      repository.deleteBySubjectId(id);
    }

    public SubjectVO findCurrentStudent(){
        Integer studentId=studentService.getCurrentStudentId();

        var ss= repository.findByStudentId(studentId);

        if (ss == null){
            return null;
        }

        var subject = subjectService.findById(ss.getSubjectId());

        return SubjectVO.convert(subject);
    }

    public void quitSubject() {
        Integer studentId = studentService.getCurrentStudentId();

        var ss = repository.findByStudentId(studentId);

        if (ss == null){
            throw new RuntimeException("学生还未选择课题");
        }

        repository.delete(ss);
    }
}
