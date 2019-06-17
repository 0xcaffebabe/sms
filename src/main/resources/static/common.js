function Common(){
    return{ ajax: {
        get: function (url, success, data) {
            if (data == undefined || data == null) {
                $.ajax({
                    url: url,
                    method: "GET",
                    success: success,
                    error: function (response) {
                        alert("数据请求出错");
                        console.log(response);
                    }
                });
            } else {
                $.ajax({
                    url: url,
                    method: "GET",
                    success: success,
                    data: data,
                    error: function (response) {
                        alert("数据请求出错");
                        console.log(response);
                    }
                });
            }

        }
    ,
        post: function (url, success, data) {
            $.ajax({
                url: url,
                method: "POST",
                headers: {"Content-Type": "application/json"},
                data: JSON.stringify(data),
                success: success,
                error: function (response) {
                    alert("数据请求出错");
                    console.log(response);
                }
            });
        }
    ,
        put: function (url, success, data) {
            if (data == undefined || data == null) {
                $.ajax({
                    url: url,
                    method: "PUT",
                    headers: {"Content-Type": "application/json"},
                    success: success,
                    error: function (response) {
                        alert("数据请求出错");
                        console.log(response);
                    }
                });
            } else {
                $.ajax({
                    url: url,
                    method: "PUT",
                    headers: {"Content-Type": "application/json"},
                    success: success,
                    data: JSON.stringify(data),
                    error: function (response) {
                        alert("数据请求出错");
                        console.log(response);
                    }
                });
            }
        }
    ,
        delete:function (url,success,data) {
            if (data == undefined || data == null) {
                $.ajax({
                    url: url,
                    method: "DELETE",
                    headers: {"Content-Type": "application/json"},
                    success: success,
                    error: function (response) {
                        alert("数据请求出错");
                        console.log(response);
                    }
                });
            } else {
                $.ajax({
                    url: url,
                    method: "DELETE",
                    success: success,
                    headers: {"Content-Type": "application/json"},
                    data: JSON.stringify(data),
                    error: function (response) {
                        alert("数据请求出错");
                        console.log(response);
                    }
                });
            }

        }
    ,
        upload: function (url, success, data) {

            $.ajax({
                url: url,
                type: 'POST',
                data: data,
                processData: false,
                contentType: false,
                success: success,
                error: function (response) {
                    alert("数据请求出错");
                    console.log(response);
                }
            });
        }
    }}
}
var common = Common();