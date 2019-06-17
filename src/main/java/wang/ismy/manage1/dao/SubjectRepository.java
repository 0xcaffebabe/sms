package wang.ismy.manage1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import wang.ismy.manage1.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
