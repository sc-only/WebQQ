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
})