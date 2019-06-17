package wang.ismy.manage1.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student_subject")
public class StudentSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer subjectId;

    private Integer studentId;

}
