<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>管理员管理</title>
</head>
<body>
<div class="container" id="app">

    <div class="row">
        <div class="col-md-4">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="#">主页</a></li>
                <li role="presentation"><a href="/admin/student">学生管理</a></li>
                <li role="presentation"><a href="/admin/teacher">教师管理</a></li>
                <li role="presentation"><a href="/admin/subject">课题管理</a></li>
            </ul>

        </div>
        <div class="col-md-8">
            <h3>系统信息</h3>
            <ul class="list-group">
                <li class="list-group-item">学生数：{{info.student}}</li>
                <li class="list-group-item">教师数：{{info.teacher}}</li>
                <li class="list-group-item">课题数：{{info.subject}}</li>

            </ul>
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
           info:{}
       }
       ,
        created:function () {
            this.getInfo();
        }
       ,
        methods:{
           getInfo:function () {

               var that = this;
               common.ajax.get("/api/admin/info",function (r) {
                   if (r.success){
                       that.info = r.data;
                   }else{
                       alert(r.msg);
                   }
               })
           }
        }
    });
</script>
</html>