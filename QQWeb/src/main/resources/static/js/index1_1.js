$(document).ready(function(){
    $("#add").click(function(){
        $("#addfri").animate({height:'toggle'});
    });
    $("#af").click(function(){
        $("#addfri").hide();
    });
    $.ajax({
        async: false,
        url:"username",
        contentType:"application/x-www-form-urlencoded",
        type: "get",
        success:function (data) {
            var json = JSON.parse(data);
            for(var i =0;i<json.length;i++){
                var username = json[i].username;
                $("#username").text(username);
                // alert(username);
                $.ajax({
                    async: false,
                    url:"get",
                    contentType:"application/x-www-form-urlencoded",
                    type: "post",
                    data:{"username":username},
                    success:function (data) {
                        var json = JSON.parse(data);
                        for(var i = 0 ; i <json.length;i++){
                            var name = json[i].friend;
                            $("#mot1").text(name);
                            $.ajax({
                                async: false,
                                url:"headimage",
                                contentType:"application/x-www-form-urlencoded",
                                type: "post",
                                data:{"username":name},
                                success:function (data) {
                                    var json = JSON.parse(data);
                                    for(var j = 0 ; j < json.length;j++){
                                        // $('#fp').attr('src',json[j].url);
                                        $('#fp').attr('src',json[i].url);
                                    }
                                }
                            });
                        }
                    }
                });
                $.ajax({
                    async: false,
                    url:"headimage",
                    contentType:"application/x-www-form-urlencoded",
                    type: "post",
                    data:{"username":username},
                    success:function (data) {
                        var json = JSON.parse(data);
                        for(var j = 0 ; j < json.length;j++){
                            // $('#fp').attr('src',json[j].url);
                            $('#userp').attr('src',json[i].url);
                        }
                    }
                });
            }
        }
    });
    $("#cz").click(function () {
        var username = $("#name").val();
        $.ajax({
            async: false,
            url:"search",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data:{"username":username},
            success:function (data) {
                var json = JSON.parse(data);
                for(var i = 0; i<json.length;i++){
                    var name = json[i].username;
                    $("#friend").text(name);
                    $.ajax({
                        async: false,
                        url:"headimage",
                        contentType:"application/x-www-form-urlencoded",
                        type: "post",
                        data:{"username":name},
                        success:function (data) {
                            var json = JSON.parse(data);
                            for(var j = 0 ; j < json.length;j++){
                                $('.up').attr('src',json[j].url);
                            }
                        }
                    });
                }
            }
        })
    });

    $("#tj").click(function () {
        var finduser = document.getElementById("username").innerHTML;
        var friend = document.getElementById("friend").innerHTML;
        $.ajax({
            url:"addfriend",
            contentType:"application/x-www-form-urlencoded",
            type: "post",
            data:{"finduser":finduser,"friend":friend},
            success:function (data) {
                if(data=="yes"){
                    alert("添加好友成功");
                    window.location.href="index1_1";
                }else{
                    alert("对方已经是你的好友");
                }
            }
        });
    });

})
var webSocket;
var commWebSocket;
if ("WebSocket" in window)
{
    webSocket = new WebSocket("ws://localhost:8080/index1_1/"+document.getElementById('username').innerHTML);

    //连通之后的回调事件
    webSocket.onopen = function()
    {
        //webSocket.send( document.getElementById('username').value+"已经上线了");
        console.log("已经连通了websocket");
        <!--setMessageInnerHTML("已经连通了websocket");-->
    };

    //接收后台服务端的消息
    webSocket.onmessage = function (evt)
    {
        var received_msg = evt.data;
        console.log("数据已接收:" +received_msg);
        var obj = JSON.parse(received_msg);
        console.log("可以解析成json:"+obj.messageType);
        //1代表上线 2代表下线 3代表在线名单 4代表普通消息
        if(obj.messageType==1){
            //把名称放入到selection当中供选择
            var onlineName = obj.username;
            // var option = "<option>"+onlineName+"</option>";
            <!--$("#onLineUser").append(option);-->
            <!--setMessageInnerHTML(onlineName+"上线了");-->
        }
        else if(obj.messageType==2){
            <!--$("#onLineUser").empty();-->
            var onlineName = obj.onlineUsers;
            var offlineName = obj.username;
            // <!--var option = "<option>"+"&#45;&#45;所有&#45;&#45;"+"</option>";-->
            // <!--for(var i=0;i<onlineName.length;i++){-->
            //     <!--if(!(onlineName[i]==document.getElementById('username').value)){-->
            //         <!--option+="<option>"+onlineName[i]+"</option>"-->
            //     <!--}-->
            // <!--}-->
            <!--$("#onLineUser").append(option);-->

            <!--setMessageInnerHTML(offlineName+"下线了");-->
        }
        else if(obj.messageType==3){
            <!--var onlineName = obj.onlineUsers;-->
            <!--var option = null;-->
            <!--for(var i=0;i<onlineName.length;i++){-->
            <!--if(!(onlineName[i]==document.getElementById('username').value)){-->
            //         <!--option+="<option>"+onlineName[i]+"</option>"-->
            //     <!--}-->
            // <!--}-->
            // <!--$("#onLineUser").append(option);-->
            // <!--console.log("获取了在线的名单"+onlineName.toString());-->
        }
        else{
            setMessageInnerHTML(obj.fromusername+"对"+obj.tousername+"说："+obj.textMessage);
        }
    };

    //连接关闭的回调事件
    webSocket.onclose = function()
    {
        console.log("连接已关闭...");
        setMessageInnerHTML("连接已经关闭....");
    };
}
else{
    // 浏览器不支持 WebSocket
    alert("您的浏览器不支持 WebSocket!");
}
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

function closeWebSocket() {
    //直接关闭websocket的连接
    webSocket.close();
}

function send() {
    var selectText = document.getElementById("#mot1").innerHTML;
    alert(selectText);
    setMessageInnerHTML(document.getElementById('username').innerHTML+"对"+selectText+"说："+ $("#text").val());
    var message = {
        "message":$("#text").val(),
        "username":document.getElementById('username').innerHTML,
        "to":selectText
    };
    webSocket.send(JSON.stringify(message));
    $("#text").val("");

}

window.onload=function(){
    var Left=document.getElementById("left");
    var aLi=Left.getElementsByTagName("li");
    var Mid_body=document.getElementById("mid_body");
    var aLi2=Mid_body.getElementsByTagName("li");
    var oRtp=document.getElementById("ri_top_p1");
    var oRight_img=document.getElementById("right_img");
    var oElse=document.getElementById("else");

    var arrp=['JaeHyun','JaeMin','RenJun','ChenLe'];
    var arrImg=['img/3.jpg','img/5.jpg','img/6.jpg','img/7.jpg'];
    
    
        aLi[2].className="change1";
        for(var i=0;i<aLi.length;i++){   
            aLi[i].index=i;
            aLi[i].onclick=function(){
                for(var i=0;i<aLi.length;i++){
                    aLi[i].className='';
                }
                this.className="change1";
            }
        }
    
        oRtp.innerHTML=arrp[1];
        oRight_img.src=arrImg[1];
        aLi2[1].className="change2";
        for(var i=0;i<aLi2.length;i++){   
            aLi2[i].index=i;
            aLi2[i].onclick=function(){
                oRtp.innerHTML=arrp[this.index];
                oRight_img.src=arrImg[this.index];
                for(var i=0;i<aLi2.length;i++){
                    aLi2[i].className='';
                }
                this.className="change2";
            }
        }
}
