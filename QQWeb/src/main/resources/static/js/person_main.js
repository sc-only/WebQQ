$(document).ready(function(){
    $("#head").mouseover(function(){
        $("#head_1").show();
    })
    $("#head_1").mouseleave(function(){
        $("#head_1").hide();
    })
    $.ajax({
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
    $("#get").click(function () {
        var ps = $("#ps").val();
        // var name = $("#name").val();
        var name  = document.getElementById("name").innerHTML;
        console.log("修改个性签名");
        $.ajax({
            url:"ps",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data: {"name":name,"ps":ps},
            success:function (data) {
                if(data=="yes"){
                    alert(name);    
                    alert("修改个性签名成功");
                    $("#ps").text(ps);
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
})