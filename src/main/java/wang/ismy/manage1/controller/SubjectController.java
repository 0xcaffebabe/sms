package wang.ismy.manage1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.manage1.dto.Result;
import wang.ismy.manage1.dto.SubjectDTO;
import wang.ismy.manage1.service.StudentSubjectService;
import wang.ismy.manage1.service.SubjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentSubjectService studentSubjectService;
    @GetMapping("/list")
    public Object findAll(){

        return Result.success(subjectService.findAll());
    }


    @PostMapping("/")
    public Object update(@RequestBody @Valid SubjectDTO subjectDTO){
        subjectService.update(subjectDTO);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id){
        subjectService.delete(id);
        return Result.success("删除成功");
    }

    @GetMapping("/self")
    public Object getCurrentTeacherSubject(){
        return Result.success(subjectService.findCurrentTeacherSubject());
    }

    @PutMapping("/")
    public Object add(@RequestBody SubjectDTO subjectDTO){
        subjectService.add(subjectDTO);
        return Result.success("添加课题成功");
    }

    @PutMapping("/student/{id}")
    public Object studentJoin(@PathVariable("id") Integer id){
        studentSubjectService.add(id);
        return Result.success("加入课题成功");
    }

    @GetMapping("/student")
    public Object getStudentSubject(){
        return Result.success(studentSubjectService.findCurrentStudent());
    }

    @DeleteMapping("/student/")
    public Object quitSubject(){
        studentSubjectService.quitSubject();
        return Result.success("退出成功");
    }
}
