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

    <div class="panel panel-default" v-if="!isPublish">
        <div class="panel-heading">
            选题
        </div>
        <div class="panel-body">
            <table class="table table-striped table-responsive">
                <tr>
                    <th>课题名称</th>
                    <th>课题描述</th>
                    <th>操作</th>
                </tr>
                <tr v-for="s in list">
                    <th>{{s.name}}</th>
                    <th>{{s.request}}</th>
                    <th>
                        <button class="btn btn-primary" @click="join(s)">加入此课题</button>
                    </th>
                </tr>

            </table>
        </div>
    </div>


    <h3>我的课题</h3>
    <div class="panel panel-default" v-if="isPublish">
        <div class="panel-heading">
            {{subject.name}}
            <button class="btn btn-danger" style="float: right" @click="quitSubject(subject)">退出此课题</button>


        </div>
        <div class="panel-body">
            {{subject.request}}
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
           list:[],
           subject:{},
           isPublish:false
       }
       ,
        created:function () {
            this.selectList();
            this.getSubject();
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
            getSubject:function () {
                var that = this;
                common.ajax.get("/api/subject/student",function (r) {
                    if (r.success){
                        if (r.data){
                            that.subject = r.data;
                            that.isPublish = true;
                        }else{
                            that.isPublish = false;
                        }


                    }else{
                        alert(r.msg)
                    }
                })
            }
           ,
            quitSubject:function (s) {
                var that = this;
                common.ajax.delete("/api/subject/student/",function (r) {
                    if (r.success){

                        alert(r.data);
                        that.getSubject();
                        that.selectList();

                    }else{
                        alert(r.msg);
                    }
                })

            }
            ,
            join:function (s) {
               var that = this;
                common.ajax.put("/api/subject/student/"+s.id,function (r) {
                    if (r.success){
                        alert(r.data);
                        that.getSubject();
                        that.selectList();
                    }else{
                        alert(r.msg);
                    }
                })
            }
        }


    });
</script>
</html>