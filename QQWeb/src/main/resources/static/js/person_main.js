$(document).ready(function(){
    $("#head").mouseover(function(){
        $("#head_1").show();
    })
    $("#head_1").mouseleave(function(){
        $("#head_1").hide();
    })
    $.ajax({
        async: false,
        url:"username",
        contentType:"application/x-www-form-urlencoded",
        type: "get",
        success:function (data) {
            var json = JSON.parse(data);
            for(var i =0;i<json.length;i++){
                $("#name").text(json[i].username);
            }
        }
    });
    var username  = document.getElementById("name").innerHTML;
    $.ajax({
        url:"getps",
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        data:{"username":username},
        success: function(data) {
            var json = JSON.parse(data);
            for (var i = 0 ;i <json.length;i++){
                document.getElementById("ps").innerHTML = json[i].signature;
                $("#ps").val(json[i].signature);
            }
        }
    });
    $.ajax({
        url:"headimage",
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        data:{"username":username},
        success:function (data) {
            var json = JSON.parse(data);
            for(var i = 0 ; i < json.length;i++){
                $('#grtx').attr('src',json[i].url);
            }
        }
    })
    $("#get").click(function () {
        var ps = $("#ps").val();
        var name  = document.getElementById("name").innerHTML;
        console.log("修改个性签名");
        $.ajax({
            url:"ps",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"name":name,"ps":ps},
            success:function (data) {
                if(data=="yes"){
                    alert("修改个性签名成功")
                    $("#ps").val(ps);
                }else{
                    alert("修改个性签名失败");
                }
            },
            error:function () {
                console.log(data);
                alert("修改请求失败");
            }
        })
    });
    $("#qrxg").click(function () {
        var username  = document.getElementById("name").innerHTML;
        var password = $("#pass").val();
        var repassword = $("#reps").val();
        console.log("修改密码请求");
        $.ajax({
            url:"update",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"username":username,"password":password,"repassword":repassword},
            success:function (data) {
                if(data == "no1"){
                    alert("用户名不存在");
                }else if(data == "no2"){
                    alert("两次密码不同");
                }else if(data == "no3"){
                    alert("密码长度要大于6位小于16位");
                }else if(data == "yes"){
                    alert("密码修改成功");
                }
            },
            error:function () {
                console.log(data);
                alert("修改密码请求失败")
            }
        })
    });
    var username = document.getElementById("name").innerHTML;
    $.ajax({
        url:"sum",
        async: false,
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        data: {"username":username},
        success:function (data) {
            var sum=0;
            var json = JSON.parse(data);
            for(var i = 0 ; i < json.length;i++){
                if(json[i].zan==1){
                    sum++;
                }
            }
            document.getElementById("num").innerHTML=sum;
        }
    })
})