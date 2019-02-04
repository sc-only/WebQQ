//实现找回密码
$(document).ready(function () {

    $("#btn3").click(function () {
        var name = $("#rtinput7").val();
        var password = $("#rtinput8").val();
        var repassword = $("#rtinput9").val();
        console.log("找回密码请求");
        $.ajax({
            url:"change",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"name":name,"password":password,"repassword":repassword},
            success:function (data) {
                if(data == "no1"){
                    alert("用户名不存在");
                }else if(data == "no2"){
                    alert("两次密码不同");
                }else if(data == "no3"){
                    alert("密码长度要大于6位小于16位");
                }else if(data == "yes"){
                    alert("密码修改成功");
                    window.location.href="index2";
                }
            },
            error:function () {
                console.log(data);
                alert("修改密码请求失败")
            }
        })
    })
})