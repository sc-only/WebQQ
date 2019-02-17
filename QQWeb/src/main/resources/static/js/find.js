$(document).ready(function () {

    $.ajax({
        async: false,
        url:"username",
        contentType:"application/x-www-form-urlencoded",
        type: "get",
        success:function (data) {
            var json = JSON.parse(data);
            for(var i =0;i<json.length;i++){
                $("#username").text(json[i].username);
            }
        }
    });
})