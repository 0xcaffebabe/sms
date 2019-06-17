<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>课题管理</title>
</head>
<body>
<div class="container" id="app">

    <div class="row">
        <div class="col-md-4">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="/home">主页</a></li>
                <li role="presentation"><a href="/admin/student">学生管理</a></li>
                <li role="presentation"><a href="/admin/teacher">教师管理</a></li>
                <li role="presentation" class="active"><a href="#">课题管理</a></li>
            </ul>

        </div>
        <div class="col-md-8">
            <table class="table table-striped table-responsive">
                <tr>
                    <th>ID</th>
                    <th>课题名称</th>
                    <th>课题需求</th>
                    <th>负责教师</th>
                    <th>操作</th>
                </tr>

                <tr v-for="s in list">
                    <td>{{s.id}}</td>
                    <td>{{s.name}}</td>
                    <td>{{s.request}}</td>
                    <td>{{s.teacher}}</td>
                    <td>
                        <button class="btn btn-primary" @click="showStudent(s)">查看选择的学生</button>
                        <button class="btn btn-success" @click="update(s)">修改</button>

                        <button class="btn btn-danger" @click="deleteSubject(s)">删除</button>
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
        el:"#app",
        data:{
            list:[]
        }
        ,
        created:function () {
            this.selectList();
        }
        ,
        methods:{
            selectList:function () {

                var that = this;
                common.ajax.get("/api/subject/list",function (r) {
                    if (r.success){
                        that.list = r.data;
                    }else{
                        alert(r.msg);
                    }
                })
            }
            ,
            showStudent:function (s) {
                var list = s.students;

                alert("选择的学生:"+list);
            }
            ,update:function (s) {

                var name = prompt("情输入新的课题名称",s.name);
                var request = prompt("情输入新的课题描述",s.request);

                if (name && request){
                    var that = this;
                    common.ajax.post("/api/subject/",function (r) {
                        if (r.success){
                            alert(r.data);
                            that.selectList();
                        }else{
                            alert(r.msg);
                        }
                    },{
                        id:s.id,
                        name:name,
                        request:request

                    });
                }

            }
            ,
            deleteSubject:function (s) {

                common.ajax.delete("/api/subject/"+s.id,function (r) {
                    if (r.success){
                        alert(r.data);
                        that.selectList();
                    }else{
                        alert(r.msg);
                    }
                })
            }
        }
    })

</script>
</html>