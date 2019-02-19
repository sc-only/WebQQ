// alert(getCookie("userName"));
var getCookie = function (name) {
    var arr;
    var reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)){
        return arr[2];
    }
    else
        return null;
};
var friendname =getCookie("userName");
document.getElementById("friendname").innerHTML=friendname;
// console.log($('p'));
// console.log($('div'));
// console.log($('#friendname'));
// alert(friendname);
// $("p").text(friendname);
$(document).ready(function(){
    $("#head").mouseover(function(){
        $("#head_1").show();
    })
    $("#head_1").mouseleave(function(){
        $("#head_1").hide();
    });
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
    $.ajax({
        async: false,
        url:"getps",
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        data:{"username":friendname},
        success: function(data) {
            var json = JSON.parse(data);
            for (var i = 0 ;i <json.length;i++){
                $("#ps").text(json[i].signature);
            }
        }
    });
    $.ajax({
        async: false,
        url:"headimage",
        contentType:"application/x-www-form-urlencoded",
        type: "post",
        data:{"username":friendname},
        success:function (data) {
            var json = JSON.parse(data);
            for(var j = 0 ; j < json.length;j++){
                // alert(json[j].url);
                $('#fp').attr('src',json[j].url);
            }
        }
    });
})
