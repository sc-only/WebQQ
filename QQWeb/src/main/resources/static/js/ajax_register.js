//实现注册
$(document).ready(function () {

    $("#btn1").click(function () {
        var username = $("#rtinput1").val();
        var password = $("#rtinput2").val();
        var repassword = $("#rtinput3").val();
        console.log("提交请求")
        $.ajax({
            url:"register",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"username":username,"password":password,"repassword":repassword},
            success:function(data){
                if(data=="yes"){
                    alert("注册成功");
                    window.location.href="index";
                }else if(data=="no1"){
                    alert("两次密码输入不同");
                }else if(data=="no2") {
                    alert("密码长度要在6到16位之间");
                }else if(data=="no"){
                    alert("用户名已存在");
                }
            },
            error:function () {
                console.log(data)
                alert("注册请求错误");
            }
        });
    })

})