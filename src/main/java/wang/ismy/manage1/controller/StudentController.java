package wang.ismy.manage1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.manage1.dto.Result;
import wang.ismy.manage1.dto.StudentDTO;
import wang.ismy.manage1.service.StudentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PutMapping("/")
    public Object addStudent(@RequestBody @Valid StudentDTO studentDTO){
        studentService.addStudent(studentDTO);
        return Result.success("添加成功");
    }

    @GetMapping("/list")
    public Object findAll(){
        return Result.success(studentService.selectAll());
    }

    @PostMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,@RequestBody @Valid StudentDTO studentDTO){
        studentService.updateStudent(id,studentDTO);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id){
        studentService.deleteStudent(id);
        return Result.success("删除成功");
    }
}
