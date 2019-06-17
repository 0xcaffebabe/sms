package wang.ismy.manage1.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import wang.ismy.manage1.entity.Subject;

import java.util.List;

@Data
public class SubjectVO {

    private Integer id;

    private String name;

    private String request;

    private List<String> students;

    private String teacher;

    private Integer teacherId;

    public static SubjectVO convert(Subject subject){
        SubjectVO vo = new SubjectVO();
        BeanUtils.copyProperties(subject,vo);
        return vo;
    }
}
