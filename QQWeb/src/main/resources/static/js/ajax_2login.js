//实现管理员登录
$(document).ready(function () {

    $("#account6").click(function () {
        var name = $("#text2").val();
        var password = $("#text4").val();
        console.log("登录请求");
        $.ajax({
            url:"login2",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"name":name,"password":password},
            success:function (data) {
                if(data=="yes"){
                    alert("登录成功");
                    window.location.href="guanli";
                }else if(data == "no1"){
                    alert("用户名不存在");
                }else if(data == "no2"){
                    alert("密码错误");
                }
            },
            error:function () {
                console.log(data);
                alert("管理员登录错误，自己改bug去");
            }
        })
    })
})