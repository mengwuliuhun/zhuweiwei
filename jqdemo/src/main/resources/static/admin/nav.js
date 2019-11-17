$(function () {
    $.getJSON("../../../admin/isAdmin_ajax",function (ret) {
        if(ret.errorCode===-999)
        {
            alert(ret.message);
            window.location="../../../index.html";
        }
        $("#sidebar").on("click",'#adminExit',function (ret) {
            $.getJSON("../../../adminExit_ajax",function () {
                alert("管理员退出")
                window.location=window.location="../../../index.html";;
            })
        })
    });
    $("#sidebar").load("nav.html",function () {
        
    });
});