$(function () {
    imgCode.onclick = function () {
        console.log('重新加载验证码')
        this.src = "codes?" + new Date().getTime();
    }
    $("#submit").click(function () {
        var phone = $("#phone").val();
        var name=$("#name").val();
        var password=$("#pass").val();
        var rpass=$("#rpass").val();
        if(password!==rpass)
        {
            alert("密码和确认密码不同");
            return false;
        }
        var regPhone = /^1\d{10}$/;
        var regName = /^[a-zA-z]\w{1,11}$/;
        var regPassword = /^\w{3,16}$/;
        if(regPhone.test(phone)===false)
        {
            alert("手机号码要11位数字,且以1开头");
            return false;
        }
        if(regName.test(name)===false)
        {
            alert("名字以英文开头且为2到12位");
            return false;
        }
        if (regPassword.test(password) === false) {
            alert("密码要3到16位的字符");
            return false;
        }
        var data = $("#form").serializeArray();

        $.post("reg_ajax", data, function (result) {
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
        $.getJSON("regName_ajax", data, function (result) {
            $("#name_ajax").empty();
            console.log(result);
            if (result.errorCode) {
                $("#name_ajax").text(result.message).css({color:"red"});
            } else {
            }
        });
    });
    $("#phone").blur(function () {
        var data = $("#form").serializeArray();
        $.getJSON("regPhone_ajax", data, function (result) {
          $("#phone_ajax").empty();
            if (result.errorCode) {
                $("#phone_ajax").text(result.message).css({color:"red"});
            } else {
            }
        });
    });
    $("#rpass").blur(function () {
        $("#repass").empty();
        var pass=$("#pass").val();
        var repass=$("#rpass").val();
        if(pass=== ""||repass=== "")
        {
            $("#repass").text("密码或者确认密码不能为空").css({color:"red"});
        }
        if(pass!==repass)
        {
            $("#repass").text("密码和确认密码不相等").css({color:"red"});
        }
    });
});