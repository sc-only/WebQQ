$(document).ready(function () {
    $.ajax({
        async: false,
        url:"find",
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        success:function (data) {
            var json = JSON.parse(data);
            for(var i= 0 ; i <json.length;i++){
                if(i==0){
                    var username = json[i].username;
                    $(".name").text(username);
                    $(".pass").text(json[i].password);
                    var pic = document.getElementById("prc");
                    pic.className=username;
                    $.ajax({
                        async: false,
                        url:"headimage",
                        contentType:"application/x-www-form-urlencoded",
                        type: "post",
                        data:{"username":username},
                        success:function (data) {
                            var json = JSON.parse(data);
                            for(var j = 0 ; j < json.length;j++){
                                $('.'+username).attr('src',json[j].url);
                            }
                        }
                    });
                }else{
                    var father = document.getElementById("body");
                    var newfie = document.createElement("div");
                    newfie.className="fir";
                    father.appendChild(newfie);
                    // var newleft = document.createElement("div");
                    // newleft.className="left";
                    // newfie.appendChild(newleft);
                    var newle_1 = document.createElement("div");
                    var newp = document.createElement("p");
                    var newh4 = document.createElement("h4");
                    var newpass = document.createElement("span");
                    newle_1.className="le_1";
                    newp.className="name";
                    newpass.className="pass";
                    newh4.innerHTML="用户名密码";
                    var username = json[i].username;
                    var password = json[i].password;
                    // newp.className=username;
                    // $("#"+username).text(username);
                    // $("#"+up).text(password);
                    newp.innerHTML=username;
                    newpass.innerHTML=password;
                    newfie.appendChild(newle_1);
                    newfie.appendChild(newp);
                    newfie.appendChild(newh4);
                    newfie.appendChild(newpass);
                    var newimg = document.createElement("img");
                    newimg.className=username;
                    newle_1.appendChild(newimg);
                    $.ajax({
                        async: false,
                        url:"headimage",
                        contentType:"application/x-www-form-urlencoded",
                        type: "post",
                        data:{"username":username},
                        success:function (data) {
                            var json = JSON.parse(data);
                            for(var j = 0 ; j < json.length;j++){
                                // alert(username);
                                $('.'+username).attr('src',json[j].url);
                            }
                        }
                    });
                    // var newright = document.createElement("div");
                    // newright.className = "right";
                    // newfie.appendChild(newright);
                    var newul = document.createElement("ul");
                    newfie.appendChild(newul);
                    var newli1 = document.createElement("li");
                    newul.appendChild(newli1);
                    var newinput1 = document.createElement("input");
                    newinput1.type="submit";
                    newinput1.value="修改头像";
                    newinput1.className="xgtx";
                    newli1.appendChild(newinput1);
                    var newli2 = document.createElement("li");
                    newli2.className="ri_2";
                    newul.appendChild(newli2);
                    var newinput2 = document.createElement("input");
                    newinput2.type="submit";
                    newinput2.value="帐号删除";
                    newinput2.className="zhsc";
                    newli2.appendChild(newinput2);
                    var newli3 = document.createElement("li");
                    newul.appendChild(newli3);
                    var newinput3 = document.createElement("input");
                    newinput3.type="submit";
                    newinput3.value="用户封禁";
                    newinput3.className="yhfj";
                    newli3.appendChild(newinput3);
                }
            }
        },
        error:function () {
        }
    });

    $(".zhsc").click(function () {
        var username;
        username=prompt("请确认你要删除的用户");
        $.ajax({
            async: false,
            url:"delete",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data:{"username":username},
            success:function (data) {
                if(data=="yes"){
                    alert("删除成功");
                    window.location.href="guanli";
                }else{
                    alert("用户名不存在");
                }
            },
            error:function () {
                alert("删除好友请求错误");
            }
        });
    });

    $(".yhfj").click(function () {
        var username = prompt("请确认你要封禁的对象");
        $.ajax({
            async: false,
            url:"pass",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data:{"username":username},
            success:function (data) {
                var json = JSON.parse(data);
                for(var i = 0;i<json.length;i++){
                    $("#password1").text(json[i].password);
                }
            }
        });
        var password = document.getElementById("password1").innerHTML;
        $.ajax({
            // async: false,
            url:"ban",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data:{"username":username,"password":password},
            success:function (data) {
                if(data=="yes"){
                    alert("用户封禁成功");
                }
            }
        })
    });
})