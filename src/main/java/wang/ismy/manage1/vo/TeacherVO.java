package wang.ismy.manage1.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import wang.ismy.manage1.entity.Teacher;

@Data
public class TeacherVO {

    private Integer id;

    private String username;

    private String password;

    private String teacherName;

    public static TeacherVO convert(Teacher teacher){

        TeacherVO vo = new TeacherVO();
        BeanUtils.copyProperties(teacher,vo);
        return vo;
    }
}
