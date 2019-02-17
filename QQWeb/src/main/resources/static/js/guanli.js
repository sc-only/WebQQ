$(document).ready(function () {
    $.ajax({
        url:"find",
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        success:function (data) {
            var json = JSON.parse(data);
            for(var i= 0 ; i <json.length;i++){
                if(i==0){
                    $(".name").text(json[i].username);
                    $(".pass").text(json[i].password);
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
                    newp.id=i;
                    // $("#"+username).text(username);
                    // $("#"+up).text(password);
                    newp.innerHTML=username;
                    newpass.innerHTML=password;
                    newfie.appendChild(newle_1);
                    newfie.appendChild(newp);
                    newfie.appendChild(newh4);
                    newfie.appendChild(newpass);
                    var newimg = document.createElement("img");
                    newle_1.appendChild(newimg);
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
                    newinput1.id="xgtx";
                    newli1.appendChild(newinput1);
                    var newli2 = document.createElement("li");
                    newli2.className="ri_2";
                    newul.appendChild(newli2);
                    var newinput2 = document.createElement("input");
                    newinput2.type="submit";
                    newinput2.value="帐号删除";
                    newinput2.id="zhsc";
                    newli2.appendChild(newinput2);
                    var newli3 = document.createElement("li");
                    newul.appendChild(newli3);
                    var newinput3 = document.createElement("input");
                    newinput3.type="submit";
                    newinput3.value="用户封禁";
                    newinput3.id="yhfj";
                    newli3.appendChild(newinput3);
                }
            }
        },
        error:function () {
        }
    })
})