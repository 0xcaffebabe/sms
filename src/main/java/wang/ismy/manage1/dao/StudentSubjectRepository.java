package wang.ismy.manage1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import wang.ismy.manage1.entity.StudentSubject;

import java.util.List;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject,Integer> {

    List<StudentSubject> findBySubjectId(Integer subjectId);

    int deleteBySubjectId(Integer id);

    StudentSubject findByStudentId(Integer id);
}
