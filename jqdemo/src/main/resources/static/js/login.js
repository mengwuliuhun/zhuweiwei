$(function () {
    imgCode.onclick = function () {
        console.log('重新加载验证码')
        this.src = "codes?" + new Date().getTime();
    }
    $("#submit").click(function () {
        var data = $("#form").serializeArray();
        $.post("log_ajax", data, function (result) {
            console.log(result);
            if (result.errorCode) {
                alert(result.message);
            } else {
                alert(result.message);
                location = "index.html";
            }
        },"json");
    });
    $("#name").blur(function () {
        var data = $("#form").serializeArray();
        $.getJSON("logPhoneName_ajax", data, function (result) {
            $("#name_ajax").empty();
            if (result.errorCode) {
                $("#name_ajax").text(result.message).css({color:"red"});
            } else {
            }
        });
    });
});