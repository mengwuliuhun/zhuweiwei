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
            $.getJSON("../../../admin/selectCi",{id:id},function (data) {
                if(data.errorCode)
                {
                    alert("查询失败");
                    window.location="table.html";
                }else {
                    var ret=data.data;
                    $("#id").val(id);
                    $("#name").val(ret.name);
                    $("#title").val(ret.title);
                    $("#price").val("$"+ret.price);
                    $("#pubDate").val(ret.pubDate);
                    $("#img").attr({src:ret.image});
                }
            })
        }
    }
    $("#save").on("click", function () {
        $.ajax({
            url: '../../../admin/updateCoolIt',
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
});