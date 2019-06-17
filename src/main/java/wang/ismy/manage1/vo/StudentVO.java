package wang.ismy.manage1.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import wang.ismy.manage1.entity.Student;

@Data
public class StudentVO {

    private Integer id;

    private String username;

    private String studentName;

    public static StudentVO convert(Student student){

        StudentVO vo = new StudentVO();
        BeanUtils.copyProperties(student,vo);
        return vo;
    }
}
