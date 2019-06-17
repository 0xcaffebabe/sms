<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>系统</title>
</head>
<body>
<h3>欢迎登陆毕业设计选题系统</h3>
<div class="container" id="app">
    <h3>登陆</h3>

    用户名:<input type="text" class="form-control" v-model="username">
    密码:<input type="password" class="form-control" v-model="password">
    身份：
    <select v-model="type">
        <option value="管理员">管理员</option>
        <option value="教师">教师</option>
        <option value="学生">学生</option>
    </select>
    <button class="btn btn-primary" style="float: right" @click="login">登陆</button>
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
            username:'',
            password:'',
            type:''
        }
        ,
        methods:{
            login:function () {
                common.ajax.post("/login",function (r) {
                    if (r.success){
                        alert(r.msg);
                        window.location="/home";
                    }else{
                        alert(r.msg);
                    }
                },{
                    username:this.username,
                    password:hex_md5(this.password).toLocaleUpperCase(),
                    type:this.type
                });


            }
        }
    })
</script>
</html>