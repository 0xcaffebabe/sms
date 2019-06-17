package wang.ismy.manage1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import wang.ismy.manage1.entity.Student;
import wang.ismy.manage1.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    Teacher findByUsername(String username);
}
