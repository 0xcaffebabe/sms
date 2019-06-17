<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>
<div class="container" id="app">

    <div class="row">
        <div class="col-md-4">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="/home">主页</a></li>
                <li role="presentation" class="active"><a href="/admin/student">学生管理</a></li>
                <li role="presentation"><a href="/admin/teacher">教师管理</a></li>
                <li role="presentation"><a href="/admin/subject">课题管理</a></li>
            </ul>

        </div>
        <div class="col-md-8">
            <table class="table table-striped">
                <button class="btn btn-primary" style="float: right" @click="addStudent">添加</button>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>

                <tr v-for="s in studentList">
                    <td>{{s.id}}</td>
                    <td>{{s.username}}</td>
                    <td>{{s.studentName}}</td>
                    <td>
                        <button class="btn btn-success" @click="updateStudent(s)">修改</button>
                        <button class="btn btn-danger" @click="deleteStudent(s)">删除</button>
                    </td>
                </tr>

            </table>
        </div>
    </div>
</div>

</body>
<script src="/vue.js"></script>
<script src="/jquery.js"></script>
<script src="/common.js"></script>
<script src="/mdd.js"></script>
<script>
    var app = new Vue({
        el: "#app",
        data: {
            studentList: [],
        }
        ,
        created: function () {
            this.selectStudentList();
        }
        ,
        methods: {
            addStudent: function () {

                var username = prompt("情输入学生用户名");
                var password = prompt("请设置学生密码");
                var name = prompt("情输入学生姓名");

                var that  = this;
                if (username && password && name) {
                    common.ajax.put("/api/student/", function (r) {
                        if (r.success) {
                            alert(r.data);
                            that.selectStudentList();
                        } else {
                            alert(r.msg);
                        }

                    }, {
                        username: username,
                        password: hex_md5(password).toLocaleUpperCase(),
                        studentName: name
                    });

                }
            }
            ,
            selectStudentList: function () {

                var that = this;
                common.ajax.get("/api/student/list", function (r) {
                    if (r.success) {
                        that.studentList = r.data;
                    } else {
                        alert(r.msg);
                    }
                });
            }
            ,
            updateStudent:function (student) {

                var username = prompt("情输入新的学生用户名",student.username);
                var password = prompt("请设置新的学生密码");
                var name = prompt("情输入新的学生姓名",student.studentName);

                var that = this;
                if (username && password && name){
                    common.ajax.post("/api/student/"+student.id,function (r) {
                        if (r.success){
                            alert(r.data);
                            that.selectStudentList();
                        }else{
                            alert(r.msg);
                        }
                    },{
                        username:username,
                        password:hex_md5(password).toLocaleUpperCase(),
                        studentName:name
                    });
                }
            }
            ,
            deleteStudent:function (s) {

                var that = this;
                common.ajax.delete("/api/student/"+s.id,function (r) {
                    if (r.success){
                        alert(r.data);
                        that.selectStudentList();
                    }else{
                        alert(r.msg);
                    }
                })
            }
        }
    });
</script>
</html>