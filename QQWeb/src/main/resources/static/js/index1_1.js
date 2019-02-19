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
