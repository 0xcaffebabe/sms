package wang.ismy.manage1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wang.ismy.manage1.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByUsername(String username);
}
