//实现用户登录
$(document).ready(function () {

    $("#account4").click(function () {
        var username = $("#text1").val();
        var password = $("#text3").val();
        console.log("登录请求");
        $.ajax({
            url:"login",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"username":username,"password":password},
            success:function (data) {
                // if(data=="yes"){
                //     alert("登录成功");
                //     window.location.href="index1_1";
                // }else
                if(data == "no1"){
                    alert("用户名不存在");
                }else if(data == "no"){
                    alert("密码错误");
                }else if(data == "no2"){
                    alert("您的帐号被管理员封禁");
                }
                else{
                    console.log(data);
                    var json = JSON.parse(data);
                    for (var i=0; i<json.length; i++) {
                        if(json[i].enter==1){
                            alert("登录成功");
                            window.location.href="index1_1";
                        }else{
                            alert("您的账号被管理员封禁")
                        }
                    }
                }
            },
            error:function () {
                console.log(data);
                alert("登录请求错误");
            }
        });
    })
})