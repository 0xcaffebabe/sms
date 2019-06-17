package wang.ismy.manage1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.manage1.dto.Result;
import wang.ismy.manage1.dto.StudentDTO;
import wang.ismy.manage1.dto.TeacherDTO;
import wang.ismy.manage1.service.StudentService;
import wang.ismy.manage1.service.TeacherService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PutMapping("/")
    public Object addTeacher(@RequestBody @Valid TeacherDTO dto){
        teacherService.addTeacher(dto);
        return Result.success("添加成功");
    }

    @GetMapping("/list")
    public Object findAll(){
        return Result.success(teacherService.selectAll());
    }

    @PostMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,@RequestBody @Valid TeacherDTO dto){
        teacherService.updateStudent(id,dto);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id){
        teacherService.deleteStudent(id);
        return Result.success("删除成功");
    }
}
