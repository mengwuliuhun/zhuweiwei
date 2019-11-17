$(function () {
    var search=location.search;
    var key;
    var value;
    if(search!=null&&search!=="")
    {
        search=search.split("?")[1];
        key=search.split("=")[0];
        value=search.split("=")[1];
    }

    var id;
    if(key==="id")
    {
        if(!isNaN(value))
        {
            id=value;
            $.getJSON("../../../admin/selectUser",{id:id},function (data) {
                if(data.errorCode)
                {
                    alert("查询失败");
                    window.location="table.html";
                }else {
                    var ret=data.data;
                    $("#id").val(id);
                    $("#name").val(ret.name);
                    $("#phone").val(ret.phone);
                    $("#password").val(ret.password);
                    $("#img").attr({src:ret.image});
                }
            })
        }
    }
    $("#save").on("click", function () {
        $.ajax({
            url: '../../../admin/updateUser',
            type: 'post',
            processData: false,
            contentType: false,
            data: new FormData($("#form")[0]),
            success: function (ret) {
                if (ret.errorCode) {
                    alert(ret.message);
                } else {
                    alert(ret.message);
                }
            }

        });
    });
    $("#name").blur(function () {

            var data = $("#form").serializeArray();
            $.getJSON("../../../regName_ajax", data, function (result) {
                var name_ajax=$("#name_ajax");
                name_ajax.empty();
                console.log(result);
                if (result.errorCode) {
                    name_ajax.text(result.message).css({color:"red"});
                } else {
                }
            });
    });
    $("#phone").blur(function () {

            var data = $("#form").serializeArray();
            $.getJSON("../../../regPhone_ajax", data, function (result) {
                var phone_ajax=$("#phone_ajax");
                phone_ajax.empty();
                if (result.errorCode) {
                    phone_ajax.text(result.message).css({color:"red"});
                } else {
                }
            });
    });
});