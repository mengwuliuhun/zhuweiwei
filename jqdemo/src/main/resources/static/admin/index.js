$(function () {
    $("#submit").on("click",function () {
        var data=$("#form").serialize();
        $.getJSON("../aLogin_ajax",data,function (s) {
            alert(s.message);
            if(!s.errorCode)
            {
                window.location="html/user/table.html";
            }
        });
    });

});