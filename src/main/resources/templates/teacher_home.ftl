<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>教师管理主页</title>
</head>
<body>


    <div class="container" id="app">

        <button class="btn btn-primary" v-show="!isPublish" @click="addSubject">出题</button>

        <h3>我的课题</h3>
        <div class="panel panel-default" v-if="isPublish">
            <div class="panel-heading">
                {{subject.name}}
                <button class="btn btn-danger" style="float: right" @click="deleteSubject(subject)">删除</button>
                <button class="btn btn-success" style="float: right" @click="update(subject)">修改</button>

            </div>
            <div class="panel-body">
                {{subject.request}}
            </div>
            <button class="btn btn-warning " @click="showStudent(subject)">查看选择此课题的学生</button>
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
            subject:{},
            isPublish:false
        }
        ,
        created:function () {
            this.getSubject();
        }
        ,
        methods:{
            getSubject:function () {
                var that = this;
                common.ajax.get("/api/subject/self",function (r) {
                    if (r.success){

                        if (r.data != null){
                            that.isPublish = true;
                            that.subject = r.data;
                        }else{
                            that.isPublish = false;
                        }

                    }else{
                        alert(r.msg);
                    }
                })
            }
            ,
            update:function (s) {

                var name = prompt("情输入新的课题名称",s.name);
                var request = prompt("情输入新的课题描述",s.request);

                if (name && request){
                    var that = this;
                    common.ajax.post("/api/subject/",function (r) {
                        if (r.success){
                            alert(r.data);
                            that.getSubject();
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
            showStudent:function (s) {
                var list = s.students;

                alert("选择的学生:"+list);
            }
            ,
            deleteSubject:function (s) {

                var that = this;
                common.ajax.delete("/api/subject/"+s.id,function (r) {
                    if (r.success){
                        alert(r.data);
                        that.getSubject();
                    }else{
                        alert(r.msg);
                    }
                })
            }
            ,
            addSubject:function () {

                var name = prompt("情输入课程名称");
                var request = prompt("情输入课程描述");
                var that = this;
                if (name && request){
                    common.ajax.put("/api/subject/",function (r) {
                        if (r.success){
                            alert(r.data);
                            that.getSubject();
                        }else{
                            alert(r.msg);
                        }
                    },{
                        name:name,
                        request:request
                    });
                }
            }

        }
    })
</script>
</html>